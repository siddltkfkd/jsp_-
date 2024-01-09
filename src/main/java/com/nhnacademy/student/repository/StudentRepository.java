package com.nhnacademy.student.repository;

import com.nhnacademy.student.Student;

import java.util.List;

public interface StudentRepository {
	void save(Student student); // 등록
	void update(Student student); // 수정
	void deleteById(String id); // 삭제
	Student getStudentById(String id); //조회
	List<Student> getStudents();
	boolean existById(String id);
}
