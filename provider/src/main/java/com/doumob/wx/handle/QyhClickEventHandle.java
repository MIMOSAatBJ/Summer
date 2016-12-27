package com.doumob.wx.handle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doumob.base.BaseMessage;
import com.doumob.handle.MessageHandle;
import com.doumob.http.SimpleResponse;
import com.doumob.runtime.Config;
import com.doumob.wx.pojo.WxQyhMessage.ClickEvent;
import com.doumob.wx.service.DispatchService;

@Service
public class QyhClickEventHandle implements MessageHandle {
	private Logger logger=Logger.getLogger(getClass());
	
	@Autowired
	private DispatchService dispatch;
	public <T extends BaseMessage> String doHandle(T t) { 
		SimpleResponse response=null;
		if(getKey().equals(t.getClass().getSimpleName())){
			ClickEvent ce=(ClickEvent) t;
			if("V001_receive".equals(ce.getEventKey())){//接单标识
				response=dispatch.takeOrder(Config.getValue("qyh.CorpID"), Config.getValue("qyh.agentId"), ce.getFromUserName());
				logger.debug(response.toJson());
			}
		}
		return null;
	}
	
	public <T extends BaseMessage> Class<T> get(){
		return null;
	}

	@Override
	public String getKey() {
		return ClickEvent.class.getSimpleName();
	}

}
