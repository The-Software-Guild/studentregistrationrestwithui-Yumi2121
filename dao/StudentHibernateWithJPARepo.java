package com.softra.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.softra.model.Student;


@Repository(value="hiberstujpa")
public class StudentHibernateWithJPARepo implements IDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public Student save(Student student) {
		em.merge(student);
		return student;
	}

	@Override
	@Transactional
	public Student update(Student student) {
		em.merge(student);
		return student;
	}

	@Override
	public List<Student> findAll() {	
		List<Student> studentList = em.createQuery("select a from Student a", Student.class).getResultList();
		if (studentList == null) {
			System.out.println("No student found . ");
		} else {
			studentList.forEach(System.out::println);
		}
		return studentList;			
	}

	@Override
	@Transactional
	public Student deleteById(int id) {
		Student student = findById(id);
		if (student != null) {
			em.remove(student);
		}
		return student;
	}

	@Override
	public Student findById(int id) {
		Student student = em.find(Student.class, id);
		return student;
	}

	@Override
	public List<Student> findByName(String name) {
		
		List<Student> studentList = em.createQuery("select a from Student a where a.name= '" + name + "'", Student.class).getResultList();
		if (studentList == null) {
			System.out.println("No student found . ");
		} else {
			studentList.forEach(System.out::println);
		}
		return studentList;			
	}
	

}
