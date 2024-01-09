package com.nhnacademy.student.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
		filterName = "character encoding filter",
		urlPatterns = "/*",
		initParams = {
				@WebInitParam(name = "encoding", value = "UTF-8")
		}
)
public class CharacterEncodingFilter implements Filter {
	private String encoding;
	@Override
	public void init(FilterConfig filterConfig){
		this.encoding = filterConfig.getInitParameter("encoding");
	}
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		servletRequest.setCharacterEncoding(this.encoding);
		servletResponse.setCharacterEncoding(this.encoding);
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
