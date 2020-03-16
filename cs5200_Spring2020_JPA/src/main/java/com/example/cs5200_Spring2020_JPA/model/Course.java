package com.example.cs5200_Spring2020_JPA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String label;

  public Course(String label) {
    this.label = label;
  }

  public Course() {}


  @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
  private List<Section> offeredSections;

  @ManyToOne()
  @JsonIgnore
  private Faculty author;

  public List<Section> getOfferedSections() {
    return offeredSections;
  }

  public void setOfferedSections(List<Section> offeredSections) {
    this.offeredSections = offeredSections;
  }

  public void offeredSection(Section section){
    this.getOfferedSections().add(section);
    if(section.getCourse() != this)
      section.setCourse(this);
  }

  public Faculty getAuthor() {
    return author;
  }

  public void setAuthor(Faculty author) {
    this.author = author;
    if(!author.getAuthoredCourses().contains(this)) {
      author.getAuthoredCourses().add(this);
    }
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
