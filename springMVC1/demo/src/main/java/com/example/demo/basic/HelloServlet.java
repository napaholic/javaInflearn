package com.example.demo.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello Servlet");
		System.out.println("request = " + request);
		System.out.println("response = " + response);
		// request.getParameter() 는 get, post 모두 지원
		// get 방식의 쿼리 파라미터를 꺼낼 때 사용
		// post 방식의 form submit 데이터를 꺼낼 때 사용
		// http://localhost:8080/hello?username=hello

		String username = request.getParameter("username");
		System.out.println("username = " + username);
		// response.getWriter().write("hello " + username);
		// username=hi
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("hello " + username);

	}
}
