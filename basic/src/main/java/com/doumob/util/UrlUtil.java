package com.doumob.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.doumob.runtime.Config;

/**
 * 用于一些url的拼接
 * @author killer
 *
 */
public class UrlUtil {
	public static String basepath=Config.getValue("basepath");
	/**
	 * @DESC 获取认证接口
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	public static String getAuthUrl(){
		StringBuffer sb=new StringBuffer();
		try {
			sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
			.append(Config.getValue("AppID")).append("&redirect_uri=")
			.append(URLEncoder.encode(basepath+"/menu/step2","UTF-8"))
			.append("&response_type=code&scope=snsapi_base&state=state#wechat_redirect");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * @DESC 获取请求access_token的链接 
	 * @param code
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	public static String getCdUrl(String code){
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/sns/oauth2/access_token?appid=")
		.append(Config.getValue("AppID")).append("&secret=")
		.append(Config.getValue("AppSecret")).append("&code=")
		.append(code).append("&grant_type=authorization_code");
		return sb.toString();
	}
	
	/**
	 * @DESC 获取微信接口调用凭证
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	public static String getTokenUrl(){
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=")
		.append(Config.getValue("AppID")).append("&secret=")
		.append(Config.getValue("AppSecret"));
		return sb.toString();
	}
	
	/**
	 * @DESC 获取企业号token
	 * @return
	 * @author zhangH
	 * @date 2016年11月3日
	 * @version
	 */
	public static String getEpTokenUrl(String corpId,String secret){
		StringBuffer sb=new StringBuffer();
		sb.append("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=")
		.append(corpId).append("&corpsecret=")
		.append(secret);
		return sb.toString();
	}
	
	/**
	 * @DESC 通过refreshToke获取新的token
	 * @param refresh_token
	 * @return
	 * @author zhangH
	 * @date 2016年11月1日
	 * @version
	 */
	public static String getRfreshTokenUrl(String refresh_token){
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=")
		.append(Config.getValue("AppID"))
		.append("&grant_type=refresh_token&refresh_token=")
		.append(refresh_token);
		return sb.toString();
	}

	/**
	 * @DESC 发送客服消息接口
	 * @param token
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	public static String getSendMsgUrl(String token){
		StringBuffer sb=new StringBuffer();
		sb.append("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=").append(token);
		return sb.toString();
	}
	
	/**
	 * @DESC 给企业号员工发送消息
	 * @param token
	 * @return
	 * @author zhangH
	 * @date 2016年11月3日
	 * @version
	 */
	public static String getSendEpMsgUrl(String token){
		StringBuffer sb=new StringBuffer();
		sb.append("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=").append(token);
		return sb.toString();
	}
	
	/**
	 * 拼接获取用户信息上的url
	 * @param token
	 * @param userId
	 * @return
	 */
	public static String getFetchEmpUrl(String token,String userId){
		StringBuffer sb=new StringBuffer();
		sb.append("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=")
		.append(token).append("&userid=").append(userId);
		return sb.toString();
	}
}
