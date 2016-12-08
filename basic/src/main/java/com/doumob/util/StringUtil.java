package com.doumob.util;

import java.net.URLDecoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author killer
 *
 */
public class StringUtil {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static final String ALLNUMBER = "0123456789";

	/**
	 * 得到随机序列，主要用于随机参数生成长度是16-32位
	 * 
	 * @return
	 */
	public static String getRandomsequence() {
		Random r = new Random();
		int l = 16 + r.nextInt(17);// 设置随机数长度为16-32
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < l; i++) {
			sb.append(ALLCHAR.charAt(r.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * 得到指定长度的随机序列
	 * 
	 * @return
	 */
	public static String getRandomsequence(Integer length) {
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append(ALLCHAR.charAt(r.nextInt(ALLCHAR.length())));
		}
		return sb.toString();
	}

	/**
	 * @param digit格式化字符长度
	 * @return
	 */
	public static String getFormater(Integer digit) {
		String s = "%0" + digit + "d";
		return s;
	}

	/**
	 * @DESC 将传入对象所有值不为空的属性拼接成字符串，key按字母顺序排列，类似a=a&b=b格式
	 * @param obj 
	 * @param separater 分割符 为空或者""则不进行拼接
	 * @return
	 * @author zhangH
	 * @date 2016年10月25日
	 * @version
	 */
	@SuppressWarnings("unchecked")
	public static <T> String apendKey(T obj,String separater){
		SortedMap<String, Object> smap = new TreeMap<String, Object>();
		if (obj instanceof Map) {
			smap.putAll((Map<String, String>) obj);
		} else {
			try {
				smap.putAll(BeanUtils.describe(obj));
			} catch (Exception e) {
				e.printStackTrace();
			} 
			smap.remove("class");
		}
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : smap.entrySet()) {
			if (entry.getValue() != null&& !entry.getValue().toString().isEmpty()) {
				sb.append(entry.getKey()).append("=").append(entry.getValue());
			}
			if(separater!=null&&!separater.isEmpty()){
				sb.append(separater);
			}
		}
		return sb.substring(0, sb.length() - 1);
	}
	
	public static void main(String[] args)throws Exception {
		String s="msg_signature=22f3a5f91335bdd3b6d96da97817be06c472c4fe&timestamp=1478054585&nonce=1605353502&echostr=0CcS0NVVuRlMwSgpj9E2CsBwvUhSAUFWHCBxbzZH0gxS8xU8Lu6ZQvjwGFqAISowWX8LIGB5XH9YCG0D7d%2BvPQ%3D%3D";
		System.out.println(s);
		System.out.println(URLDecoder.decode("", "UTF-8"));
	}

}
