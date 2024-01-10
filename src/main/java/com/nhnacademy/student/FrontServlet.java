package com.nhnacademy.student;

import com.nhnacademy.student.controller.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nhnacademy.student.RequestDispatcher.*;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
	private static final String REDIRECT_PREFIX="redirect";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		try {
			// 실제 로직을 처리할 Command(Controller) 결정, String view = command.execute() ...
			Command command = resolveCommand(request.getServletPath(), request.getMethod());
			String view = command.execute(request, response);

			if (view.startsWith(REDIRECT_PREFIX)) {
				log.info("redirect-url : {}", view.substring(REDIRECT_PREFIX.length() + 1));
				response.sendRedirect(view.substring(REDIRECT_PREFIX.length()+1));
			} else {
				// redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 처리.
				javax.servlet.RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.include(request, response);
			}
		}catch (Exception ex){
			//공통 error 처리
			request.setAttribute("status_code", request.getAttribute(ERROR_STATUS_CODE));
			request.setAttribute("exception_type", request.getAttribute(ERROR_EXCEPTION_TYPE));
			request.setAttribute("message", request.getAttribute(ERROR_MESSAGE));
			request.setAttribute("exception", request.getAttribute(ERROR_EXCEPTION));
			request.setAttribute("request_uri", request.getAttribute(ERROR_REQUEST_URI));
			log.error("status_code:{}", request.getAttribute(ERROR_STATUS_CODE));
			javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
			rd.forward(request,response);
		}

	}

	private Command resolveCommand(String servletPath, String method){
		Command command = null;

		if(servletPath.contains("/student/delete.do") && method.equalsIgnoreCase("POST")){
			command = new StudentDeleteController();
		} else if(servletPath.contains("/student/list.do") && method.equalsIgnoreCase("GET")){
			command = new StudentListController();
		} else if (servletPath.contains("/student/register.do") && method.equalsIgnoreCase("GET")){
			command = new StudentRegisterController();
		} else if (servletPath.contains("/student/register.do") && method.equalsIgnoreCase("POST")){
			command = new StudentRegisterFormController();
		} else if (servletPath.contains("/student/update.do") && method.equalsIgnoreCase("GET")){
			command = new StudentUpdateController();
		} else if (servletPath.contains("/student/update.do") && method.equalsIgnoreCase("POST")){
			command = new StudentUpdateFormController();
		} else if (servletPath.contains("/student/view.do") && method.equalsIgnoreCase("GET")){
			command = new StudentViewController();
		} else if (servletPath.contains("/error")){
			command = new ErrorController();
		}

		return command;
	}

}