package com.doumob.mongo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * mongo 配置项
 * 
 * @author killer
 * 
 */
public class MongoConfig {
	private static Properties prop;

	private static String getOptions(String key) {
		if(prop==null){
			prop = new Properties();
		}
		InputStream is = MongoConfig.class.getResourceAsStream("/mongodb.properties");
		try {
			prop.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	/**
	 * @DESC:得到服务地址<br>
	 * @autor:zhangH<br>
	 * @2015-8-5<br>
	 * @return
	 */
	public static List<ServerAddress> getServer() {
		String replica=getOptions("replica.set");
		String[] hp=replica.split(",");
		List<ServerAddress> list=new ArrayList<ServerAddress>();
		for (int i = 0; i < hp.length; i++) {
			try {
				String[] sp=hp[i].split(":");
				ServerAddress address=new ServerAddress(sp[0],Integer.valueOf(sp[1]));
				list.add(address);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public static List<MongoCredential> getAuth(){
		MongoCredential credential = MongoCredential.createCredential(
				getOptions("auth_name"),"admin",getOptions("auth_pwd").toCharArray());
		List<MongoCredential> list=new ArrayList<MongoCredential>();
		list.add(credential);
		return list;
		
	}

	/**
	 * 
	 * @DESC:得到连接选项<br>
	 * @autor:zhangH<br>
	 * @2015-8-5<br>
	 * @return
	 */
	public static MongoClientOptions getOptions() {
		Builder builder = new Builder();
		builder.connectionsPerHost(Integer.valueOf(getOptions("mongo.connectionsPerHost")));
		builder.connectTimeout(Integer.valueOf(getOptions("mongo.connectTimeout")));
		builder.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(getOptions("mongo.threadsAllowedToBlockForConnectionMultiplier")));
		builder.maxWaitTime(Integer.valueOf(getOptions("mongo.maxWaitTime")));
		builder.socketKeepAlive(Boolean.valueOf(getOptions("mongo.socketKeepAlive")));
		return builder.build();
	}

	/**
	 *@DESC:得到指定连接的数据库<br>
	 *@autor:zhangH<br>
	 *@2015-8-5<br>
	 *@return
	 */
	public static String getDBName() {
		return getOptions("mongo.database");
	}
}
