package com.doumob.wx.factory.impl;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.doumob.annotation.MsgType;
import com.doumob.base.BaseMessage;
import com.doumob.factory.MessageFactory;
import com.doumob.util.OxmUtil;
import com.doumob.wx.pojo.WxGzhMessage;

@Service
@SuppressWarnings("unchecked")
public class WxGzhMessageFactoryImpl implements MessageFactory {
	
	private Logger logger=Logger.getLogger(getClass());
	
	private static final Class<?>[] c=WxGzhMessage.class.getDeclaredClasses();

	
	public <T extends BaseMessage> T create(String xml) {
		T r=null;
		if(xml==null||xml.isEmpty()){
			logger.warn("parm->xml mustn't be null");
		}
		else{
			String type=OxmUtil.getMsgType(xml);
			String event=OxmUtil.getEvent(xml);
			for (Class<?> t: c) {
				if(t.getAnnotations().length>0&&
						t.getAnnotation(MsgType.class).value().equalsIgnoreCase(type)
						&&t.getAnnotation(MsgType.class).eventKey().equalsIgnoreCase(event)){
				  r=(T)OxmUtil.fromXml(xml, t);
				  logger.debug(r.getClass().getName());
				  break;
				}
			}
			if(r==null){
				 logger.warn("unsupport MsgType["+type+"],please check the system.");
			}
		}
		return r;
	}

	public <T extends BaseMessage> T create(Class<T> clazz, String xml) {
		T r=null;
		if(xml==null||xml.isEmpty()){
			logger.warn("parm->xml mustn't be null");
		}
		else{
			 r=(T)OxmUtil.fromXml(xml, clazz);
		}
		return r;
	}

}
