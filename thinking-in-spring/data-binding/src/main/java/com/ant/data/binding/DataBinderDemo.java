package com.ant.data.binding;

import com.ant.spring.ioc.overview.domain.Company;
import com.ant.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * {@link DataBinder} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/21 11:19 下午
 * @see DataBinder
 */
public class DataBinderDemo {

	public static void main(String[] args) {
		User user = new User();
		// 创建 DataBinder
		DataBinder binder = new DataBinder(user, "");

		// 创建 PropertyValues
		Map<String, Object> source = new HashMap<>();
		source.put("id", 1);
		source.put("name", "马以");

		// PropertyValues 存在 User 中不存在的属性值
		// DataBinder 特性一： 忽略未知属性
		source.put("age", 16);

		// PropertyValues 存在 嵌套属性 company.name
		// DataBinder 特性二：支持嵌套属性
//		source.put("company", new Company());
		source.put("company.name", "蚂蚁搬家");

		PropertyValues propertyValues = new MutablePropertyValues(source);

		// 1. 调整 DataBinder IgnoreUnknownFields 值 （true-默认） 为false（抛出异常）
		// binder.setIgnoreUnknownFields(false);

		// 2. 调整自动增加嵌套路径值 （true-默认）为false (报异常 设置为false 需要一个一个添加属性)
		binder.setAutoGrowNestedPaths(false);

		// 3. 是否忽略错误字段（false-默认）为true
		binder.setIgnoreInvalidFields(true);

		// 4. 设置必须字段
		binder.setRequiredFields("id", "name", "city");

		binder.bind(propertyValues);

		System.out.println(user);


		// 5. 获取绑定结果文案信息
		BindingResult result = binder.getBindingResult();
		System.out.println(result);
	}
}
