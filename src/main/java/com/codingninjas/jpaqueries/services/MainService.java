package com.codingninjas.jpaqueries.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingninjas.jpaqueries.entities.Course;
import com.codingninjas.jpaqueries.entities.CourseMarksDTO;
import com.codingninjas.jpaqueries.entities.Student;
import com.codingninjas.jpaqueries.repository.CourseRepository;
import com.codingninjas.jpaqueries.repository.GradeRepository;
import com.codingninjas.jpaqueries.repository.StudentRepository;
import com.codingninjas.jpaqueries.entities.Grade;

@Service
public class MainService {
	
	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	GradeRepository gradeRepository;
	
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	public void setStudent(Student student) {
		studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Course getCourseById(int id) {
		return courseRepository.findById(id).get();
	}
	
	public void setCourses(List<CourseMarksDTO> courses, int id) {
		Student student = this.getStudentById(id);
		
		for(CourseMarksDTO courseMarks: courses) {
			Course course = this.getCourseByName(courseMarks.getCourse()).orElse(new Course());
			Grade grade = new Grade();
			
			grade.setStudent(student);
			grade.setMarks(courseMarks.getMarks());
			grade.setCourse(course);
			
			course.setName(courseMarks.getCourse());
			course.getStudents().add(student);
			
			student.getCourses().add(course);
			student.getGrades().add(grade);
			
			gradeRepository.save(grade);
			courseRepository.save(course);
			studentRepository.save(student);
		}
	}

	private Optional<Course> getCourseByName(String course) {
		return courseRepository.findByName(course);
	}

	public List<Student> getAllStudentsByCourse(String course) {
		return studentRepository.findAllByCourses_name(course);
	}

	public List<String> getCoursesNameByStudentId(int id) {
		return courseRepository.getCoursesByStudentId(this.getStudentById(id));
	}

	public double getAverageGradesOfStudent(int id) {
		return gradeRepository.getAverageGradesOfStudent(id);
	}
	
}
