package com.example.cs5200_Spring2020_JPA.dao;

import com.example.cs5200_Spring2020_JPA.model.Course;
import com.example.cs5200_Spring2020_JPA.model.Enrollment;
import com.example.cs5200_Spring2020_JPA.model.Faculty;
import com.example.cs5200_Spring2020_JPA.model.Person;
import com.example.cs5200_Spring2020_JPA.model.Section;
import com.example.cs5200_Spring2020_JPA.model.Student;
import com.example.cs5200_Spring2020_JPA.repository.CourseRepository;
import com.example.cs5200_Spring2020_JPA.repository.EnrollmentRepository;
import com.example.cs5200_Spring2020_JPA.repository.FacultyRepository;
import com.example.cs5200_Spring2020_JPA.repository.PersonRepository;
import com.example.cs5200_Spring2020_JPA.repository.SectionRepository;
import com.example.cs5200_Spring2020_JPA.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UniversityImpl implements UniversityDao {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  FacultyRepository facultyRepository;

  @Autowired
  StudentRepository studentRepository;

  @Autowired
  CourseRepository courseRepository;

  @Autowired
  SectionRepository sectionRepository;

  @Autowired
  EnrollmentRepository enrollmentRepository;

  public UniversityImpl() {

  }

  @Override
  public void truncateDatabase() {
    enrollmentRepository.deleteAll();
    sectionRepository.deleteAll();
    courseRepository.deleteAll();
    facultyRepository.deleteAll();
    studentRepository.deleteAll();
    personRepository.deleteAll();
  }

  @Override
  public Faculty createFaculty(Faculty faculty) {
    Faculty f = facultyRepository.save(faculty);
    return f;
  }

  @Override
  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Course createCourse(Course course) {
    return courseRepository.save(course);
  }

  @Override
  public Section createSection(Section section) {
    return sectionRepository.save(section);
  }

  @Override
  public Course setAuthorForCourse(String faculty, String course) {
    Course c = courseRepository.findCoursesByLabel(course);
    Faculty faculty1 = facultyRepository.findFacultyByFirstName(faculty);
    c.setAuthor(faculty1);
    //faculty.authoredCourse(c);

    //Faculty faculty1 = facultyRepository.findFacultyByFirstName(faculty.getFirstName());
    //faculty1.setAuthoredCourses(faculty.getAuthoredCourses());
    //faculty1.authoredCourse(c);
    facultyRepository.save(faculty1);
    return courseRepository.save(c);
    //return c;
  }

  @Override
  public Course addSectionToCourse(String section, String course) {
    Course c = courseRepository.findCoursesByLabel(course);
    Section s = sectionRepository.findSectionByTitle(section);
    c.offeredSection(s);
    sectionRepository.save(s);
    return courseRepository.save(c);
    //return null;
  }

  @Override
  public Boolean enrollStudentInSection(String student, String section) {
    Student std = studentRepository.findStudentByFirstName(student);
    Section sec = sectionRepository.findSectionByTitle(section);

    if(sec.getSeats()==0)
      return false;
    Enrollment enrollment = new Enrollment();
    enrollment.setStudent(std);
    enrollment.setSection(sec);
    std.enrollInSection(enrollment);
    sec.enrollStudent(enrollment);
    sec.setSeats(sec.getSeats()-1);
    studentRepository.save(std);
    sectionRepository.save(sec);
    enrollmentRepository.save(enrollment);
    return true;
  }

  @Override
  public List<Person> findAllUsers() {
   return (List) personRepository.findAll();
  }

  @Override
  public List<Faculty> findAllFaculty() {
    return (List) facultyRepository.findAll();
  }

  @Override
  public List<Student> findAllStudents() {
    return (List) studentRepository.findAll();
  }

  @Override
  public List<Course> findAllCourses() {
    return (List) courseRepository.findAll();
  }

  @Override
  public List<Section> findAllSections() {
    return (List) sectionRepository.findAll();
  }

  @Override
  public List<Course> findCoursesForAuthor(Faculty faculty) {
    faculty = facultyRepository.findFacultyByFirstName(faculty.getFirstName());
    return faculty.getAuthoredCourses();
  }

  @Override
  public List<Section> findSectionForCourse(Course course) {
    course = courseRepository.findCoursesByLabel(course.getLabel());
    return course.getOfferedSections();
  }

  @Override
  public List<Student> findStudentsInSection(Section section) {
    section = sectionRepository.findSectionByTitle(section.getTitle());
    List<Enrollment> enrollments = section.getEnrolledStudents();
    List<Student> students = new ArrayList<>();
    for(Enrollment e : enrollments){
      students.add(e.getStudent());
    }
    return students;
  }

  @Override
  public List<Section> findSectionsForStudent(Student student) {
    student = studentRepository.findStudentByFirstName(student.getFirstName());
    List<Enrollment> enrollments = student.getEnrolledSections();
    List<Section> sections = new ArrayList<>();
    for(Enrollment e : enrollments){
      sections.add(e.getSection());
    }
    return sections;
  }

  @Override
  public int findSeatsRemainingForSection(Section section) {
    section = sectionRepository.findSectionByTitle(section.getTitle());
    return section.getSeats();
  }


}
