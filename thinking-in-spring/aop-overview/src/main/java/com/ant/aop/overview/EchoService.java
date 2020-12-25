package com.ant.aop.overview;

/**
 * <p>
 * Echo 服务
 * </p>
 *
 * @author Ant
 * @since 2020/12/24 8:50 下午
 */
public interface EchoService {

	String echo(String message) throws NullPointerException;

}
