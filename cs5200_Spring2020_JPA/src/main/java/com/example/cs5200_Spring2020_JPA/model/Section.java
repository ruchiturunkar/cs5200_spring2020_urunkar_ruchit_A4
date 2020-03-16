package com.example.cs5200_Spring2020_JPA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name ="sections")
public class Section {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String title;
  private int seats;

  public Section() {
  }

  public Section(String title, int seats) {
    this.title = title;
    this.seats = seats;
  }

  @ManyToOne
  @JsonIgnore
  private Course course;

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
    if(!course.getOfferedSections().contains(this))
      course.getOfferedSections().add(this);
  }

  @OneToMany(mappedBy = "section")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Enrollment> enrolledStudents;

  public void enrollStudent(Enrollment student){
    this.getEnrolledStudents().add(student);
    if(student.getSection()!=this)
      student.setSection(this);
  }


  public List<Enrollment> getEnrolledStudents() {
    return enrolledStudents;
  }

  public void setEnrolledStudents(List<Enrollment> enrolledStudents) {
    this.enrolledStudents = enrolledStudents;
  }

  public int getSectionId() {
    return id;
  }

  public void setSectionId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSeats() {
    return seats;
  }

  public void setSeats(int seats) {
    this.seats = seats;
  }


}
