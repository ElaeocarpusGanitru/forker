package oa.service;

import oa.dao.DaoSupport;
import oa.domain.User;

public interface UserService extends DaoSupport<User>{

	User getByNamePassword(java.lang.String name, java.lang.String password);

}
