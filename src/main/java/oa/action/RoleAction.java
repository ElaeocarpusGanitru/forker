package oa.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import oa.domain.Privilege;
import oa.domain.Role;
import oa.service.PrivilegeService;
import oa.service.RoleService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends ActionSupport{
	@Resource
	private RoleService roleService ;
	
	@Resource
	private PrivilegeService privileService;
	
	private Role role = new Role();
	private Long[] ids;
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	private static final long serialVersionUID = 1L;

	public String list()
	{
		List<Role> list = roleService.findAll();
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	public String delete()
	{
		if (role != null)
		{
			roleService.delete(role.getId());
		}
		return "toList";
	}
	
	public String add()
	{
		String name = role.getName();
		
		//如果role非空
		if (name != null && !name.trim().isEmpty())
		{
			roleService.save(role);
		}
		return "toList";
	}
	
	public String addUI()
	{
		return "saveUI";
	}
	
	public String edit()
	{
		System.out.println("edit role : " + role);

		//如果role非空
		if (!role.getName().trim().isEmpty())
		{
			roleService.update(role);
		}
		return "toList";
	}
	
	public String editUI()
	{
		role = roleService.getById(role.getId());
		ActionContext.getContext().getValueStack().push(role);
		
		return "saveUI";
	}
	
	public String setPrivilegeUI()
	{
		role = roleService.getById(role.getId());
		
		Set<Privilege> privileges = role.getPrivileges();
		ids = new Long[privileges.size()];
		int index = 0;
		for (Privilege p : privileges)
		{
			ids[index++] = p.getId();
		}
		
		List<Privilege> privilegelist = privileService.findAll();
		System.out.println("size : " + privilegelist.size());
		ActionContext.getContext().put("privilegelist", privilegelist);
		return "privilegeUI";
	}
	
	//设置用户权限
	public String setPrivilege()
	{
		role = roleService.getById(role.getId());
		System.out.println("set privilege : " + role);
		
		List<Privilege> privileges = privileService.getByIds(ids);
		System.out.println("set privilege size :  " + privileges.size());
		role.setPrivileges(new HashSet(privileges));
		
		roleService.update(role);
		return "toList";
	}
}
