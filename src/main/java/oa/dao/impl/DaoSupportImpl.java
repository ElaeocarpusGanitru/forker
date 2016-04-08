package oa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import oa.dao.DaoSupport;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T>{

	@Resource
	private SessionFactory sessionFactory = null;
	private Class<T> cls = null; //需要处理
	
	/**
	 * 提供获取当前Session方法
	 * @return
	 */
	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	public DaoSupportImpl()
	{
		//使用反射技术得到T的真实类型
		//获取当前new的对象的 泛型的父类 类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		cls = (Class<T>)pt.getActualTypeArguments()[0];
		
	}
	
	protected Class getImplClass()
	{
		return cls;
	}
	
	public void save(T object) {
		getSession().save(object);
	}

	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null)
		{
			getSession().delete(obj);
		}	
	}

	public void update(T object) {
		if (object != null)
		{
			getSession().update(object);
		}
	}

	public T getById(Long id) {
		if (id == null)
		{
			return null;
		}
		return (T) getSession().get(cls, id);
	}
	
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0)
		{
			return Collections.EMPTY_LIST;
		}
		
		String hql = "FROM " + getImplClass().getSimpleName() + " WHERE id IN (:ids)";
		Query query = getSession().createQuery(hql);		
		query.setParameterList("ids", ids);
		return query.list();
	}

	public List<T> findAll() {
		String hql = "FROM " + getImplClass().getSimpleName();
		Query query =  getSession().createQuery(hql);
		return query.list();
	}
}
