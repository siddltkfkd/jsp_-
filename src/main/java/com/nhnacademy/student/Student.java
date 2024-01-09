package com.nhnacademy.student;

import java.time.LocalDateTime;
import java.util.Date;

public class Student {
	private String id;
	private String name;
	private  Gender gender;
	private  int age;
	private Date createdAt;

	public Student(String id, String name, Gender gender, int age){
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.createdAt = new Date();
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
	public Date getCreatedAt(){
		return createdAt;
	}

	@Override
	public String toString(){
		return "id : " + id + ", name : " + name + ", gender : " + gender + ", age : " + age + ", createdAt : " + createdAt;
	}
}
