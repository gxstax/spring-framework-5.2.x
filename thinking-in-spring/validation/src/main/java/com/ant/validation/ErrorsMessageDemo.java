package com.ant.validation;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * <p>
 * 错误文案示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/15 7:46 上午
 * @see Errors
 */
public class ErrorsMessageDemo {
	public static void main(String[] args) {
		// 0. 创建 User 对象
		User user = new User();
		user.setName("马以");

		// 1. 选择 Errors -> BeanPropertyBindingResult
		Errors errors = new BeanPropertyBindingResult(user, "user");
		// 2. 调用 reject 或 rejectValue
		// reject 生成 ObjectError
		// reject 生成 FieldError
		errors.reject("user.properties.not.null");
		errors.rejectValue("name", "name.required");

		// 3. 获取 Errors 中的 ObjectError 和 FieldError
		// FieldError is ObjectError
		List<ObjectError> globalErrors = errors.getGlobalErrors();
		List<FieldError> fieldErrors = errors.getFieldErrors();
		List<ObjectError> allErrors = errors.getAllErrors();

		// 4. 通过 ObjectError 和 FieldError 中的code 和 args 关联 MessageSource实现
		MessageSource messageSource = createMessageSource();

		for (ObjectError allError : allErrors) {
			final String message = messageSource.getMessage(allError.getCode(), allError.getArguments(), Locale.getDefault());
			System.out.println(message);
		}
	}

	static MessageSource createMessageSource() {

		StaticMessageSource messageSource = new StaticMessageSource();
		messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 所有属性不能为空");
		messageSource.addMessage("name.required", Locale.getDefault(), "The name of the User must not be null");
		messageSource.addMessage("id.required", Locale.getDefault(), "The id of the User must not be null");
		return messageSource;
	}
}
