package com.ant.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <p>
 * "users.xsd" {@link NamespaceHandler} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/5/29 12:37 上午
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		// 将 "user" 元素注册对应的 BeanDefinitionParser 实现
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
