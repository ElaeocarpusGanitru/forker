package oa.service;

import java.util.List;

import oa.dao.DaoSupport;
import oa.domain.Department;

public interface DepartmentService extends DaoSupport<Department> {

	public abstract List<Department> findAllShow(); //是否需要修改
	public abstract List<Department> listTop();
	public abstract List<Department> listSub(Long parentId);
}
