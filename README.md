1.创建数据库
mysql> show create database OA;
+----------+-------------------------------------------------------------+
| Database | Create Database                                             |
+----------+-------------------------------------------------------------+
| OA       | CREATE DATABASE `OA` /*!40100 DEFAULT CHARACTER SET utf8 */ |
+----------+-------------------------------------------------------------+
1 row in set (0.01 sec)


2.MyEclipse工程
	1.创建OA工程
	2.设置工程、JSP、TOMCAT设置为UTF-8格式
	3.添加框架环境
		junit
		struts2 : strust.xml web.xml
		spring
		hibernate
	4.整合框架
		strust2和spring整合 
		hibernate和spring整合
			1. 让Spring管理SessionFactory(单例)
			2. 声明式事务管理
	5.资源分类
	6.日志配置

3.设计实体/表
	实体 -> javaBean -> hbm -> 数据库
	interface BaseDao<T>
	{
		save(T t);
		delete(Integer id);
		update(T object);
		getById(Integer id);
		List<T> findAll();
		List<T> getByIds(Integer[] ids);
	} 
	
	class BaseDaoImpl implements BaseDao
	{
		//实现类
	}
	
	其他类
	class RoleDao  //关注自己私有的方法即可
	class RoleDaoImple extends BaseDaoImpl implements RoleDao
	{
		//实现类
	}
	
	
4.设计Action，就知道service需要提供的方法
功能					方法名称			返回值			对应JSP页面
----------------------------------------------------------
显示岗位页面			list()			"list"			list.jsp
删除岗位				delete()		"toList"		list.jsp
添加岗位				add()			"toList"		list.jsp
添加岗位页面			addUI()			"addUI"			addUI.jsp	
修改岗位				edit()			"toList"		list.jsp
修改岗位页面			editUI()		"editUI"		editUI.jsp

5.设计service，就知道dao中需要实现的方法

6.设计dao
--------------------------------第三天-------------------------
由于涉及到上级部门在jsp/department/saveUI.jsp中显示上级部门
1.在Department中addUI中获取所有department
2.在saveUI中动态显示部门元素


-------------------------------------------第四天----------------
初始化数据：
    权限数据
    超级管理员

权限方案
    用户*-------*角色*---------权限
  
与权限相关的功能具体包含
    分配权限....
    登录、注销、页面（根据权限显示信息）        
    拦截action请求，验证用户是否有权限


操作步骤
    1.实体-----------getter/setter----------hbm.xml文件
    2.
	