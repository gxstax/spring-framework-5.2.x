package com.ant.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * <p>
 * {@link PropertyEditor} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/22 11:36 下午
 *
 * @see PropertyEditor
 */
public class PropertyEditorDemo {

	public static void main(String[] args) {
		// 模拟Spring framework 操作
		String text = "name = 马以";

		PropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
		propertyEditor.setAsText(text);
		System.out.println(propertyEditor.getValue());

		System.out.println(propertyEditor.getAsText());
	}

}
