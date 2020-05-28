package com.ant.configuration.metadata;

import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * <p>
 * "user" 元素的 {@link BeanDefinitionParser} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/5/29 12:43 上午
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		setPropertyValues("id", element, builder);
		setPropertyValues("name", element, builder);
		setPropertyValues("city", element, builder);
	}

	public void setPropertyValues(String attribute, Element element, BeanDefinitionBuilder builder) {
		String attributeValue = element.getAttribute(attribute);
		if (StringUtils.hasText(attributeValue)) {
			// BeanDefinition 定义我们的属性,相当于 -> <property name="" value=""/>
			builder.addPropertyValue(attribute, attributeValue);
		}
	}
}
