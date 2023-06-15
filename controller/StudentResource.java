package com.softra.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softra.exception.StudentNotFoundException;
import com.softra.model.Student;
import com.softra.service.IService;


@RestController
@CrossOrigin(origins = "*")
public class StudentResource {
	
	@Autowired
	private IService service;
	
	@GetMapping(path="/")
	public String showIndexPage() {
		return "index";
	}
	

	// GET /students
	@GetMapping(path="/students")
	public @ResponseBody List<Student> fetchAllStudents() {
		List<Student> students = service.getAllStudents();
		return students;
	}
	
	// GET /students/id
	@GetMapping(path="/students/{id}")
	public Student fetchStudent(@PathVariable int id) throws StudentNotFoundException {
		Student student = service.findById(id);
		if(student == null) {
			throw new StudentNotFoundException("student with id: " + id + " not found!");
		}
		return student;
	}
	
	// POST /students
	@PostMapping(path = "/students")
	public Student addStudent(@Valid @RequestBody Student student) {
		Student stu = service.addStudent(student);
		return stu;
	}
	
	// DELETE /students/id
	@DeleteMapping(path="/students/{id}")
	public void deleteStudent(@PathVariable(value="id") Integer id) throws StudentNotFoundException {
		Student stu = service.findById(id);
		if(stu == null) {
			throw new StudentNotFoundException("student with id: " + id + " not found!");
		}
		service.deleteById(id);
	}
	

	@PutMapping(path="/students/{id}")
	public Student editStudent(@PathVariable(value="id") Integer id, @Valid @RequestBody Student student) throws StudentNotFoundException {
		Student stu = service.findById(id);
		if(stu == null) {
			throw new StudentNotFoundException("student with id: " + id + " not found!");
		}
	
		stu.setName(student.getName());
		stu.setAge(student.getAge());
		stu.setMobile(student.getMobile());
		stu.setAddress(student.getAddress());
		service.editStudent(student);
		return stu;
		
	}
	
	@GetMapping(path = "/students/name/{name}")
	public List<Student> fetchStudentByName(@PathVariable String name) throws StudentNotFoundException {
		List<Student> students = service.findByName(name);
		if(students == null) {
			throw new StudentNotFoundException("student with name: " + name + " not found!");
		}
		return students;
	}
	
}
