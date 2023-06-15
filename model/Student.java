package com.softra.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
// (name="\"Student\"")
@Table(name="Student")
public class Student {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name ="studentId")
	private int id;
	
	@Column(name ="name")
	@Size(min=3,message="Name must have atleast 3 chars")
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Column(name ="age")
	private int age;
	
	@Column(name ="mobile")
	private String mobile;
	
	@Column(name ="address")
	private String address;
	
	public Student() {
		
	}
	
	public Student(String name, int age, String mobile, String address) {
		super();
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.address = address;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
