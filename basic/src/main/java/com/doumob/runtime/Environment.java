package com.doumob.runtime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * 通过此类可得到运行时IOC容器
 * 
 * @author zhangH
 */
public class Environment {

	private static ApplicationContext applicationContext;

	private static String xml_path = "applicationContext.xml";

	static {
		applicationContext = ContextLoader.getCurrentWebApplicationContext();
		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext(xml_path);
		}
	}

	/**
	 * 需要通过 Environment 获得的资源,都写在environment_xmlPath所对应的xml中
	 */
	private Environment() {
	}

	private static Environment env = new Environment();

	public static Environment getInstance() {
		if (env == null)
			env = new Environment();
		return env;
	}

	@SuppressWarnings("unchecked")
	public <E> E getService(Class<E> c) {
		// 从指定的spring配置文件中读取是否有c类型的（包括其子类型）的bean
		if (applicationContext == null) {
			applicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			if (applicationContext == null)
				applicationContext = new ClassPathXmlApplicationContext(
						xml_path);
		}
		String[] names = applicationContext.getBeanNamesForType(c);
		// 配置文件中每个对应的接口只配置一个，所以可以拿第一个，如果有多个，
		// 也只拿第一个
		if (names.length >= 1) {
			return (E) applicationContext.getBean(names[0]);
		} else {
			try {
				throw new Exception("没有这样的业务类存在");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
