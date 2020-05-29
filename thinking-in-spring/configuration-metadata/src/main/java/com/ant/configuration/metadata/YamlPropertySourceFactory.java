package com.ant.configuration.metadata;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * <p>
 * YAML 格式的 {@link PropertySourceFactory} 示例
 * </p>
 *
 * @author GaoXin
 * @since 2020/5/29 10:53 下午
 */
@SuppressWarnings("unchecked")
public class YamlPropertySourceFactory implements PropertySourceFactory{
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
		YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
		yamlPropertiesFactoryBean.setResources(resource.getResource());

		Properties yamlProperties = yamlPropertiesFactoryBean.getObject();

		return new PropertiesPropertySource(name, yamlProperties);
	}
}
