package com.ant.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * 功能描述
 * </p>
 *
 * @author GaoXin
 * @since 2020/6/22 11:37 下午
 */
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// 将String 转换为 properties
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(text));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		setValue(properties);
	}

	@Override
	public String getAsText() {
		Properties properties = (Properties) getValue();

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Object, Object> obj : properties.entrySet()) {
			sb.append(obj.getKey()).append("=").append(obj.getValue()).append(System.getProperty("line.separator"));
		}

		return sb.toString();
	}
}
