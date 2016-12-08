package com.doumob.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.StringConverter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * object xml互转工具
 * @author killer
 *
 */
public class OxmUtil {
	/**
	 * 该方法自动将xml转换为对象
	 * 
	 * @param xml
	 * @return
	 * @return
	 */
	public static <T> T fromXml(String xml, Class<T> t) {
		XStream x = new XStream();
		x.ignoreUnknownElements();
		x.processAnnotations(t);
		x.registerConverter(new CdataConverter());
		@SuppressWarnings("unchecked")
		T obj = (T) x.fromXML(xml);
		return obj;
	}
	/**
	 * 此方法返回该对象xml表现的形式，根元素通过注解指定为"xml"， 如果对象某个字段为这空，则不返回该字段的
	 * 
	 * @return
	 */
	public static <T> String toXml(T obj) {
		XStream x = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-",
				"_")));
		x.processAnnotations(obj.getClass());
		return x.toXML(obj);
	}

	/**
	 * 此转换器将包含<![CDATA[]]>标签的值，取出真正的值
	 * 
	 * @author killer
	 *
	 */
	public static class CdataConverter extends StringConverter {
		public Object fromString(String str) {
			Matcher m = Pattern.compile("<!\\[CDATA\\[([^\\]]+)\\]").matcher(str);
			if (m.find()) {
				str = m.group(1);
			}
			return str;
		}
	}
	
	/**
	 * @DESC 取得微信消息类型
	 * @param xml
	 * @return
	 * @author zhangH
	 * @date 2016年10月26日
	 * @version
	 */
	public static String getMsgType(String xml){
		Matcher m = Pattern.compile("<MsgType>(.*)</MsgType>").matcher(xml);
		String text="";
		if(m.find()){
			text=m.group(1);
			Matcher cd = Pattern.compile("<!\\[CDATA\\[([^\\]]+)\\]").matcher(text);
			if(cd.find()){
				text=cd.group(1);
			}
		}
		return text;
	}
	
	/**
	 * @DESC 获取微信事件类型
	 * @param xml
	 * @return
	 * @author zhangH
	 * @date 2016年10月27日
	 * @version
	 */
	public static String getEvent(String xml){
		Matcher m = Pattern.compile("<Event>(.*)</Event>").matcher(xml);
		String text="";
		if(m.find()){
			text=m.group(1);
			Matcher cd = Pattern.compile("<!\\[CDATA\\[([^\\]]+)\\]").matcher(text);
			if(cd.find()){
				text=cd.group(1);
			}
		}
		return text;
	}
	
	public static void main(String[] args) {
	}
}
