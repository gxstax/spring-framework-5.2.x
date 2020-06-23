package com.ant.conversion;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 自定义 {@link PropertyEditorRegistrar} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/6/23 8:21 上午
 */
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());
	}
}
