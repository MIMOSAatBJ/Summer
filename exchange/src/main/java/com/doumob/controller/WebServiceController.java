package com.doumob.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doumob.base.BaseController;
import com.doumob.http.SimpleResponse;
import com.doumob.pojo.RegBean;
import com.doumob.service.ExChangeService;

@RestController
@RequestMapping("webservice")
public class WebServiceController extends BaseController {
	private Logger logger=Logger.getLogger(getClass());
	@Autowired
	private ExChangeService exchange;

	@RequestMapping("regdem")
	public SimpleResponse regdem(HttpServletRequest request, HttpServletResponse response) {
		RegBean obj=getparmFromStream(request,RegBean.class);
		return exchange.regDemander(obj.getGroup(), obj.getUnionId());
	}
	
	public SimpleResponse regprovider(HttpServletRequest request, HttpServletResponse response){
		RegBean obj=getparmFromStream(request,RegBean.class);
		return exchange.regProvider(obj.getGroup(), obj.getAgentId(), obj.getUnionId());
	}
	
	@RequestMapping("takeOrder")
	public SimpleResponse takeOrder(HttpServletRequest request, HttpServletResponse response){
		RegBean obj=null;
		try {
			 obj=getparmFromStream(request,RegBean.class);
		} catch (Exception e) {
			logger.debug("error :[ "+e.getClass().getName()+" ]");
		}
		return exchange.regDemander(obj.getGroup(), obj.getUnionId());
	}

}
