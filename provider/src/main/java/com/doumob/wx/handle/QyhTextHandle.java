package com.doumob.wx.handle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.doumob.base.BaseMessage;
import com.doumob.handle.MessageHandle;
import com.doumob.wx.pojo.WxQyhMessage.TextMessage;

@Service
public class QyhTextHandle implements MessageHandle{
	private Logger logger=Logger.getLogger(getClass());
	
	public <T extends BaseMessage> String doHandle(T t) {
		if(getKey().equals(t.getClass().getSimpleName())){
			logger.debug(getKey());
		}
		return null;
	}


	public String getKey() {
		return TextMessage.class.getSimpleName();
	}

}
