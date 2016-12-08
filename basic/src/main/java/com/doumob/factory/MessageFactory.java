package com.doumob.factory;

import com.doumob.base.BaseMessage;

public interface MessageFactory {
	/**
	 * @DESC 根据己有的实现，创建微信消息
	 * @param messageType 消息类型
	 * @param xml 消息串，xml类型
	 * @return
	 * @author zhangH
	 * @date 2016年10月26日
	 * @version
	 */
	public <T extends BaseMessage> T create(String xml);
	
	/**
	 * @DESC 创建微信消息
	 * @param c
	 * @param xml
	 * @return
	 * @author zhangH
	 * @date 2016年10月26日
	 * @version
	 */
	public <T extends BaseMessage> T create(Class<T> c,String xml);

}
