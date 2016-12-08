package com.doumob.wx.service;

import com.doumob.wx.pojo.Credential;
import com.doumob.wx.pojo.Verification;


public interface WxCredentialService {
	
	/**
	 * 
	 * @DESC 验证微信签名
	 * @param vc
	 * @return
	 * @author zhangH
	 * @date 2016年10月26日
	 * @version
	 */
	public boolean validSign(Verification vc);
	
	/**
	 * @DESC 查找用户凭证
	 * @param c
	 * @return
	 * @author zhangH
	 * @date 2016年11月1日
	 * @version
	 */
	public Credential findCredential(Credential c);
	
	/**
	 * @DESC 查找用户个人信息，并用update替换
	 * @param query
	 * @param update
	 * @return
	 * @author zhangH
	 * @date 2016年11月1日
	 * @version
	 */
	public void findAndUpdate(Credential query,Credential update);
	
	/**
	 * 根据openId查找用户授权，查找不到就重新获取
	 * @param openId
	 * @return
	 */
	public Credential getByRefresh(String openId);

	/**
	 * 处理接受的消息
	 * @param body
	 * @return
	 */
	public String handleMessage(String body);

}
