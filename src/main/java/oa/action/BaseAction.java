package oa.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction<T, K> extends ActionSupport{
	
	private Class t = null;
	private Class k = null;
	
	private static final long serialVersionUID = 1L;
	
	BaseAction()
	{
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		t = (Class<T>)type.getActualTypeArguments()[0];
		k =  (Class<T>)type.getActualTypeArguments()[1];
	}
	
	public T getService() throws InstantiationException, IllegalAccessException 
	{
		return (T)t.newInstance();
	}

	public K getInstance() throws InstantiationException, IllegalAccessException 
	{
		return (K)k.newInstance();
	}
}
