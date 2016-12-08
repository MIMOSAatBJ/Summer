package com.doumob.base;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * 基本实体对象
 * @author killer
 *
 */
public class BaseBean implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	protected static final Gson gson=new Gson();
	@SuppressWarnings("unchecked")
	public <T> T clone(Class<T> c){
		T t=null;
		try {
			 t=(T) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	public String toJson(){
		return gson.toJson(this);
	}
	
}
