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
import com.doumob.wx.service.WxAuthService;

@RequestMapping("entry")
public class EntryController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private WxAuthService authService;

	/**
	 * 
	 * @DESC 验证接口
	 * @param request
	 * @param response
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	@RequestMapping(value = "process", method = RequestMethod.GET)
	public void validate(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = getparmMap(request);
		Verification vc=gson.fromJson(gson.toJson(map), Verification.class);
		String r=authService.decrypt(vc);
		if(r!=null){
			logger.debug("验证成功!");
			write(response, r);
		}
		else{
			logger.warn("验证失败，请检查!");
		}
		
	}

	/**
	 * 
	 * @DESC 消息接收接口
	 * @param request
	 * @param response
	 * @author zhangH
	 * @date 2016年11月2日
	 * @version
	 */
	@RequestMapping(value = "process", method = RequestMethod.POST)
	public void process(HttpServletRequest request, HttpServletResponse response,@RequestBody String body) {
		Map<String, String> map = getparmMap(request);
		Verification vc=gson.fromJson(gson.toJson(map), Verification.class);
		String r=authService.decrypt(vc,body);
		if(r!=null){
			logger.debug("解密成功!"+r);
			String result=authService.handleMessage(r);
			logger.debug(result);
		}
		else{
			logger.warn("验证失败，请检查!");
		}
	}
}
