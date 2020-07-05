package com.ant.anntation.enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * HelloWorld Configuration Class
 * </p>
 *
 * @author Ant
 * @since 2020/7/4 6:38 下午
 */
@Configuration
public class HelloWorldConfiguration {
	@Bean
	public String helloWorld() {
		return "HelloWorld";
	}
}
