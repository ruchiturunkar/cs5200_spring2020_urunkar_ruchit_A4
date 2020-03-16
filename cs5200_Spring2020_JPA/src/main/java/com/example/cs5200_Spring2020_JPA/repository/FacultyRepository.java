package com.example.cs5200_Spring2020_JPA.repository;

import com.example.cs5200_Spring2020_JPA.model.Faculty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

  @Query("select f from Faculty f where f.firstName =:firstname")
  public Faculty findFacultyByFirstName(@Param("firstname") String firstname);

}