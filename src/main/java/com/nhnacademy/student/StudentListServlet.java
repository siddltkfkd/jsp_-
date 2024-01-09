package com.nhnacademy.student;

import com.nhnacademy.student.repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "student list servlet", urlPatterns = "/student/list")
public class StudentListServlet extends HttpServlet {
	private StudentRepository studentRepository;

	@Override
	public void init(ServletConfig config) {
		studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<Student> studentList = studentRepository.getStudents();
		request.setAttribute("studentList", studentList);
		RequestDispatcher rd = request.getRequestDispatcher("/student/list.jsp");
		rd.forward(request, response);
	}
}
