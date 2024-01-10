package com.nhnacademy.student.controller;

import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class StudentViewController implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
		String id = request.getParameter("id");
		if (Objects.isNull(id)){
			throw new RuntimeException("id가 null 입니다.");
		}
		Student student = studentRepository.getStudentById(id);

		request.setAttribute("student", student);
		return "/student/view.jsp";
	}
}
