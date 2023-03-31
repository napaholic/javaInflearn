package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);

		//resp.setHeader("Content-Type", "text/plain;charset=utf-8");
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("my-header", "hello");

		//[header 편의 메서드]
//		content(resp);

		//[cookie 편의 메서드]
//		cookie(resp);

		//[redirect 편의 메서드]
		redirect(resp);

		PrintWriter writer = resp.getWriter();
		writer.println("안녕하세요response-header");

	}

	private void content(HttpServletResponse resp) {
		//Content-Type: text/plain;charset=utf-8
		// Content-Length: 2
		// response.setHeader("Content-Type", "text/plain;charset=utf-8");
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		//response.setContentLength(2);
		// (생략시 자동 생성)
	}

	private void cookie(HttpServletResponse resp) {
		// Set-Cookie: myCookie=good; Max-Age=600;
		// Set-Cookie: myCookie=good; Max-Age=600; SameSite=Strict
		// Set-Cookie: myCookie=good; Max-Age=600; SameSite=Lax
		// Set-Cookie: myCookie=good; Max-Age=600; SameSite=None; Secure
		Cookie cookie = new Cookie("myCookie", "good");
		cookie.setMaxAge(600);
		resp.addCookie(cookie);
	}

	private void redirect(HttpServletResponse resp) throws IOException {
		//Status Code 302
		//Location: /basic/hello-form.html
//		resp.setStatus(HttpServletResponse.SC_FOUND); // 302
//		resp.setHeader("Location", "/basic/hello-form.html");
		resp.sendRedirect("/basic/hello-form.html");
	}
}
