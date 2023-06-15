package com.softra.service;

import java.util.List;

import com.softra.model.Student;

public interface IService {
	public Student addStudent(Student student);
	public Student editStudent(Student student);
	public Student findById(int id);
	public List<Student> findByName(String name);
	public List<Student> getAllStudents();
	public void deleteById(int id);
}
