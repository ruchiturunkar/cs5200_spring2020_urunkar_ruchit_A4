package com.example.cs5200_Spring2020_JPA.repository;

import com.example.cs5200_Spring2020_JPA.model.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
  @Query("select s from Student s where s.firstName =:firstname")
  public Student findStudentByFirstName(@Param("firstname") String firstname);
}
