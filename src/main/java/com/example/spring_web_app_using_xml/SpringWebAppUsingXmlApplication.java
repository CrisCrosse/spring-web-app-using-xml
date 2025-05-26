package com.example.spring_web_app_using_xml;

import com.example.spring_web_app_using_xml.filter.TopSecretFilter;
import com.example.spring_web_app_using_xml.userrealm.MyUserRealm;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.security.BasicAuthenticator;
import org.mortbay.jetty.security.Constraint;
import org.mortbay.jetty.security.ConstraintMapping;
import org.mortbay.jetty.security.SecurityHandler;
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
		Server server = new Server(8080);
		Context jettyContext = new Context(server, "/api/v1", true, true);

//		instantiate and add a servlet matching /hello requests to the server context
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

		// Add basic authentication and only authorise the hello endpoint to "Cool" users
		SecurityHandler securityHandler = jettyContext.getSecurityHandler();
		securityHandler.setUserRealm(new MyUserRealm());
		securityHandler.setAuthenticator(new BasicAuthenticator());
		Constraint constraint = new Constraint("sayHiToCoolUsersOnly", "CoolUserRole");
		constraint.setAuthenticate(true);
		ConstraintMapping constraintMapping = new ConstraintMapping();
		constraintMapping.setConstraint(constraint);
		constraintMapping.setPathSpec("/hello");
		ConstraintMapping[] constraintMappings = new ConstraintMapping[] {constraintMapping};
		securityHandler.setConstraintMappings(constraintMappings);

		server.start();
		System.out.println("Server started at http://localhost:8080/api/v1");
		server.join();
	}
}
