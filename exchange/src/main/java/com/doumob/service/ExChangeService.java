package com.doumob.service;

import com.doumob.base.BaseService;
import com.doumob.http.SimpleResponse;

public interface ExChangeService extends BaseService {

	/**
	 * 注册服务提供者
	 * 
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public SimpleResponse regProvider(String group, String agentId, String unionId);

	/**
	 * @DESC 接收订单
	 * @param group
	 * @param agentId
	 * @param unionId
	 * @return
	 * @author zhangH
	 * @date 2016年12月14日
	 * @version
	 */
	public SimpleResponse takeOrder(String group, String agentId, String unionId);

	/**
	 * 注册服务需求方
	 * 
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public SimpleResponse regDemander(String group, String unionId);

	/**
	 * 注销服务方
	 * 
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public SimpleResponse logoffProvider(String group, String unionId);

	/**
	 * 注销需求方
	 * 
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public SimpleResponse logoffDemander(String group, String unionId);

	/**
	 * @DESC 查找当前可接单的服务提供商
	 * @param group
	 * @return
	 * @author zhangH
	 * @date 2016年12月8日
	 * @version
	 */
	public Integer queryProvider(String group,String agentId);

}
