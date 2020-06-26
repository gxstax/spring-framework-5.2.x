package com.ant.generic;

import org.springframework.core.ResolvableType;

/**
 * <p>
 * {@link ResolvableType} 示例
 * </p>
 *
 * @author Ant
 * @since 2020/6/26 11:35 上午
 */
public class ResolvableTypeDemo {
	/**private HashMap<Integer, List<String>> myMap;
	 *
	 * public void example() {
	 *     ResolvableType t = ResolvableType.forField(getClass().getDeclaredField("myMap"));
	 *     t.getSuperType(); // AbstractMap<Integer, List<String>>
	 *     t.asMap(); // Map<Integer, List<String>>
	 *     t.getGeneric(0).resolve(); // Integer 获取map的第一个参数类型
	 *     t.getGeneric(1).resolve(); // List 获取map的第二个参数类型 也就是 List
	 *     t.getGeneric(1); // List<String> 获取第一个参数也就是
	 *     t.resolveGeneric(1, 0); // String 获取嵌套参数中的第二个参数的第一个类型 也就是String
	 * }
	 */
	public static void main(String[] args) {
		ResolvableType resolvableType = ResolvableType.forClass(GenericTypeResolverDemo.StringList.class);
		// 获取该类型的父类 也就是 ArrayList
		ResolvableType superType = resolvableType.getSuperType();
		System.out.println("superType-->" + superType.resolve());

		// ArrayList 父类型 AbstractList
		ResolvableType arrayListSuper = resolvableType.getSuperType().getSuperType();
		System.out.println("arrayListSuper-->" + arrayListSuper.resolve());

		// 获取 raw Type
		System.out.println(resolvableType.asCollection().resolve());
		// 获取泛型参数类型
		System.out.println(resolvableType.asCollection().resolveGeneric(0));
	}
}
