package oa.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import oa.dao.impl.DaoSupportImpl;
import oa.domain.Department;
import oa.service.DepartmentService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl extends DaoSupportImpl<Department> 
	implements DepartmentService
{
	private List<Department> getTopDepartment() {
		Session session = getSession();
		String hql = "From Department d WHERE d.parent.id IS NULL";
		return session.createQuery(hql).list();
	}

	private List<Department> getSubDepartment(Long parentId) {
		Session session = getSession();
		String hql = "From Department d WHERE d.parent.id is ?";
		return session.createQuery(hql).setParameter(0, parentId).list();
	}
		
	//找出当前层级信息
	private List<Department> findSub(Long parentId, String prefix)
	{
		String localPrefix  = "";
		List<Department> tmp = null;
		if (parentId == null)
		{
			tmp = getTopDepartment();
		}
		else
		{
			localPrefix = prefix + "　";
			tmp = getSubDepartment(parentId);
		}
		
		List<Department> list = new ArrayList<Department>();
		for (Department d : tmp)
		{
			Department dept = new Department();
			dept.setId(d.getId());
			String name =  localPrefix + "|-" + d.getName();
			dept.setName(name);
			list.add(dept);
		}
		return list;
	}
	
	public List<Department> findAllShow() //是否需要修改
	{
		List<Department> list = findSub(null, "");
		for (Department d: list)
		{
			System.out.println(d.getName());
		}
		return list;
	}

	@Override
	public void save(Department department) {
		
		Department parent = department.getParent();
		
		if (parent == null) //有上级
		{
			parent = new Department();
		}
		else
		{
			Long parentId = parent.getId();
			parent = getById(parentId);
		}
		department.setParent(parent);
		
		getSession().save(department);
	}


	public List<Department> listTop() {

		return getTopDepartment();
	}

	public List<Department> listSub(Long parentId) {
		return getSubDepartment(parentId);
	}

}
