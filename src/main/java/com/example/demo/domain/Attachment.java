package com.example.demo.domain;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

@Entity
public class Attachment {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(optional=false)
	private DynamicTarget dynamicTarget;

	@Lob
	private byte[] jpeg;

	protected Attachment() {
		// Constructor for Hibernate (not used in code)
	}

	public Attachment(DynamicTarget dynamicTarget, InputStream jpeg) {
		Assert.notNull(dynamicTarget, "DynamicTarget must not be null");
		Assert.notNull(jpeg, "JPEG must not be null");
		this.dynamicTarget = dynamicTarget;
		this.jpeg = getBytes(jpeg);
	}

	private byte[] getBytes(InputStream inputStream) {
		try {
			return StreamUtils.copyToByteArray(inputStream);
		} catch (IOException ex) {
			throw new IllegalStateException("Unable to load bytes", ex);
		}
	}

	public byte[] getJpeg() {
		return this.jpeg;
	}

}
