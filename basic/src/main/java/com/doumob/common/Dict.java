package com.doumob.common;

public interface Dict {
	
	
	/**
	 * 服务提供者服务状态
	 * @author killer
	 */
	public static enum ServerState{
		on(1,"开户"),off(0,"关闭");
		private ServerState(Integer state, String remark) {
			this.state = state;
			this.remark = remark;
		}
		private Integer state;
		private String remark;
		public Integer getState() {
			return state;
		}
		public String getRemark() {
			return remark;
		}
	}
	
	/**
	 * 服务类型
	 * @author killer
	 *
	 */
	public static enum ServeType{
		provider("provider","提供方"),demander("demander","需求方");
		private ServeType(String type, String remark) {
			this.type = type;
			this.remark = remark;
		}
		private String type;
		private String remark;
		public String getType() {
			return type;
		}
		public String getRemark() {
			return remark;
		}
	}
	
	/**
	 * 微信消息类型
	 * @author killer
	 *
	 */
	public static enum MessageType{
		text("text","纯文本"),image("image","图片"),
		voice("voice","语音"),video("video","视频"),
		shortvideo("shortvideo","小视频"),location("location","地理位置"),
		link("link","链接");
		private  String type;
		private  String remark;
		private MessageType(String type, String remark) {
			this.type = type;
			this.remark = remark;
		}
		public String getType() {
			return this.type;
		}
		public String getRemark() {
			return remark;
		}
	}
	
	/**
	 * mongo数据集合定义
	 * @author killer
	 *
	 */
	public static enum Table{
		cache("cache","用于存储有过期时间的数据"),credential("credential","用户凭证"),
		wxqyh("provider.wxqyh","微信企业号"),qyhemp("provider.qyhemp","微信企业号员工列表");
		private Table(String tableName, String explain) {
			this.tableName = tableName;
			this.explain = explain;
		}
		private String tableName;
		private String explain;
		public String getTableName() {
			return tableName;
		}
		public String getExplain() {
			return explain;
		}
	}
	
	/**
	 * 定义系统提示消息
	 * @author killer
	 *
	 */
	public static enum SystemTip{
		start("start","正在为您匹配咨询师，请稍后！"),neworder("neworder","目前有一位患者请求服务，点击接单进行匹配"),
		late("late","很抱歉，用户己匹配"),matched("matched","匹配好了，开始咨询吧。");
		private String key;
		private String tip;
		private SystemTip(String key, String tip) {
			this.key = key;
			this.tip = tip;
		}
		public String getKey() {
			return key;
		}
		public String getTip() {
			return tip;
		}
	}

}
