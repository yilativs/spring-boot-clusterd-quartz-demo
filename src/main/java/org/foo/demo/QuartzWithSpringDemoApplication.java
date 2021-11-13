package org.foo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class QuartzWithSpringDemoApplication {

	private static ConfigurableApplicationContext context;

	public static ConfigurableApplicationContext getContext() {
		return context;
	}

	public static void main(String[] args) {
		context = SpringApplication.run(QuartzWithSpringDemoApplication.class, args);
	}

}
