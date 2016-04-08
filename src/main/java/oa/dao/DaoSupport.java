package oa.dao;

import java.util.List;

public interface DaoSupport<T> {
	
	/**
	 * 保存一个对象
	 * @param object
	 */
	void save(T object);
	
	/**
	 * 用Id号删除一个对象
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 更新一个对象
	 * @param object
	 */
	void update(T object);
	
	/**
	 * 
	 * 用Id号找一个对象
	 * @param id
	 * @return
	 */
	T getById(Long id);
	
	/**
	 * 查找所有对象
	 */
	List<T> findAll();
	
	/**
	 *  查找一组对象
	 */
	List<T> getByIds(Long[] ids);
}
