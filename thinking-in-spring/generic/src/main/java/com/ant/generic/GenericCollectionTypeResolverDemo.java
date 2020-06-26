package com.ant.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * GenericCollectionTypeResolver 在4.2后就已经被删除了，如果要体验这个例子，请自行引入之前的spring-core版本
 * {@link GenericCollectionTypeResolver} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/25 8:55 下午
 */
@SuppressWarnings({"unchecked", "rawtypes", "serial", "deprecation"})
public class GenericCollectionTypeResolverDemo {

//	private static GenericTypeResolverDemo.StringList stringList;
//
//	private static List<String> strings;
//
//
//	public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
//
//		// StringList extends ArrayList<String> 具体化
//		// getCollectionType 返回具体化泛型参数类型集合的成员类型 = String
//		System.out.println(org.springframework.core.GenericCollectionTypeResolver.getCollectionType(GenericTypeResolverDemo.StringList.class));
//
//		System.out.println(org.springframework.core.GenericCollectionTypeResolver.getCollectionType(ArrayList.class));
//
//
//		Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
//		System.out.println(org.springframework.core.GenericCollectionTypeResolver.getCollectionFieldType(field));
//
//		field = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
//		System.out.println(org.springframework.core.GenericCollectionTypeResolver.getCollectionFieldType(field));
//
//	}


}
