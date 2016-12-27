package com.doumob.wx.handle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.doumob.base.BaseMessage;
import com.doumob.handle.MessageHandle;
import com.doumob.wx.pojo.WxGzhMessage.WxText;

@Service
public class GzhTextHandle implements MessageHandle {
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public <T extends BaseMessage> String doHandle(T t) {
		if(getKey().equals(t.getClass().getSimpleName())){
			logger.debug(getClass()+" handled :"+ t.toJson());
		}
		return null;
	}

	public String getKey() {
		return WxText.class.getSimpleName();
	}

}
