package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class DynamicTarget {

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	private String name;

	protected DynamicTarget() {
		// Constructor for Hibernate (not used in code)
	}

	public DynamicTarget(String name) {
		Assert.hasText(name, "Name must not be empty");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
