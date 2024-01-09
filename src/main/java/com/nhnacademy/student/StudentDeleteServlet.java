package com.nhnacademy.student;

import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

	private StudentRepository studentRepository;

	@Override
	public void init(ServletConfig config) throws ServletException {
		studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get parameter  : id , id가 존재하지 않을경우 throw new RuntimeException("...")
		String id = request.getParameter("id");
		if (Objects.isNull(id)){
			throw new RuntimeException("id가 null입니다.");
		}
		studentRepository.deleteById(id);
		// /student/list <-- redirect
		response.sendRedirect("/student/list");
	}
}