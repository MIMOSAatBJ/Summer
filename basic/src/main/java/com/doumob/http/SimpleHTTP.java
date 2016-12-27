package com.doumob.http;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * @author killer 简单的http调用
 */
public class SimpleHTTP {
	private static Logger logger=Logger.getLogger(SimpleHTTP.class);
	/**
	 * @DESC post发送http请求，parm请采用"application/json;charset=UTF-8"编码
	 * @param url
	 * @param parm
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static HttpResponse post(String url) {
			return post(url,null);
	}
	/**
	 * @DESC post发送http请求，parm请采用"application/json;charset=UTF-8"编码
	 * @param url
	 * @param parm
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static HttpResponse post(String url, String parm) {
		if(parm!=null){
			return HttpRequest.post(url)
					.bodyText(parm, MediaType.APPLICATION_JSON_UTF8_VALUE).send();
		}else{
			return HttpRequest.post(url).send();
		}
	}
	
	/**
	 * @DESC  post发送http请求，parm请采用"application/json;charset=UTF-8"编码,返回指定class的实例
	 * @param url
	 * @param parm
	 * @param c
	 * @return
	 * @author zhangH
	 * @date 2016年10月27日
	 * @version
	 */
	public static <T> T post(String url, String parm,Class<T> c) {
		T t=null;
		HttpResponse hr=null;
		hr=post(url, parm);
		if(hr.statusCode()==200){
			if(hr!=null&&hr.bodyText()!=null){
				t=new Gson().fromJson(hr.bodyText(), c);
			}
		}else{
			logger.info(hr.body());
		}
		return t;
	}
	
	/**
	 * @DESC get发送http请求，parm请采用"txt/html"编码
	 * @param url
	 * @param parm
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static HttpResponse get(String url){
			return get(url,null);
	}
	
	/**
	 * @DESC get发送http请求，parm请采用"txt/html"编码
	 * @param url
	 * @param parm
	 * @author zhangH
	 * @date 2016年9月26日
	 * @version
	 */
	public static HttpResponse get(String url,String parm){
		if(parm!=null){
			return HttpRequest.get(url).bodyText(parm,  MediaType.TEXT_HTML_VALUE).send();
		}else{
			return HttpRequest.get(url).send();
		}
	}
	
	/**
	 * @DESC 解析为指定的class
	 * @param r
	 * @param c
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	public static <T> T parse(HttpResponse r,Class<T> c){
		try {
			T t=new Gson().fromJson(r.bodyText(),c);
			return t;
		} catch (Exception e) {
			logger.error("error :" +e.getClass().getName());
			return null;
		}
		
	}
	
}
