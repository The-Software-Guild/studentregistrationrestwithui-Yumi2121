package com.softra.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.softra.model.Student;

@Repository
@Profile("dev")
public class StudentMemoryRepository implements IDao {
	
	private static List<Student> studentList = new ArrayList<>();
	
	static {
		studentList.add(new Student("Simon", 10, "11111", "Melbourne"));
		studentList.add(new Student("Bob", 9, "22222", "Sydney"));
		
	}
	
	public StudentMemoryRepository() {};
	
	@Override
	public Student save(Student student) {
		studentList.add(student);
		return student;
	}

	@Override
	public Student update(Student student) {
		boolean found = false;
		
		for (int i=0; i < studentList.size(); i++) {
			var stu = studentList.get(i);
			if (stu.getId()== student.getId()) {
				studentList.set(i, student);
				found = true;
				return student;
			}
		}
		if (found = false) {
			System.out.println("There is no matching student id can be found! ");
					
		}
		return null;
			
	}

	@Override
	public List<Student> findAll() {
		
		return studentList;
	}

	@Override
	public Student deleteById(int id) {
		
		Iterator<Student> iter = studentList.iterator();
		while(iter.hasNext()) {
			Student student = iter.next();
			if(student.getId() == id) {
				iter.remove();
				return student;
			}
		}
		return null;
	}

	@Override
	public Student findById(int id) {
		Student stu=studentList.stream().filter(a -> a.getId() == id).findAny().orElse(null);
//		Optional<Student> opt = Optional.ofNullable(stu);
		return stu;
	}

	@Override
	public List<Student> findByName(String name) {
		List<Student> studentsByName = new ArrayList<>();
		
		for(Student s : studentList) {
			if(name.equals(s.getName())) {
				studentsByName.add(s);
			}
		}
		return studentsByName;
	}

}
