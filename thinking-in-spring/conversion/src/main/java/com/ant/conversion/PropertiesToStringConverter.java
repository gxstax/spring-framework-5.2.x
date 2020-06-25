package com.ant.conversion;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * {@link Properties} -> {@link String} {@link ConditionalGenericConverter} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/6/24 7:04 下午
 */
public class PropertiesToStringConverter implements ConditionalGenericConverter {
	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return Properties.class.equals(sourceType.getObjectType())
				&& String.class.equals(targetType.getObjectType());
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return Collections.singleton(new ConvertiblePair(Properties.class, String.class));
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Properties properties = (Properties) source;

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Object, Object> obj : properties.entrySet()) {
			sb.append(obj.getKey()).append("=").append(obj.getValue()).append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}
}
