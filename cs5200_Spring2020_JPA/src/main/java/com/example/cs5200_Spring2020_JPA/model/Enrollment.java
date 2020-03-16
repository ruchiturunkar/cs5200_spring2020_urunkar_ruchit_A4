package com.example.cs5200_Spring2020_JPA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="enrollments")
public class Enrollment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int grade;
  private String feedback;

  public Enrollment() {
  }

  public Enrollment(int grade, String feedback) {
    this.grade = grade;
    this.feedback = feedback;
  }



  public Section getSection() {
    return section;
  }

  public void setSection(Section section) {
    this.section = section;
    if(!section.getEnrolledStudents().contains(this))
      section.getEnrolledStudents().add(this);
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
    if(!student.getEnrolledSections().contains(this))
      student.getEnrolledSections().add(this);
  }

  @ManyToOne
  @JsonIgnore
  private Section section;

  @ManyToOne
  @JsonIgnore
  private Student student;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }



}
