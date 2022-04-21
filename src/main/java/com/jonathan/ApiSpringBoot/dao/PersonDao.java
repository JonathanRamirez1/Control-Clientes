package com.jonathan.ApiSpringBoot.dao;

import com.jonathan.ApiSpringBoot.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {
}
