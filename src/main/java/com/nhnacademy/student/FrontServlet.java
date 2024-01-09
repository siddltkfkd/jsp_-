package com.nhnacademy.student;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
	private static final String REDIRECT_PREFIX="redirect";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try{
			//실제 요청 처리할 servlet을 결정
			String servletPath = resolveServlet(request.getServletPath());
			log.info("path : {}", servletPath);
			RequestDispatcher rd;

			// 실제 요청을 처리할 서블릿으로 요청 전달
			if (Objects.nonNull(servletPath)){
				rd = request.getRequestDispatcher(servletPath);
				rd.include(request, response);
			}

			//실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
			String view = (String) request.getAttribute("view");
			log.info("view : {}", view);
			if (view.startsWith(REDIRECT_PREFIX)) {
				String redirect_url = view.substring(REDIRECT_PREFIX.length()+1);
				log.info("redirect-url : {}", redirect_url);
				response.sendRedirect(redirect_url);

			} else {
				rd = request.getRequestDispatcher(view);
				rd.include(request, response);


			}
		}catch(Exception ex){
			log.error("", ex);
			request.setAttribute("exception", ex);
			RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.include(request, response);
		}
	}

	private String resolveServlet(String servletPath){
		String processingServlet = null;
		if(servletPath.contains("/student/list.do")){
			processingServlet = servletPath.replace("/student/list.do", "/student/list");
		} else if(servletPath.contains("/student/view.do")) {
			processingServlet = servletPath.replace("/student/view.do", "/student/view");
		} else if(servletPath.contains("student/delete.do")) {
			processingServlet = servletPath.replace("/student/delete.do", "/student/delete");
		} else if(servletPath.contains("student/register.do")) {
			processingServlet = servletPath.replace("/student/register.do", "/student/register");
		} else if(servletPath.contains("student/update.do")) {
			processingServlet = servletPath.replace("/student/update.do", "/student/update");
		} else{
			log.info("servlet Path : {}", servletPath);
		}
		return processingServlet;
	}

}