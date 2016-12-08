package com.doumob.wx.service;

import com.doumob.wx.pojo.Verification;

public interface WxAuthService {
	
	
	/**
	 * @DESC 解密企业号认证信息，并返回明文
	 * @param vc
	 * @return
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	public String decrypt(Verification vc);
	
	/**
	 * @DESC 消息解密,并返回明文
	 * @param vc
	 * @param call
	 * @return
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	public String decrypt(Verification vc,String post);
	
	/**
	 * @DESC 解析消息并处理
	 * @param vc
	 * @param call
	 * @return
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	public String handleMessage(String body);
}
