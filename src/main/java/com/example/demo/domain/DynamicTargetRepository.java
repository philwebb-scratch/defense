package com.example.demo.domain;

import org.springframework.data.repository.Repository;

public interface DynamicTargetRepository extends Repository<DynamicTarget, Long> {

	DynamicTarget findByName(String name);

}
