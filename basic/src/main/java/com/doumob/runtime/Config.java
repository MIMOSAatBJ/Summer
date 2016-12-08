package com.doumob.runtime;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {
	private static Logger log = Logger.getLogger(Config.class);
	private static Properties prop = new Properties();
	private static List<String> configList = new ArrayList<String>();
	static {
		configList.add("/config.properties");
		load("/config.properties");
	}
	
	/**
	 * @DESC 加载配置
	 * @param path
	 * @author zhangH
	 * @date 2016年10月10日
	 * @version
	 */
	private static void load(String path) {
		try {
			InputStream is = Config.class.getResourceAsStream(path);
			if (is != null) {
				prop.load(is);
				is.close();
			}
		} catch (Exception e) {
			log.error("Throw exception when load:"+path
					+ e.getClass().getSimpleName());
		}
	}

	/**
	 * @DESC 合并其它配置信息到系统properties
	 * @param path
	 * @author zhangH
	 * @date 2016年9月18日
	 * @version
	 */
	public static void mergeConfig(String path) {
		try {
			configList.add(path);
			load(path);
		} catch (Exception e) {
			log.error("Throw exception when mergeConfig:"
					+ e.getClass().getSimpleName());
		}
	}

	/**
	 * 
	 * @DESC 重新加载所有之前加载过的配置
	 * @param ignore
	 *            指定哪些路径的配置信息不需要再加载
	 * @author zhangH
	 * @date 2016年10月10日
	 * @version
	 */
	public static void reLoad(String... ignore) {
		if (ignore != null) {
			for (int i = 0; i < ignore.length; i++) {
				configList.remove(ignore[i]);
			}
		}
		try {
			prop.clear();
			for (String p : configList) {
				load(p);
			}
			log.error("config reloaded");
		} catch (Exception e) {
			log.error("Throw exception when reLoad config");
		}
	}

	/**
	 * 获取指定key的配置项
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return prop.getProperty(key);
	}

	/**
	 * @DESC 获取以编码的value，主要支持有中文的配置项，默认UTF-8
	 * @param key
	 * @param charset
	 * @return
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static String getValue(String key, String charset) {
		String c = charset == null ? "UTF-8" : charset;
		try {
			String v=getValue(key);
			if(v==null){
				return v;
			}else{
				return new String(v.getBytes("ISO-8859-1"), c);
			}
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException");
		}
		return "";
	}

	/**
	 * 
	 * @DESC 得到本机IP
	 * @author zhangH
	 * @date 2016年9月15日
	 * @version
	 */
	public static String getAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("Throw UnknownHostException when calling the method getAddress");
		}
		return null;
	}

	/**
	 * @DESC 返回配置中的项目编码
	 * @return
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static String getEncoding() {
		return getValue("encoding");
	}

	public static void main(String[] args) {
		System.out.println(getValue("configurator", "UTF-8"));
		System.out.println(getValue("log4j.rootLogger"));
		mergeConfig("/log4j.properties");
		System.out.println(getValue("log4j.rootLogger"));
		String s=new String("/log4j.properties");
		reLoad(s);
		System.out.println(getValue("log4j.rootLogger"));
		System.out.println(getValue("configurator", "UTF-8"));
	}
}
