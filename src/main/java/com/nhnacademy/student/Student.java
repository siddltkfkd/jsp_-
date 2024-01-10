package com.nhnacademy.student;

import java.time.LocalDateTime;
import java.util.Date;

public class Student {
	private String id;
	private String name;
	private  Gender gender;
	private  int age;
	private LocalDateTime createdAt;

	public Student(){}

	public Student(String id, String name, Gender gender, int age){
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.createdAt = LocalDateTime.now();
	}

	public String getId(){
		return id;
	}
	public String getName() {
		return name;
	}
	public Gender getGender(){
		return gender;
	}
	public int getAge(){
		return age;
	}
	public LocalDateTime getCreatedAt(){
		return createdAt;
	}
}
