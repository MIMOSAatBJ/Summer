package com.doumob.wx.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doumob.factory.MessageFactory;
import com.doumob.handle.HandleContext;
import com.doumob.runtime.Config;
import com.doumob.wx.aes.AesException;
import com.doumob.wx.aes.WXBizMsgCrypt;
import com.doumob.wx.pojo.Verification;
import com.doumob.wx.pojo.WxQyhMessage;
import com.doumob.wx.service.WxAuthService;

@Service
public class WxAuthServiceImpl implements WxAuthService {

	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private MessageFactory factory;


	public String handleMessage(String body) {
		WxQyhMessage wm = factory.create(body);
		return HandleContext.proccess(wm);
	}

	public String decrypt(Verification vc) {
		String sEchoStr=null;
		try {
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Config.getValue("qyh.token"),
					Config.getValue("qyh.EncodingAESKey"), 
					Config.getValue("qyh.CorpID"));
				sEchoStr = wxcpt.VerifyURL(vc.getMsg_signature(), vc.getTimestamp(),
						vc.getNonce(), vc.getEchostr());
		} catch (Exception e) {
			logger.error(e.getClass()+":"+e.getMessage());
		}
		return sEchoStr;
	}

	@Override
	public String decrypt(Verification vc,String post) {
		String sMsg = null;
		WXBizMsgCrypt wxcpt=null;
		try {
			wxcpt = new WXBizMsgCrypt(Config.getValue("qyh.token"),
					Config.getValue("qyh.EncodingAESKey"), 
					Config.getValue("qyh.CorpID"));
			sMsg = wxcpt.DecryptMsg(vc.getMsg_signature(), vc.getTimestamp(),
					vc.getNonce(), post);
		} catch (AesException e) {
			logger.error(e.getClass()+":"+e.getMessage());
		}
		return sMsg;
	}
}
