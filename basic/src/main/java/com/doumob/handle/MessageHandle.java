package com.doumob.handle;

import com.doumob.base.BaseMessage;

/**
 * 处理微信消息
 * @author killer
 *
 */
public interface MessageHandle{
	
	/**
	 * 处理微信消息
	 * @param t 解析的实体
	 * @return 处理后的消息或者null,一般没什么鸟用
	 */
	public <T extends BaseMessage>  String doHandle(T t);
	
	/**
	 * 获取要处理的消息类型类全限定名称，比如
	 * 
	 * @return
	 */
	public String getKey();

}
