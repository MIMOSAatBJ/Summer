package com.doumob.wx.pojo;

import com.doumob.base.BaseBean;

/**
 * 企业号签名认证
 * @author killer
 *
 */
public class Verification extends BaseBean{
	private static final long serialVersionUID = 1L;
	//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	private String signature;
	//时间戳
	private String timestamp;
	//随机数
	private String nonce;
	//随机字符串
	private String echostr;
	//该字段并不在微信请求参数中，但为了appendKey时方便，故添加在此
	private String token;
	private String msg_signature;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getMsg_signature() {
		return msg_signature;
	}
	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}
}
