package com.ant.generic;
import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * {@link org.springframework.core.GenericTypeResolver} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/25 8:55 下午
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial"})
public class GenericTypeResolverDemo {
	public static void main(String[] args) throws NoSuchMethodException {
		// 返回类型 String 类型是 Comparable<String> 的具体化
		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class,"getString");

		// ArrayList<Object> 是 List 的泛型参数具体化
		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

		// StringList 也是 List 的泛型参数具体化
		displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");

		// 具备 ParameterizedType 返回，否则返回null

		Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
		System.out.println(typeVariableMap);
	}

	public static ArrayList<Object> getList() {
		return null;
	}

	public static StringList getStringList() {
		return null;
	}

	public static String getString() {
		return null;
	}

	public static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc,  String methodName, Class... argumentClass)  throws NoSuchMethodException {
		Method method = GenericTypeResolverDemo.class.getMethod(methodName);

		// 常规类作为方法返回值
		Class<?> returnType = GenericTypeResolver.resolveReturnType(method, containingClass);
		System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", methodName, containingClass.getSimpleName(), returnType);

		// 常规类不具备泛型参数类型
		Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
		System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", methodName, containingClass.getSimpleName(),returnTypeArgument);
	}

	static class StringList extends ArrayList<String> { // 泛型参数的具体化（字节码有记录）

	}
}
