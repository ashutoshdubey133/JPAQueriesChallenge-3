package com.codingninjas.jpaqueries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codingninjas.jpaqueries.entities.Course;
import com.codingninjas.jpaqueries.entities.CourseMarksDTO;
import com.codingninjas.jpaqueries.entities.Student;
import com.codingninjas.jpaqueries.services.MainService;

@RestController
public class MainController {
	
	@Autowired
	MainService service;
	
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {
		return service.getStudentById(id);
	}
	
	@PostMapping("/student")
	public void saveStudent(@RequestBody Student student) {
		System.out.println("student name is "+ student.getName());
		service.setStudent(student);
	}
	
	@PostMapping("/student/{id}/courses_marks")
	public void saveCoursesWithMarks(@RequestBody List<CourseMarksDTO> courses,@PathVariable int id) {
		service.setCourses(courses,id);
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return service.getAllStudents();
	}
	
	@GetMapping("/students/{course}")
	public List<Student> getAllStudentsByCourse(@PathVariable String course){
		return service.getAllStudentsByCourse(course);
	}
	
	@GetMapping("/students/{id}/courses")
	public List<String> getCoursesNameByStudentID(@PathVariable int id){
		return service.getCoursesNameByStudentId(id);
	}
	
	@GetMapping("/student/{id}/averageGrade")
	public double getAverageGradesOfAllStudents(@PathVariable int id) {
		return service.getAverageGradesOfStudent(id);
	}
	
	
}
