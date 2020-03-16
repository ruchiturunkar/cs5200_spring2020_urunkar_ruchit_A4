package com.example.cs5200_Spring2020_JPA.repository;

import com.example.cs5200_Spring2020_JPA.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {}
