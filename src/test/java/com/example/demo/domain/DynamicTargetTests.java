package com.example.demo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Tests for {@link DynamicTarget}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class DynamicTargetTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void createWhenNameIsEmptyShouldThrowException() throws Exception {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Name must not be empty");
		new DynamicTarget("");
	}

	@Test
	public void saveShouldPersistData() throws Exception {
		DynamicTarget write = new DynamicTarget("foo");
		DynamicTarget read = this.entityManager.persistAndFlush(write);
		assertThat(read.getName()).isEqualTo("foo");
	}

}
