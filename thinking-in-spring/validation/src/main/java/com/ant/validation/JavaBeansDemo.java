package com.ant.validation;

import com.ant.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * <p>
 * JavaBeans 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/22 8:01 上午
 */
public class JavaBeansDemo {
	public static void main(String[] args) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(User.class);

		Stream.of(beanInfo.getPropertyDescriptors())
				.forEach(propertyDescriptor -> {
					System.out.println(propertyDescriptor);
		});
	}
}
