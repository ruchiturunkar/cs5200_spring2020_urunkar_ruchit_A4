package com.example.cs5200_Spring2020_JPA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Faculty extends Person{
  private String office;
  private boolean tenured;

  public Faculty(String userName, String password, String firstName, String lastName, String office, boolean tenured) {
    super(userName, password, firstName, lastName);
    this.office = office;
    this.tenured = tenured;

  }

  public Faculty(String office, boolean tenured) {
    this.office = office;
    this.tenured = tenured;
    this.authoredCourses = null;
  }

  public Faculty(){
    this.authoredCourses = null;
   }

  @OneToMany(mappedBy="author", fetch = FetchType.EAGER)
  private List<Course> authoredCourses;

  public List<Course> getAuthoredCourses() {
    return authoredCourses;
  }

  public void setAuthoredCourses(List<Course> authoredCourses) {
    this.authoredCourses = authoredCourses;
  }

  public void authoredCourse(Course course) {
  /*  if(this.getAuthoredCourses().size() == 0) {
      this.authoredCourses = new ArrayList<>();*/
      this.authoredCourses.add(course);
    //}
/*    else if(!this.getAuthoredCourses().contains(course))
      this.authoredCourses.add(course);*/
    if (course.getAuthor() != this)
        course.setAuthor(this);

  }


  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public boolean isTenured() {
    return tenured;
  }

  public void setTenured(boolean tenured) {
    this.tenured = tenured;
  }
}
