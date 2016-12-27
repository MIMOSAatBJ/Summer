package com.doumob.wx.pojo;

import com.doumob.annotation.MsgType;
import com.doumob.base.BaseMessage;
import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class WxGzhMessage extends BaseMessage{
	static final long serialVersionUID = 1L;
	
	@SerializedName("receiver")
	private String ToUserName;//	开发者微信号
	@SerializedName("source")
	private String FromUserName;//	发送方帐号（一个OpenID）
	@SerializedName("timestamp")
	private String CreateTime;//	消息创建时间 （整型）
	@SerializedName("msgtype")
	private String MsgType;//	text
	@SerializedName("objId")
	private String MsgId;//	消息id，64位整型
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
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	

	@MsgType("text")
	@XStreamAlias("xml")
	public static class WxText extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		@SerializedName("content")
		private String Content;//	文本消息内容
		public String getContent() {
			return Content;
		}
		public void setContent(String content) {
			Content = content;
		}
		
	}
	
	@MsgType("image")
	@XStreamAlias("xml")
	public static class WxImage extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		@SerializedName("url")
		private String PicUrl;//图片地址
		@SerializedName("mediaId")
		private String MediaId;
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
	
	@MsgType("voice")
	@XStreamAlias("xml")
	public static class WxVoice extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		@SerializedName("mediaId")
		private String MediaId;
		@SerializedName("format")
		private String Format;
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		public String getFormat() {
			return Format;
		}
		public void setFormat(String format) {
			Format = format;
		}
	}
	
	@MsgType("video")
	@XStreamAlias("xml")
	public static class WxVideo extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		@SerializedName("mediaId")
		private String MediaId;
		@SerializedName("thumbMediaId")
		private String ThumbMediaId;
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		public String getThumbMediaId() {
			return ThumbMediaId;
		}
		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
	}
	
	@MsgType("shortvideo")
	@XStreamAlias("xml")
	public static class WxShortVideo extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		@SerializedName("mediaId")
		private String MediaId;
		@SerializedName("thumbMediaId")
		private String ThumbMediaId;
		public String getMediaId() {
			return MediaId;
		}
		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
		public String getThumbMediaId() {
			return ThumbMediaId;
		}
		public void setThumbMediaId(String thumbMediaId) {
			ThumbMediaId = thumbMediaId;
		}
	}
	
	@MsgType("location")
	@XStreamAlias("xml")
	public static class WxLocation extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		private String Location_X;
		private String Location_Y;
		private String Scale;//地图缩放大小
		private String Label;//地理位置信息
		public String getLocation_X() {
			return Location_X;
		}
		public void setLocation_X(String location_X) {
			Location_X = location_X;
		}
		public String getLocation_Y() {
			return Location_Y;
		}
		public void setLocation_Y(String location_Y) {
			Location_Y = location_Y;
		}
		public String getScale() {
			return Scale;
		}
		public void setScale(String scale) {
			Scale = scale;
		}
		public String getLabel() {
			return Label;
		}
		public void setLabel(String label) {
			Label = label;
		}
	}
	
	
	@MsgType("link")
	@XStreamAlias("xml")
	public static class WxLink extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		private String Title;
		private String Description;
		private String url;
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}
	
	@MsgType(value="event",eventKey="view")
	@XStreamAlias("xml")
	public static class WxViewEvent extends WxGzhMessage{
		private static final long serialVersionUID = 1L;
		public  String Event;
		private String EventKey;
		public  String getEvent() {
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
