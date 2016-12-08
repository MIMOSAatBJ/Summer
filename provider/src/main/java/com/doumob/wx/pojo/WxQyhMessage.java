package com.doumob.wx.pojo;

import com.doumob.annotation.MsgType;
import com.doumob.base.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class WxQyhMessage extends BaseMessage {
	private static final long serialVersionUID = 1L;
	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private Integer AgentID;
	//MsgId 	消息id，64位整型      不要，我说不要就要
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public Integer getAgentID() {
		return AgentID;
	}
	public void setAgentID(Integer agentID) {
		AgentID = agentID;
	}

	@MsgType(value="text")
	@XStreamAlias("xml")
	public static class TextMessage extends WxQyhMessage{
		private static final long serialVersionUID = 1L;
		private String Content;

		public String getContent() {
			return Content;
		}

		public void setContent(String content) {
			Content = content;
		}
		
	}
	
	@MsgType(value="image")
	@XStreamAlias("xml")
	public static class ImageMessage extends WxQyhMessage{
		private static final long serialVersionUID = 1L;
		private String PicUrl;	//图片链接
		private String MediaId; // 	图片媒体文件id，可以调用获取媒体文件接口拉取数据
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		
	}

	@MsgType(value="event",eventKey="click")
	@XStreamAlias("xml")
	public static class ClickEvent extends WxQyhMessage{
		private static final long serialVersionUID = 1L;
		private String Event;
		private String EventKey;
		public String getEvent() {
			return Event;
		}
		public void setEvent(String event) {
			Event = event;
		}
		public String getEventKey() {
			return EventKey;
		}
		public void setEventKey(String eventKey) {
			EventKey = eventKey;
		}
	}

}
