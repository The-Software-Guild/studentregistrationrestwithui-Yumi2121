package com.softra.dao;

import java.util.List;
import java.util.Optional;

import com.softra.model.Student;

public interface IDao {
	public Student save(Student student);
	public Student update(Student student);
	public List<Student> findAll();
	public Student deleteById(int id);
	
	public Student findById(int id);
	public List<Student> findByName(String name);
	
}
