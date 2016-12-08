package com.doumob.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class BaseController {
	private Logger logger=Logger.getLogger(getClass());
	protected final Gson gson=new Gson();
	protected final String success="success";
	/**
	 * @DESC 从request对象中获取参数，返回参数一定不会为null
	 * @param request
	 * @return
	 * @author zhangH
	 * @date 2016年10月25日
	 * @version
	 */
	protected Map<String, String> getparmMap(HttpServletRequest request) {
		Enumeration<String> names= request.getParameterNames();
		Map<String, String> parms=new HashMap<String, String>();
		while (names.hasMoreElements()) {
			String key=names.nextElement();
			String value=request.getParameter(key);
			parms.put(key, value);
		}
		if(logger.isDebugEnabled()){
			logger.debug("received parms:"+parms);
		}
		return parms;
	}
	
	/**
	 * @DESC 在对queryString Encode之后，解析参数 
	 * @param request
	 * @return
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	protected Map<String, String> getparmMapAfterEncode(HttpServletRequest request){
		Map<String, String>  map=null;
		try {
			String qs=URLDecoder.decode(request.getQueryString(), "UTF-8");
			map=parse(qs);
			logger.debug("parsed parms:"+map);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getClass()+":"+e.getMessage());
		}
		return map;
	}
	
	/**
	 * @DESC 解析queryString
	 * @param s 符合a=b&c=d格式
	 * @return 返回map
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	protected Map<String, String> parse(String s){
		Map<String, String> map=new HashMap<String, String>();
		if(s!=null){
			String[] kv=s.split("&");
			for (String str:kv) {
				String[] pv=str.split("=");
				map.put(pv[0], pv[1]);
			}
		}
		return map;
	}
	/**
	 * @DESC 通过response的writer写出内容，而非json格式
	 * @param response
	 * @author zhangH
	 * @date 2016年10月25日
	 * @version
	 */
	protected void write(HttpServletResponse response,String str){
		try {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter pw=response.getWriter();
			pw.write(str);
			pw.close();
		} catch (IOException e) {
			logger.error("write Exception:"+e.getClass().getSimpleName());
		}
		
	}
	
	/**
	 * @DESC 从客户端对象中拿到想要的cookie
	 * @param request
	 * @param key cookie的key
	 * @return
	 * @author zhangH
	 * @date 2016年11月1日
	 * @version
	 */
	protected Cookie getCookie(HttpServletRequest request,String key) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (key.equalsIgnoreCase(c.getName())) {
					cookie = c;
					break;
				}
			}
		}
		return cookie;
	}
	
	/**
	 * @DESC 创建一个cookie
	 * @param key 
	 * @param value 存储的值
	 * @param maxage 生命周期，时间是秒
	 * @return
	 * @author zhangH
	 * @date 2016年11月1日
	 * @version
	 */
	protected Cookie createCookie(String key,String value,Integer maxage){
		Cookie cookie = new Cookie(key,value);
		cookie.setMaxAge(maxage);
		cookie.setPath("/");
		return cookie;
	}

}
