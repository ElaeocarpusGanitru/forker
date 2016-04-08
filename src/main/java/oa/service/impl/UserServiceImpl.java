package oa.service.impl;

import oa.dao.impl.DaoSupportImpl;
import oa.domain.Role;
import oa.domain.User;
import oa.service.UserService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService{
	
}
