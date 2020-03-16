package com.example.cs5200_Spring2020_JPA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Student extends Person{
  private int gradYear;
  private long scholarship;

  public Student(String userName, String password, String firstName, String lastName, int gradYear, int scholarship) {
    super(userName, password, firstName, lastName);
    this.gradYear = gradYear;
    this.scholarship = scholarship;
  }

  public Student() {
  }

 /* @ManyToMany
  @JoinTable(name="ENROLLMENT",
          joinColumns=@JoinColumn(name="STUDENT_ID",
                  referencedColumnName="ID"),
          inverseJoinColumns=@JoinColumn(name=
                  "SECTION_ID", referencedColumnName="SECTION_ID"))*/

  @OneToMany(mappedBy = "student")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<Enrollment> enrolledSections;

  public void enrollInSection(Enrollment section){
    this.getEnrolledSections().add(section);
    if(section.getStudent()!=this)
      section.setStudent(this);
  }

  public List<Enrollment> getEnrolledSections() {
    return enrolledSections;
  }

  public void setEnrolledSections(List<Enrollment> enrolledSections) {
    this.enrolledSections = enrolledSections;
  }

  public int getGradYear() {
    return gradYear;
  }

  public void setGradYear(int gradYear) {
    this.gradYear = gradYear;
  }

  public long getScholarship() {
    return scholarship;
  }

  public void setScholarship(long scholarship) {
    this.scholarship = scholarship;
  }
}
