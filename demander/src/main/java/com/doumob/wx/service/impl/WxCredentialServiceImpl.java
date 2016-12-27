package com.doumob.wx.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doumob.common.Dict.Table;
import com.doumob.factory.MessageFactory;
import com.doumob.handle.HandleContext;
import com.doumob.http.SimpleHTTP;
import com.doumob.mongo.MongoAPI;
import com.doumob.runtime.Config;
import com.doumob.util.UrlUtil;
import com.doumob.wx.pojo.Credential;
import com.doumob.wx.pojo.Verification;
import com.doumob.wx.pojo.WxGzhMessage;
import com.doumob.wx.service.WxCredentialService;

@Service
public class WxCredentialServiceImpl implements WxCredentialService {

//	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private MongoAPI mapi;

	@Autowired
	private MessageFactory factory;

	public Credential findCredential(Credential c) {
		return mapi.findOne(Table.credential.getTableName(), c);
	}

	public void findAndUpdate(Credential query, Credential update) {
		mapi.findAndRemove(Table.credential.getTableName(), query);
		mapi.insert(Table.credential.getTableName(), update);
		// mapi.findAndUpdate(Table.credential.getTableName(), query, update);
	}

	public Credential getByRefresh(String openId) {
		Credential result = null;
		if (openId != null) {
			Credential temp = new Credential();
			temp.setOpenid(openId);
			temp = findCredential(temp);
			if (temp != null) {
				result = SimpleHTTP.post(UrlUtil.getRfreshTokenUrl(temp.getRefresh_token()), null, Credential.class);
			}
		}
		return result;
	}

	public boolean validSign(Verification vc) {
		List<String> list = new ArrayList<String>();
		list.add(vc.getNonce());
		list.add(vc.getTimestamp());
		list.add(Config.getValue("token"));
		Collections.sort(list);
		StringBuffer sb = new StringBuffer();
		for (String s : list) {
			sb.append(s);
		}
		String str = sb.toString();
		String secret = DigestUtils.sha1Hex(str);
		return vc.getSignature().equals(secret);
	}

	@Override
	public String handleMessage(String body) {
		WxGzhMessage wm = factory.create(body);
		if(wm.getToUserName().equals(Config.getValue("AppID"))){
			return HandleContext.proccess(wm);
		}
		return null;
		// if (wm instanceof WxText) {
		// WxText wt=(WxText) wm;
		// Emp emp=null;
		// if(Pool.match.containsValue(wm.getFromUserName())){
		// for (Entry<String, String> e: Pool.match.entrySet()) {
		// if(e.getValue().equals(wm.getFromUserName())){
		// emp=Pool.matchedserver.get(e.getKey());
		// break;
		// }
		// }
		// if(emp!=null){
		// Map<String, String> map = new HashMap<String, String>();
		// map.put("content",wt.getContent());
		// outService.sendEmpMessage(EpOutMessage
		// .createEpMessage(emp.getUserid(), "",
		// "text",4, "text",
		// map, 0));
		// }
		// }
		// }
		// logger.debug(wm.toJson());
	}

}
