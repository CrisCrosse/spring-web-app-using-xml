package com.example.spring_web_app_using_xml;

import com.example.spring_web_app_using_xml.controller.GreetingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.thymeleaf.standard.expression.GreaterThanExpression;

//@SpringBootApplication
public class SpringWebAppUsingXmlApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringWebAppUsingXmlApplication.class, args);
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		GreetingController greetingController = (GreetingController) context.getBean("GreetingController");
		greetingController.sayHello();
	}

}
