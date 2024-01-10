package com.nhnacademy.student.controller;

import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentUpdateController implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		StudentRepository studentRepository = (StudentRepository) request.getServletContext().getAttribute("studentRepository");
		String id = request.getParameter("id");

		Student student = studentRepository.getStudentById(id);
		request.setAttribute("student", student);

		return "/student/register.jsp";
	}
}
