package com.doumob.wx.service;

import com.doumob.http.SimpleResponse;

/**
 * 调度服务类
 * @author killer
 *
 */
public interface DispatchService {
	
	/**
	 * @DESC 调用该方法表示用户发起了咨询请求
	 * @param c
	 * @author zhangH
	 * @date 2016年12月8日
	 * @version
	 */
	public SimpleResponse takeOrder(String group,String agentId,String userId);
	

}
