package com.doumob.mongo;

import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.QueryOperators;

/**
 * 操作mongo数据库的帮助类
 * 
 */
public class MongoUtil {
	private static Logger logger = Logger.getLogger(MongoUtil.class);
	protected static Gson json=new Gson();

	/**
	 * @DESC:对存入元素进行格式化,null将被忽略<br>
	 * @autor:zhangH<br>
	 * @2015-8-6<br>
	 * @param o
	 * @return
	 * @version 2.0
	 */
	public static Document format(Object o) {
		Document basic = null;
		try {
			basic = Document.parse(json.toJson(o));
			Iterator<Entry<String, Object>> it= basic.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry =it.next();
				if (entry.getValue() == null) {
					it.remove();
				}
			}
		} catch (Exception e) {
			logger.error("catch exception:" + e.getClass().getSimpleName() + " when format "+ o.getClass().getName());
		}
		return basic;
	}
	/**
	 * @DESC:格式化时间查询（包含开始和结束，接收时间格式为yyyy-MM-dd）<br>
	 * @autor:zhangH<br>
	 * @2015-8-7<br>
	 * @param field 进行比较的字段
	 * @param start 开始时间
	 * @param end   结束时间
	 * @return
	 * @version 2.0
	 */
	public static Document formatTimeQuery(String field, String start,String end) {
		return new Document(field, new Document(QueryOperators.GTE,start).append(QueryOperators.LTE, end));
	}
	
	/**
	 *@DESC:格式化其它操作<br>
	 *@autor:zhangH<br>
	 *@2015-8-19<br>
	 *@param field
	 *@param oper
	 *@param value
	 *@return
	 *@version 2.0
	 */
	public static Document formatOper(String field,String oper,String value){
		return new Document(field, new Document(oper,value));
	}

	/**
	 * @DESC:格式化match，如果需要调用聚合函数 ，则查询条件需要经过此方法进行格式化<br>
	 * @autor:zhangH<br>
	 * @2015-8-7<br>
	 * @param basic
	 * @return
	 */
	public static Document formateMatch(Document basic) {
		return new Document("$match", basic);
	}

	/**
	 * @DESC:列出要显示的字段<br>
	 * @autor:zhangH<br>
	 * @2015-8-7<br>
	 * @param fields
	 * @return
	 */
	public static Document formatGroup(String[] fields) {
		Document group = new Document(); // 接口
		for (int i = 0; i < fields.length; i++) {
			group.put(fields[i], "$" + fields[i]);
		}
		return new Document("$project", group);
	};

	/**
	 * @DESC:得到聚合函数count需要的对象<br>
	 * @autor:zhangH<br>
	 * @2015-8-10<br>
	 * @return
	 */
	public static Document formatCount() {
		return formatCount(null);
	}

	/**
	 * @DESC:得到聚合函数count需要的对象<br>
	 * @autor:zhangH<br>
	 * @2015-8-10<br>
	 * @param groupBy
	 *            分组字段
	 * @return
	 */
	public static Document formatCount(String[] groupBy) {
		Document base = new Document();
		if (groupBy != null) {
			for (int i = 0; i < groupBy.length; i++) {
				base.put(groupBy[i], "$" + groupBy[i]);
			}
		}
		Document groupFields = new Document("_id", base);
		groupFields.put("count", new Document("$sum", 1));
		return new Document("$group", groupFields);
	}

	/**
	 * @DESC:得到聚合函数需要的对象<br>
	 * @autor:zhangH<br>
	 * @2015-8-7<br>
	 * @param field
	 *            需要sum统计的字段
	 * @return
	 */
	public static Document formatSum(String... sumfield) {
		return formatSum(null, sumfield);
	}

	/**
	 * @DESC:得到聚合函数需要的对象<br>
	 * @autor:zhangH<br>
	 * @2015-8-10<br>
	 * @param groupBy
	 *            分组字段
	 * @param sumfield
	 *            求和字段
	 * @return
	 */
	public static Document formatSum(String[] groupBy, String... sumfield) {
		Document base = new Document();
		if (groupBy != null) {
			for (int i = 0; i < groupBy.length; i++) {
				base.put(groupBy[i], "$" + groupBy[i]);
			}
		}
		Document groupFields = new Document("_id", base);
		groupFields.put("count", new Document("$sum", 1));
		for (int i = 0; i < sumfield.length; i++) {
			groupFields.put(sumfield[i], new Document("$sum", "$" + sumfield[i]));
		}
		return new Document("$group", groupFields);
	}

	/**
	 * @DESC:得到集合名称<br>
	 * @autor:zhangH<br>
	 * @2015-8-12<br>
	 * @param group
	 * @param key
	 * @return
	 */
	public static String getCollection(String prefix, Object... key) {
		StringBuffer sb=new StringBuffer();
				sb.append(prefix);
		for (int i = 0; i < key.length; i++) {
			sb.append(".").append(key[i]);
		}
		return sb.toString();
	}

}
