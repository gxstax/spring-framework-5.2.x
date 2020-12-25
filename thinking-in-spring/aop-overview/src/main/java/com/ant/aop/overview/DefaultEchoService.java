package com.ant.aop.overview;

/**
 * <p>
 *	默认 {@link EchoService} 实现
 * </p>
 *
 * @author Ant
 * @since 2020/12/24 8:51 下午
 */
public class DefaultEchoService implements EchoService {
	
	/**
	 * <p>
	 * echo 实现方法
	 * </p>
	 * 
	  * @param message
	 * @return {@link String}
	 */
	@Override
	public String echo(String message) {
		return "[ECHO]" + message;
	}
}
