package com.nhnacademy.student.controller;

import com.nhnacademy.student.Gender;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class StudentUpdateFormController implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
		String id = request.getParameter("id");
		String name = request.getParameter("name");

		Gender gender = null;
		if(Objects.nonNull(request.getParameter("gender"))){
			gender = Gender.valueOf(request.getParameter("gender"));
		}

		Integer age = null;
		if(Objects.nonNull(request.getParameter("age"))){
			age = Integer.parseInt(request.getParameter("age"));
		}

		if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
			log.error("id : {}, name : {}, gender : {}, age : {}", id, name, gender, age);
			throw new RuntimeException("id,name,gender,age 확인해주세요!");
		}

		Student student = new Student(id, name, gender, age);
		studentRepository.update(student);
		return "redirect:/student/view.do?id="+id;
	}
}
