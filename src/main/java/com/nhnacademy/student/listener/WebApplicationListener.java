package com.nhnacademy.student.listener;

import com.nhnacademy.student.Gender;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.Student;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class WebApplicationListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		StudentRepository studentRepository = new MapStudentRepository();

		for (int i=0; i<10; i++) {
			Student student = new Student(String.valueOf(i), "학생 "+i, Gender.M, ((int)(Math.random()*3 + 12)));
			studentRepository.save(student);
		}
		log.info("학생 객체 생성 완료");

		// ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
		servletContext.setAttribute("studentRepository", studentRepository);
	}
}
