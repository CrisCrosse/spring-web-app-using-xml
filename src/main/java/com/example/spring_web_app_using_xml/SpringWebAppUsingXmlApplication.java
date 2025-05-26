package com.example.spring_web_app_using_xml;

import com.example.spring_web_app_using_xml.filter.TopSecretFilter;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.FilterHolder;
import org.mortbay.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SpringWebAppUsingXmlApplication {

	public static void main(String[] args) throws Exception {
		// Start a Jetty server
		// the Jetty server is a request handler which has a thread pool and aggregates HTTP connectors + handlers
		// ie. it reads requests and delegates to the correct handler via the thread pool
		Server server = new Server(8080);
		Context jettyContext = new Context(server, "/api/v1", true, true);

//		instantiate a servlet with an anonymous inner class
		HttpServlet helloServlet = new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.setHeader("Content-Type", "text/plain");
				resp.getWriter().write("Hello!");
			}
		};
		ServletHolder servletHolder = new ServletHolder();
		servletHolder.setServlet(helloServlet);
		jettyContext.addServlet(servletHolder, "/hello");

		// Add a custom filter to check for the presence of a specific HTTP header
		TopSecretFilter myHeaderFilter = new TopSecretFilter();
		jettyContext.addFilter(new FilterHolder(myHeaderFilter), "/*", Handler.REQUEST);

		server.start();
		System.out.println("Server started at http://localhost:8080/api/v1");
		server.join();
	}
}
