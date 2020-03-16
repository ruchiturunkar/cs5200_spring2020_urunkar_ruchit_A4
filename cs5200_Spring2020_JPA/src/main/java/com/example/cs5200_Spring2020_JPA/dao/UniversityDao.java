package com.example.cs5200_Spring2020_JPA.dao;

import com.example.cs5200_Spring2020_JPA.model.Course;
import com.example.cs5200_Spring2020_JPA.model.Faculty;
import com.example.cs5200_Spring2020_JPA.model.Person;
import com.example.cs5200_Spring2020_JPA.model.Section;
import com.example.cs5200_Spring2020_JPA.model.Student;

import java.util.List;

public interface UniversityDao {

  void truncateDatabase();
  Faculty createFaculty(Faculty faculty);
  Student createStudent(Student student);
  Course createCourse(Course course);
  Section createSection(Section section);
  Course setAuthorForCourse(String faculty, String course);
  Course addSectionToCourse(String section, String course);
  Boolean enrollStudentInSection(String student, String section);

  List<Person> findAllUsers();
  List<Faculty> findAllFaculty();
  List<Student> findAllStudents();
  List<Course> findAllCourses();
  List<Section> findAllSections();
  List<Course> findCoursesForAuthor(Faculty faculty);
  List<Section> findSectionForCourse(Course course);
  List<Student> findStudentsInSection(Section section);
  List<Section> findSectionsForStudent(Student student);
  int findSeatsRemainingForSection(Section section);
}
