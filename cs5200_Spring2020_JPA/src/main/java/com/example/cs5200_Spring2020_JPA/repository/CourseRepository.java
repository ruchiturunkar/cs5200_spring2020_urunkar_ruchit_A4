package com.example.cs5200_Spring2020_JPA.repository;

import com.example.cs5200_Spring2020_JPA.model.Course;
import com.example.cs5200_Spring2020_JPA.model.Faculty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

  @Query("select c from Course c where c.label =:label")
  public Course findCoursesByLabel(@Param("label") String label);
}

