package com.nhnacademy.student;

import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
	private StudentRepository studentRepository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if (Objects.isNull(id)){
			throw new RuntimeException("id가 null 입니다.");
		}
		Student student = studentRepository.getStudentById(id);

		request.setAttribute("student", student);

		RequestDispatcher rd = request.getRequestDispatcher("/student/view.jsp");
		rd.forward(request, response);

	}

}