package oa.service;

import java.util.List;

import oa.dao.DaoSupport;
import oa.domain.Department;
import oa.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege>{

	public abstract List<Privilege> listTop();

	public abstract List<String> findAllUrls();
}
