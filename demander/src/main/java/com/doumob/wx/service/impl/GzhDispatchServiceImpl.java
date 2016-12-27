package com.doumob.wx.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.doumob.common.WebService;
import com.doumob.http.SimpleHTTP;
import com.doumob.http.SimpleResponse;
import com.doumob.pojo.RegBean;
import com.doumob.runtime.Config;
import com.doumob.wx.pojo.Credential;
import com.doumob.wx.service.DispatchService;

@Service
public class GzhDispatchServiceImpl implements DispatchService {
	
	private Logger logger=Logger.getLogger(getClass());
	
	private String address=Config.getValue("server_address");
			
	public void register(Credential c) {
		RegBean reg=new RegBean(Config.getValue("AppID"), c.getOpenid());
		SimpleResponse sr=SimpleHTTP.post(address+WebService.regdem.getMapping(),reg.toJson(),SimpleResponse.class);
		logger.debug(sr.getInfo());
	}

}
