package com.example.cs5200_Spring2020_JPA;

import com.example.cs5200_Spring2020_JPA.dao.UniversityDao;
import com.example.cs5200_Spring2020_JPA.dao.UniversityImpl;
import com.example.cs5200_Spring2020_JPA.model.Course;
import com.example.cs5200_Spring2020_JPA.model.Faculty;
import com.example.cs5200_Spring2020_JPA.model.Person;
import com.example.cs5200_Spring2020_JPA.model.Section;
import com.example.cs5200_Spring2020_JPA.model.Student;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.*;
import org.springframework.util.Assert;

import java.util.List;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UniversityDao dao = new UniversityImpl();

/*	@Test
	void test() {
		List<Person> persons = (List) personRepository.findAll();
		for(Person p : persons){
			System.out.println(p.getFirstName());
		}
	}

	@Test
	void createPersonTest(){
		Person p = new Person("charlie", "charlie", "Charlie", "Jordon");
		personRepository.save(p);
	}*/

	/**
	 * Test to delete all the data from database.
	 * Please check in database if there exists records before running this test. If there exist no
	 * records, run other tests that create records and then run this test.
	 */
	@Test
	void truncateDatabase(){
		dao.truncateDatabase();
	}

	/**
	 * Test to create faculty records in database.
	 */
	@Test
	void createFaculty() {

		Faculty f = new Faculty();
		f.setOffice("123A");
		f.setTenured(true);
		f.setFirstName("Alan");
		f.setLastName("Turin");
		dao.createFaculty(f);

		f = new Faculty();
		f.setOffice("123B");
		f.setTenured(true);
		f.setFirstName("Ada");
		f.setLastName("Lovelace");
		dao.createFaculty(f);
	}

	/**
	 * Test to create students.
	 */
	@Test
	void createStudent(){

		Student s = new Student();
		s.setGradYear(2020);
		s.setScholarship(12000);
	  s.setFirstName("Alice");
	  s.setLastName("Wonderland");
	  dao.createStudent(s);

		s = new Student();
		s.setGradYear(2021);
		s.setScholarship(23000);
		s.setFirstName("Bob");
		s.setLastName("Hope");
		dao.createStudent(s);

		s = new Student();
		s.setGradYear(2019);
		s.setScholarship(21000);
		s.setFirstName("Charlie");
		s.setLastName("Brown");
		dao.createStudent(s);

		s = new Student();
		s.setGradYear(2019);
		s.setScholarship(0);
		s.setFirstName("Dan");
		s.setLastName("Craig");
		dao.createStudent(s);

		s = new Student();
		s.setGradYear(2022);
		s.setScholarship(11000);
		s.setFirstName("Edward");
		s.setLastName("Scissorhands");
		dao.createStudent(s);

		s = new Student();
		s.setGradYear(2018);
		s.setScholarship(0);
		s.setFirstName("Frank");
		s.setLastName("Herbert");
		dao.createStudent(s);

		s = new Student();
		s.setGradYear(2023);
		s.setScholarship(10000);
		s.setFirstName("Gregory");
		s.setLastName("Peck");
		dao.createStudent(s);

	}

	/**
	 * Test to create courses.
	 */
	@Test
	void createCourse(){
		Course c = new Course();
		c.setLabel("CS1234");
		dao.createCourse(c);
		c = new Course();
		c.setLabel("CS2345");
		dao.createCourse(c);
		c = new Course();
		c.setLabel("CS3456");
		dao.createCourse(c);
		c = new Course();
		c.setLabel("CS4567");
		dao.createCourse(c);
	}

	/**
	 * Test to create sections.
	 */
	@Test
	void createSection() {
		Section section = new Section();
		section.setTitle("SEC4321");
		section.setSeats(50);
		dao.createSection(section);

		section = new Section();
		section.setTitle("SEC5432");
		section.setSeats(50);
		dao.createSection(section);

		section = new Section();
		section.setTitle("SEC6543");
		section.setSeats(50);
		dao.createSection(section);

		section = new Section();
		section.setTitle("SEC7654");
		section.setSeats(50);
		dao.createSection(section);
	}

	/**
	 * Test to assign author to course.
	 */
	@Test
	void setAuthorForCourse() {
		dao.setAuthorForCourse("Alan","CS1234");
		dao.setAuthorForCourse("Alan","CS2345");
		dao.setAuthorForCourse("Ada","CS3456");
		dao.setAuthorForCourse("Ada","CS4567");
	}

	/**
	 * Test to map/assign sections to course.
	 */
	@Test
	void addSectionForCourse(){
		dao.addSectionToCourse("SEC4321", "CS1234");
		dao.addSectionToCourse("SEC5432", "CS1234");
		dao.addSectionToCourse("SEC6543", "CS2345");
		dao.addSectionToCourse("SEC7654", "CS3456");
	}

	/**
	 * Test to create enrollments for given students into given sections.
	 */
	@Test
	void enrollStudentInSection(){
		dao.enrollStudentInSection("Alice","SEC4321");
		dao.enrollStudentInSection("Alice","SEC5432");
		dao.enrollStudentInSection("Bob","SEC5432");
		dao.enrollStudentInSection("Charlie","SEC6543");
	}

	/**
	 * Test total number of users/persons currently in the database.
	 * If the number does not assert, the string message is shown.
	 */
	@Test
	void totalUsers(){
		//System.out.println(dao.findAllUsers().size());
		List<Person> result = dao.findAllUsers();
		Assert.isTrue(result.size()==9,"total users are " + result.size());
	}

	/**
	 * Test total number of faculties currently in the database.
	 * If the number does not assert, the string message is shown.
	 */
	@Test
	void totalFacultys(){
		List<Faculty> result = dao.findAllFaculty();
		Assert.isTrue(result.size()==2,"total Faculty are " + result.size());
	}

	/**
	 * Test total number of students currently in the database.
	 * If the number does not assert, the string message is shown.
	 */
	@Test
	void totalStudents(){
		List<Student> result = dao.findAllStudents();
		Assert.isTrue(result.size()==7,"total Student are " + result.size());
	}

	/**
	 * Test total number of Courses currently in the database.
	 * If the number does not assert, the string message is shown.
	 */
	@Test
	void totalCourses(){
		List<Course> result = dao.findAllCourses();
		Assert.isTrue(result.size()==4,"total courses are " + result.size());
	}

	/**
	 * Test total number of sections currently in the database.
	 * If the number does not assert, the string message is shown.
	 */
	@Test
	void totalSections(){
		List<Section> result = dao.findAllSections();
		Assert.isTrue(result.size()==4,"total sections are " + result.size());
	}

	@Test
	void findCoursesForFaculty() {
		Faculty f = new Faculty();
		f.setFirstName("Alan");
		List<Course> courses = dao.findCoursesForAuthor(f);
		Assert.isTrue(courses.size()==2,"Number of course for given faculty is not accurate -" + courses.size());

		f.setFirstName("Ada");
		courses = dao.findCoursesForAuthor(f);
		Assert.isTrue(courses.size()==2,"Number of course for given faculty is not accurate - " + courses.size());
	}

	@Test
	void findSectionForCourse(){
		Course course = new Course();
		course.setLabel("CS1234");
		List<Section> sections = dao.findSectionForCourse(course);
		Assert.isTrue(sections.size()==2,"Number of sections is not accurate - " + sections.size());

		course.setLabel("CS2345");
		sections = dao.findSectionForCourse(course);
		Assert.isTrue(sections.size()==1,"Number of sections is not accurate - " + sections.size());

		course.setLabel("CS3456");
		sections = dao.findSectionForCourse(course);
		Assert.isTrue(sections.size()==1,"Number of sections is not accurate - " + sections.size());

		course.setLabel("CS4567");
		sections = dao.findSectionForCourse(course);
		Assert.isTrue(sections.size()==0,"Number of sections is not accurate - " + sections.size());
	}

	@Test
	void findStudentsInSection() {
		Section section = new Section();
		section.setTitle("SEC4321");
		List<Student> students = dao.findStudentsInSection(section);
		Assert.isTrue(students.size() == 1, "students in given section are " + students.size());
		//System.out.println(students.size());

		section.setTitle("SEC5432");
		students = dao.findStudentsInSection(section);
		Assert.isTrue(students.size() == 2, "students in given section are " + students.size());
		//System.out.println(students.size());

		section.setTitle("SEC7654");
		students = dao.findStudentsInSection(section);
		Assert.isTrue(students.size() == 0, "students in given section are " + students.size());
		//System.out.println(students.size());

		section.setTitle("SEC6543");
		students = dao.findStudentsInSection(section);
		Assert.isTrue(students.size() == 1, "students in given section are " + students.size());

	}
	@Test
	void findSectionsForStudent(){
		Student s = new Student();
		s.setFirstName("Alice");
		List<Section> sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 2, "sections for given student are " + sections.size());

		s.setFirstName("Bob");
		sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 1, "sections for given student are " + sections.size());

		s.setFirstName("Charlie");
		sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 1, "sections for given student are " + sections.size());

		s.setFirstName("Dan");
		sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 0, "sections for given student are " + sections.size());

		s.setFirstName("Edward");
		sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 0, "sections for given student are " + sections.size());

		s.setFirstName("Frank");
		sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 0, "sections for given student are " + sections.size());

		s.setFirstName("Gregory");
		sections = dao.findSectionsForStudent(s);
		Assert.isTrue(sections.size() == 0, "sections for given student are " + sections.size());
	}

	@Test
	void sectionSeats(){
		Section section = new Section();
		section.setTitle("SEC4321");
		Assert.isTrue(dao.findSeatsRemainingForSection(section)==49, "Seats Remaining are incorrect");

		section.setTitle("SEC5432");
		Assert.isTrue(dao.findSeatsRemainingForSection(section)==48, "Seats Remaining are incorrect");

		section.setTitle("SEC6543");
		Assert.isTrue(dao.findSeatsRemainingForSection(section)==49, "Seats Remaining are incorrect");

		section.setTitle("SEC7654");
		Assert.isTrue(dao.findSeatsRemainingForSection(section)==50, "Seats Remaining are incorrect");

	}

}
