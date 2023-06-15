package com.softra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.softra.dao.IDao;
import com.softra.model.Student;

@Service
public class StudentService implements IService {
	
	@Autowired
	@Qualifier(value="hiberstujpa")
//	@Qualifier(value="stujpa")
	private IDao dao;

	@Override
	public Student addStudent(Student student) {
		Student stu = dao.save(student);
		return stu;
	}

	@Override
	public Student editStudent(Student student) {
		Student stu = dao.update(student);
		return stu;
	}

	@Override
	public Student findById(int id) {
		Student student = dao.findById(id);
	
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students = dao.findAll();
		return students;
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);

	}

	@Override
	public List<Student> findByName(String name) {
		
		return dao.findByName(name);
		
	}

}
