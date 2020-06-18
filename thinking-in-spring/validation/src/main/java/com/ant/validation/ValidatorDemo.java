package com.ant.validation;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

import static com.ant.validation.ErrorsMessageDemo.createMessageSource;

/**
 * <p>
 * 自定义 Spring {@link Validator} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/6/15 8:24 上午
 */
public class ValidatorDemo {

	public static void main(String[] args) {
		// 1. 创建 Validator
		UserValidator validator = new UserValidator();
		// 2. 判断是否支持目标对象的类型
		User user = new User();
		System.out.println("user 对象是否被 Uservalidator 支持校验" + validator.supports(User.class));
		// 3. 创建 Errors 对象
		Errors errors = new BeanPropertyBindingResult(user, "user");

		validator.validate(user, errors);

		// 获取 MessageSource 对象
		MessageSource messageSource = createMessageSource();

		for (ObjectError allError : errors.getAllErrors()) {
			final String message = messageSource.getMessage(allError.getCode(), allError.getArguments(), Locale.getDefault());
			System.out.println(message);
		}

	}

	static class UserValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return User.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			User user = (User) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
			String userName = user.getName();
		}
	}
}
