package com.example.spring_web_app_using_xml;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.GenericWebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpringWebAppUsingXmlApplication {

	public static void main(String[] args) throws Exception {


		// Create a Spring application context
		GenericWebApplicationContext context = new GenericWebApplicationContext();

		HttpServlet helloServlet = new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.getWriter().write("Hello!");
			}
		};

		// Register the handler in the context
		context.registerBean("helloServlet", HttpServlet.class, () -> helloServlet);
		context.refresh();

		// Start an embedded server (e.g., Jetty or Tomcat) and register the DispatcherServlet
		Server server = new Server(8080);
		Context jettyContext = new Context(server, "/", Context.SESSIONS);

		ServletHolder servletHolder = new ServletHolder();
		servletHolder.setServlet(helloServlet);
		jettyContext.addServlet(servletHolder, "/*");

		server.start();
		System.out.println("Server started at http://localhost:8080/hello");
		server.join();
	}
}
