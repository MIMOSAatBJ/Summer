package com.doumob.handle;

import java.util.HashMap;
import java.util.Map;

import com.doumob.base.BaseMessage;

/**
 * 处理器容器
 * @author killer
 *
 */
public class HandleContext{
	
	private static Map<String,MessageHandle> context;
	
	public static <T extends BaseMessage> String proccess(T t){
		if(t!=null){
			MessageHandle h=context.get(t.getClass().getSimpleName());
			return h.doHandle(t);
		}
		return null;
	}
	
	/**
	 * 注册消息处理器
	 * @param mh
	 */
	public static void registerHandle(MessageHandle mh){
		if(context==null){
			context=new HashMap<String, MessageHandle>();
		}
		context.put(mh.getKey(), mh);
	}	
}
