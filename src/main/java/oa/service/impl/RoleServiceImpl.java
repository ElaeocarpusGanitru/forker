package oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import oa.dao.impl.DaoSupportImpl;
import oa.domain.Role;
import oa.service.RoleService;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleService")
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService{

}
