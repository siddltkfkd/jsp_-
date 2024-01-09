package com.nhnacademy.student.repository;

import com.nhnacademy.student.Student;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

public class MapStudentRepository implements StudentRepository {
	private Map<String, Student> studentMap = new ConcurrentHashMap<>();
	@Override
	public void save(Student student) {
		studentMap.put(student.getId(), student);
	}

	@Override
	public void update(Student student) {
		studentMap.replace(student.getId(), student);
	}

	@Override
	public void deleteById(String id) {
		studentMap.remove(id);
	}

	@Override
	public Student getStudentById(String id) {
		return studentMap.get(id);
	}

	@Override
	public List<Student> getStudents() {
		List<Student> studentList= new ArrayList<Student>();
		for (Student student : studentMap.values()){
			studentList.add(student);
		}
		return studentList;
	}

	@Override
	public boolean existById(String id) {
		return studentMap.containsKey(id);
	}
}
