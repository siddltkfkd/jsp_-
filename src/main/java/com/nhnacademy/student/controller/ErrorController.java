package com.nhnacademy.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.nhnacademy.student.RequestDispatcher.*;

public class ErrorController implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("status_code", request.getAttribute(ERROR_STATUS_CODE));
		request.setAttribute("exception_type", request.getAttribute(ERROR_EXCEPTION_TYPE));
		request.setAttribute("message", request.getAttribute(ERROR_MESSAGE));
		request.setAttribute("exception", request.getAttribute(ERROR_EXCEPTION));
		request.setAttribute("request_url", request.getAttribute(ERROR_REQUEST_URI));

		return "/error.jsp";
	}
}
