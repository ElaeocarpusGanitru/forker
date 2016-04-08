package oa.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import oa.domain.Department;
import oa.domain.Role;
import oa.domain.User;
import oa.service.DepartmentService;
import oa.service.RoleService;
import oa.service.UserService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport{
	
	@Resource
	private UserService userService;
	
	@Resource
	DepartmentService departmentService;

	@Resource
	private RoleService roleService ;
	
	private Long[] roleIds;
	private User user = new User();
	
	private static final long serialVersionUID = 1L;

	public String list()
	{
		List<User> users = userService.findAll();
		ActionContext.getContext().put("list", users);
		return "list";
	}
	
	public String delete()
	{
		
		userService.delete(user.getId());
		return "toList";
	}
	
	public String initPassword()
	{
		User tmp = userService.getById(user.getId());
		tmp.setPassword("123456");
		userService.save(tmp);
		return "toList";
	}
	
	public String add()
	{	
		
		Department department = departmentService.getById(user.getDepartment().getId());	
		user.setDepartment(department);
		
		//加密密码字段
		String password = "123456";
		user.setPassword(password);

		Set<Role> roles = new HashSet<Role>();
		roles.addAll(roleService.getByIds(roleIds));
		user.setRoles(roles);		
		userService.save(user);
		
		return "toList";
	}
	
	public String addUI()
	{
		List<Department> departmentList = departmentService.findAllShow();
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		return "saveUI";
	}
	
	public String edit()
	{
		
		Department department = departmentService.getById(user.getDepartment().getId());			

		Set<Role> roles = new HashSet<Role>();
		roles.addAll(roleService.getByIds(roleIds));

		User tmp = userService.getById(user.getId());
		if (tmp != null)
		{
			tmp.setDepartment(department);
			tmp.setDescription(user.getDescription());
			tmp.setEmail(user.getEmail());
			tmp.setGender(user.getGender());
			tmp.setName(user.getName());
			tmp.setPhone(user.getPhone());
			tmp.setRoles(roles);
			userService.save(tmp);
		}
		return "toList";
	}
	
	public String editUI()
	{
		List<Department> departmentList = departmentService.findAllShow();
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		Long id = user.getId();
		user = userService.getById(id);
		ActionContext.getContext().getValueStack().push(user);
		
		int i = 0;
		roleIds = new Long[user.getRoles().size()];
		for (Role role : user.getRoles())
		{
			roleIds[i++] = role.getId();
		}
	
		return "saveUI";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
}
