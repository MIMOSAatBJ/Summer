package com.doumob.mongo;

import static com.doumob.mongo.MongoUtil.format;
import static com.doumob.mongo.MongoUtil.json;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

@Service
public class MongoAPI {
	private static Logger logger = Logger.getLogger(MongoAPI.class);

	private static MongoClient pool;

	private static MongoAPI API;

	/**
	 * A database connection with internal connection pooling. For most
	 * applications, you should have one Mongo instance for the entire JVM
	 */
	private MongoAPI() {
		if (pool == null) {
//			pool = new MongoClient(MongoConfig.getServer(),
//					MongoConfig.getOptions());
			pool = new MongoClient(MongoConfig.getServer(),MongoConfig.getAuth(),
			 MongoConfig.getOptions());
		}
	}

	/**
	 * @DESC 获取API实例
	 * @return MongoAPI
	 * @author zhangH
	 * @date 2016年6月16日
	 * @version 1.0
	 */

	@PostConstruct
	public static MongoAPI getInstance() {
		if (API == null) {
			API = new MongoAPI();
		}
		return API;
	}

	/**
	 * @DESC:得到默认指定的数据库<br>
	 * @autor:zhangH<br>
	 * @2015-8-5<br>
	 * @return
	 */
	public MongoDatabase getDB() {
		return pool.getDatabase(MongoConfig.getDBName());
	}

	/**
	 * @DESC 得到指定的数据库,DBname为空，则返回默认数据库
	 * @return
	 * @author zhangH
	 * @date 2016年6月16日
	 * @version
	 */
	public MongoDatabase getDB(String DBname) {
		return pool.getDatabase(DBname);
	}

	/**
	 * @DESC:得到指定集合<br>
	 * @autor:zhangH<br>
	 * @2015-8-5<br>
	 * @param name
	 * @return
	 */
	public MongoCollection<Document> getCollection(String name) {
		return getDB().getCollection(name);
	}

	/**
	 * @DESC:得到指定集合<br>
	 * @autor:zhangH<br>
	 * @2015-8-5<br>
	 * @param name
	 * @return
	 */
	public MongoCollection<Document> getCollection(String dbName, String name) {
		return getDB(dbName).getCollection(name);
	}

	/**
	 * @DESC:根据前缀得到所有集合名称<br>
	 * @autor:zhangH<br>
	 * @2015-8-12<br>
	 * @param prefix
	 * @return
	 */
	public List<String> getCollectionsByPrefix(String prefix) {
		MongoIterable<String> set = getDB().listCollectionNames();
		List<String> list = new ArrayList<String>();
		for (String name : set) {
			if (name.startsWith(prefix)) {
				list.add(name);
			}
		}
		return list;
	}

	/**
	 * @DESC:根据前缀得到所有集合名称<br>
	 * @autor:zhangH<br>
	 * @2015-8-12<br>
	 * @param dbName
	 *            指定数据库
	 * @param prefix
	 * @return
	 */
	public List<String> getCollectionsByPrefix(String dbName, String prefix) {
		MongoIterable<String> set = getDB(dbName).listCollectionNames();
		List<String> list = new ArrayList<String>();
		for (String name : set) {
			if (name.startsWith(prefix)) {
				list.add(name);
			}
		}
		return list;
	}

	/**
	 * @DESC:向默认数据库的集合中存入t<br>
	 * @autor:zhangH<br>
	 * @2015-8-6<br>
	 * @param collection
	 *            集合名称
	 * @param t
	 */
	public void insert(String collection, Object t) {
		Document obj = format(t);
		if (obj != null) {
			try {
				getCollection(collection).insertOne(obj);
			} catch (Exception e) {
				logger.error("catch exception:" + e.getClass().getSimpleName());
			}
		}
	}

	/**
	 * @DESC 向指定库的指定集合中插入数据
	 * @param dbName
	 * @param collection
	 * @param t
	 * @author zhangH
	 * @date 2016年7月6日
	 * @version
	 */
	public void insert(String dbName, String collection, Object t) {
		Document obj = format(t);
		if (obj != null) {
			try {
				getCollection(dbName, collection).insertOne(obj);
			} catch (Exception e) {
				logger.error("catch exception:" + e.getClass().getSimpleName());
			}
		}
	}

	/**
	 * @DESC:向数据库的集合中存入t<br>
	 * @autor:zhangH<br>
	 * @2015-8-6<br>
	 * @param collection
	 *            集合名称
	 * @param t
	 */
	public <T> void insert(String collection, List<T> list) {
		List<Document> dbList = new ArrayList<Document>();
		for (T t : list) {
			dbList.add(format(t));
		}
		try {
			getCollection(collection).insertMany(dbList);
		} catch (Exception e) {
			logger.error("catch exception:" + e.getClass().getSimpleName());
		}
	}

	/**
	 * @DESC 向指定库指定集合中插入所有元素
	 * @param dbName
	 * @param collection
	 * @param list
	 * @author zhangH
	 * @date 2016年7月6日
	 * @version
	 */
	public <T> void insert(String dbName, String collection, List<T> list) {
		List<Document> dbList = new ArrayList<Document>();
		for (T t : list) {
			dbList.add(format(t));
		}
		try {
			getCollection(dbName, collection).insertMany(dbList);
		} catch (Exception e) {
			logger.error("catch exception:" + e.getClass().getSimpleName());
		}
	}

	/**
	 * @DESC 从指定集合中按query条件查找
	 * @param collectin
	 * @param query
	 * @return
	 * @author zhangH
	 * @date 2016年7月6日
	 * @version
	 */
	@SuppressWarnings("unchecked")
	public <T> T findOne(String collectin, T query) {
		Document dom = getCollection(collectin).find(format(query)).first();
		T obj =null;
		if(dom!=null){
			obj = (T) json.fromJson(dom.toJson(), query.getClass());
		}
		return obj;
	}

	/**
	 * @DESC 从指定数库的指定集合中按query条件查找db为null
	 * @param dbName
	 * @param collectin
	 * @param query
	 * @return
	 * @author zhangH
	 * @date 2016年7月6日
	 * @version
	 */
	@SuppressWarnings("unchecked")
	public <T> T findOne(String dbName, String collectin, T query) {
		Document dom = getCollection(dbName, collectin).find(format(query))
				.first();
		T obj = (T) json.fromJson(dom.toJson(), query.getClass());
		return obj;
	}

	/**
	 * @DESC:找到并修改<br>
	 * @autor:zhangH<br>
	 * @2015-8-7<br>
	 * @param collection
	 * @param query
	 * @param update
	 */
	public <T> void findAndUpdate(String collection, T query, T update) {
		getCollection(collection).findOneAndUpdate(format(query),
				format(update));
	}

	 /**
	 * @DESC:找到并删除<br>
	 * @autor:zhangH<br>
	 * @2015-8-11<br>
	 * @param collection
	 * @param query
	 */
	 public <T> void findAndRemove(String collection, T query) {
	    getCollection(collection).findOneAndDelete(format(query));
	 }

	  /**
	 * 在指定集合中查找符合条件的第一个元素。第一个的产生是按指定字段排序号的结果
	 * @param collection
	 * @param t
	 * @param item 需要排序的字段
	 * @param sort 默认文档排序(其中1是asc,-1是desc)
	 * @return
	 */
	 @SuppressWarnings("unchecked")
	public <T> T findFirstOne(String collection, T t, String item, Integer sort) {
		Document sdb = null;
		FindIterable<Document> cursor = null;
		if (item != null) {
			sdb = new Document().append(item, sort == null ? 1 : -1);
			cursor = getCollection(collection).find(format(t)).sort(sdb);
		} else {
			cursor = getCollection(collection).find(format(t));
		}
		if (cursor != null) {
			return (T) json.fromJson(cursor.first().toJson(), t.getClass());
		}
		return null;
	}
	
	/**
	 * @DESC:查找所有<br>
	 * @autor:zhangH<br>
	 * @2015-8-12<br>
	 * @param collection
	 * @param query
	 * @return
	 */
	public <T> List<T> findAll(String collection, T query) {
		FindIterable<Document> fi = getCollection(collection).find(	MongoUtil.format(query));
		List<T> list = new ArrayList<T>();
		MongoCursor<Document> cursor = fi.iterator();
		while (cursor.hasNext()) {
			@SuppressWarnings("unchecked")
			T t = (T) json.fromJson(cursor.next().toJson(), query.getClass());
			list.add(t);
		}
		return list;
	}
	
	public static void main(String[] args) {
		MongoAPI api=MongoAPI.getInstance();
		System.out.println(api.getCollection("cache").count());
		//enterprize
	}
	
}