package oa.service.impl;

import oa.dao.impl.DaoSupportImpl;
import oa.domain.User;
import oa.service.UserService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{

	@Override
	public User getByNamePassword(String name, String password) {
		Session session = getSession();
		String hql = "From User u WHERE u.loginName = ? AND u.password = ?";
		Query query = session.createQuery(hql);
		query.setString(0, name);
		query.setString(1, password);
		User user = (User)query.uniqueResult();
		return user;
	}
	
}
