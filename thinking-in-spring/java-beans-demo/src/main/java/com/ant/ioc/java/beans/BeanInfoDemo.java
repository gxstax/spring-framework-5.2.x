package com.ant.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * <p>
 * {@link java.beans.BeanInfo} 示例
 * </p>
 *
 * @author GaoXin
 * @since 2021/9/20 4:39 下午
 */
public class BeanInfoDemo {

	public static void main(String[] args) throws IntrospectionException {

		BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);

		Stream.of(beanInfo.getPropertyDescriptors()).forEach(
				propertyDescriptor -> {
					String name = propertyDescriptor.getName();
					if ("age".equals(name)) {
						// String to Integer
						propertyDescriptor.setPropertyEditorClass(StringToInterPropertyEditor.class);
					}

				}
		);
	}

	static class StringToInterPropertyEditor extends PropertyEditorSupport {
		public void setAsText(String text) throws java.lang.IllegalArgumentException {
			setValue(Integer.valueOf(text));
		}
	}

}
