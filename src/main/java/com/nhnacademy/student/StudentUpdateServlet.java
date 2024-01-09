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
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
	private StudentRepository studentRepository;

	@Override
	public void init(ServletConfig config) {
		studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("get - update");
		String id = request.getParameter("id");

		Student student = studentRepository.getStudentById(id);
		request.setAttribute("student", student);

//		RequestDispatcher rd = request.getRequestDispatcher("/student/register.jsp");
//		rd.forward(request, response);

		request.setAttribute("view", "/student/register.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("post - update");
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
//		response.sendRedirect("/student/view?id="+id);
		request.setAttribute("view", "redirect:/student/view.do?id="+id);
	}
}