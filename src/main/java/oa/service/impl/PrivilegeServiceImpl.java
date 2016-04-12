package oa.service.impl;

import java.util.List;

import oa.dao.impl.DaoSupportImpl;
import oa.domain.Privilege;
import oa.service.PrivilegeService;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("privilegeService")
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege>
	implements PrivilegeService
{
	private List<Privilege> getTopPrivilege() {
		Session session = getSession();
		String hql = "From Privilege d WHERE d.parent.id IS NULL";
		return session.createQuery(hql).list();
	}
	
	public List<Privilege> listTop() {
		return getTopPrivilege();
	}

	@Override
	public List<String> findAllUrls() {
		Session session = getSession();
		String hql = "SELECT url From Privilege d WHERE d.url IS NOT NULL";
		return  session.createQuery(hql).list();
	}
}
