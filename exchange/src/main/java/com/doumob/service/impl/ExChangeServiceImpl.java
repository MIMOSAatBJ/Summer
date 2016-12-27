package com.doumob.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.doumob.http.HttpCode;
import com.doumob.http.SimpleResponse;
import com.doumob.pojo.RegBean;
import com.doumob.pool.ProviderPool;
import com.doumob.service.ExChangeService;

@Service
public class ExChangeServiceImpl implements ExChangeService {
	private Logger logger=Logger.getLogger(getClass());

	@Override
	public SimpleResponse regDemander(String group, String unionId) {
		//TODO需要通知所有服务对象
		return new SimpleResponse(HttpCode.success);
	}

	@Override
	public SimpleResponse logoffProvider(String group, String unionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleResponse logoffDemander(String group, String unionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryProvider(String group,String agentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleResponse regProvider(String group, String agentId, String unionId) {
		ProviderPool.addProvider(new RegBean(group, agentId,unionId));
		return new SimpleResponse(HttpCode.success);
	}

	
	public SimpleResponse takeOrder(String group, String agentId, String unionId) {
		logger.debug("--"+group);
		return new SimpleResponse(HttpCode.success);
	}

}
