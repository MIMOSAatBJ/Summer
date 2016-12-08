package com.doumob.service;

import com.doumob.base.BaseService;

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
	public String regProvider(String group, String unionId);

	/**
	 * 注册服务需求方
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public String regDemander(String group, String unionId);

	/**
	 * 注销服务方
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public String logoffProvider(String group, String unionId);

	/**
	 * 注销需求方
	 * @param group
	 *            所在分组
	 * @param unionId
	 *            唯一标识
	 * @return
	 */
	public String logoffDemander(String group, String unionId);
	

}
