package com.ant.validation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;


/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author Ant
 * @since 2020/6/17 11:35 下午
 */
@Configuration
@ComponentScan("com.ant")
public class SpringBeansValidatorDemo {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

		context.register(SpringBeansValidatorDemo.class);
		context.refresh();

		Validator validator = context.getBean(Validator.class);
		System.out.println(validator instanceof LocalValidatorFactoryBean);

		UserProcessor processor = context.getBean(UserProcessor.class);
		processor.processUser(new User());

		context.close();

	}

	@Component
	@Validated
	static class UserProcessor{
		public void processUser(@Valid User user) {
			System.out.println(user);
		}
	}


	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean () {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
		processor.setValidator(localValidatorFactoryBean());
		return processor;
	}

	static class User {

		@NotNull
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
