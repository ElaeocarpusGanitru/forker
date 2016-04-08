package oa.action;

import java.util.List;

import javax.annotation.Resource;

import oa.domain.Role;
import oa.service.RoleService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;


@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends ActionSupport{

	@Resource
	private RoleService roleService ;
	
	private Role role = new Role();
	
	
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

}
