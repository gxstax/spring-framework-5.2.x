package com.ant.generic;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * Java API 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/25 12:28 下午
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class GenericAPIDemo {
	public static void main(String[] args) {
		// 原生类型 primitive type：int long float
		Class intClass = int.class;

		// 数组类型 array types：int[],Object[]
		Class objectArrayTypes = Object[].class;

		// 原始类型 raw types；java.lang.String
		Class rawClass = String.class;

		// 泛型参数类型 Parameterized types
//		Class genericClass = ArrayList.class;
		ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();

		// parameterizedType.getRawType() = java.util.ArrayList
		// 泛型类型变量 Type Variable

		System.out.println(parameterizedType.toString());

		// <E>
		Arrays.stream(parameterizedType.getActualTypeArguments()).forEach(System.out::println);
	}
}
