package com.example.demo;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Attachment;
import com.example.demo.domain.AttachmentRepository;
import com.example.demo.domain.DynamicTarget;
import com.example.demo.domain.DynamicTargetRepository;

@Component
@Transactional
public class Example implements CommandLineRunner {

	private final EntityManager entityManager;

	private final DynamicTargetRepository dynamicTargetRepository;

	private final AttachmentRepository attachmentRepository;

	public Example(EntityManager entityManager, DynamicTargetRepository dynamicTargetRepository, AttachmentRepository attachmentRepository) {
		this.entityManager= entityManager;
		this.dynamicTargetRepository = dynamicTargetRepository;
		this.attachmentRepository = attachmentRepository;;
	}

	@Override
	public void run(String... arg0) throws Exception {
		DynamicTarget dynamicTarget = new DynamicTarget("foo");
		this.entityManager.persist(dynamicTarget);
		Attachment attachment = new Attachment(dynamicTarget, new ByteArrayInputStream("a-funny-cat".getBytes()));
		this.entityManager.persist(attachment);

		// Usually handled for you
		this.entityManager.flush();

		DynamicTarget found = this.dynamicTargetRepository.findByName("foo");
		List<Attachment> attachments = attachmentRepository.findByDynamicTarget(found);
		System.err.println(new String(attachments.get(0).getJpeg()));
	}

}
