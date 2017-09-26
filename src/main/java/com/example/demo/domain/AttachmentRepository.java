package com.example.demo.domain;

import java.util.List;

import org.springframework.data.repository.Repository;

public interface AttachmentRepository extends Repository<Attachment, Long> {

	List<Attachment> findByDynamicTarget(DynamicTarget dynamicTarget);

}
