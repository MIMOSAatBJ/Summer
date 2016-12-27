package com.doumob.wx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doumob.base.BaseController;
import com.doumob.wx.pojo.Verification;
import com.doumob.wx.service.WxCredentialService;

/**
 * 微信消息接收器
 * @author killer
 *
 */
@RequestMapping("receiver")
public class MessageInputController extends BaseController {
	
	private Logger logger=Logger.getLogger(getClass());
	@Autowired
	private WxCredentialService wxService;
	
	/**
	 * @DESC 验证服务器
	 * @param request
	 * @param response
	 * @author zhangH
	 * @date 2016年10月25日
	 * @version
	 */
	@RequestMapping(value="handle",method=RequestMethod.GET)
	public void accessCkeck(HttpServletRequest request,
			HttpServletResponse response){
		Map<String, String> parms=getparmMap(request);
		Verification vc=gson.fromJson(gson.toJson(parms), Verification.class);
		if(wxService.validSign(vc)){
			logger.info("signature验证成功");
			write(response, vc.getEchostr());
		}
	}
	
	/**
	 * @DESC 处理消息
	 * @param request
	 * @param response
	 * @return
	 * @author zhangH
	 * @date 2016年10月25日
	 * @version
	 */
	@RequestMapping(value="handle",method=RequestMethod.POST)
	public void pross(HttpServletRequest request,
			HttpServletResponse response,@RequestBody String body){
		Map<String, String> parms=getparmMap(request);
		Verification vc=gson.fromJson(gson.toJson(parms), Verification.class);
		if(wxService.validSign(vc)){
			wxService.handleMessage(body);
		}
		write(response,success);
	}

}
