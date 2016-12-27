package com.doumob.wx.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doumob.base.BaseController;
import com.doumob.http.SimpleHTTP;
import com.doumob.util.DateUtil;
import com.doumob.util.UrlUtil;
import com.doumob.wx.pojo.Credential;
import com.doumob.wx.service.DispatchService;
import com.doumob.wx.service.WxCredentialService;

@RequestMapping("menu")
public class MenuController extends BaseController {

	@Autowired
	private WxCredentialService cdService;

	@Autowired
	private DispatchService dispatchService;

	private String cookie_key = "wx_1yLrvFn9OBWSqVY6_chat";

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * @DESC 开始微信授权
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	@RequestMapping("step1")
	public String auth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Cookie cookie = getCookie(request, cookie_key);
		if (cookie != null) {
			Credential c = new Credential();
			c.setOpenid(cookie.getValue());
			c = cdService.findCredential(c);
			if (c != null && DateUtil.getOffMills(c.getTimestamp(), DateUtil.getLongDate()) < Integer
					.valueOf(c.getExpires_in())) {
				request.setAttribute("openid", cookie.getValue());
				request.setAttribute("url", UrlUtil.basepath);
				return "menu/prepare";
			}
		}
		return "redirect:" + UrlUtil.getAuthUrl();
	}

	/**
	 * @DESC 授权第二步，获取accessToken
	 * @param request
	 * @param response
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	@RequestMapping("step2")
	public String prepare(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> parms = getparmMap(request);
		Credential credential = null;
		Cookie cookie = getCookie(request, cookie_key);
		if (cookie != null) {
			credential = cdService.getByRefresh(cookie.getValue());
			logger.debug("新的token己获取。");
		}
		if (credential == null) {
			credential = SimpleHTTP.post(UrlUtil.getCdUrl(parms.get("code")), null, Credential.class);
			logger.debug("首次获取token。" + credential.toJson());
		}
		if (credential != null) {
			credential.setTimestamp(DateUtil.getLongDate());
			Credential query = new Credential();
			query.setOpenid(credential.getOpenid());
			cdService.findAndUpdate(query, credential);
			response.addCookie(createCookie(cookie_key, credential.getOpenid(), 90 * 24 * 60 * 60));
			request.setAttribute("openid", credential.getOpenid());
			request.setAttribute("url", UrlUtil.basepath);
		}
		return "menu/prepare";
	}

	/**
	 * @DESC 接收到匹配的需求，开始匹配
	 * @param request
	 * @param response
	 * @return
	 * @author zhangH
	 * @date 2016年10月31日
	 * @version
	 */
	@RequestMapping("start")
	public void start(HttpServletRequest request, HttpServletResponse response) {
		String parm = request.getQueryString();
		Credential c = new Credential();
		c.setOpenid(parm);
		c = cdService.findCredential(c);
		logger.debug(c.toJson());
		if (parm != null && c != null) {
			dispatchService.register(c);
			write(response, "ok");
		} else {
			logger.debug("服务器异常，请求失败");
			write(response, "服务器异常，请重新请求");
		}
	}
}
