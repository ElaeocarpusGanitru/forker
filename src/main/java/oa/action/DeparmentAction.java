package oa.action;

import java.util.List;

import javax.annotation.Resource;

import oa.domain.Department;
import oa.service.DepartmentService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller("departmentAction")
@Scope("prototype")
public class DeparmentAction extends ActionSupport{

	@Resource
	DepartmentService departmentService;
	
	Department department = new Department();
	
	public String list()
	{
		Department parent = department.getParent();
		Long parentId = null;
		List<Department> list = null;

		if (parent != null)
		{
			parentId = parent.getId();
		}
		System.out.println("parentId: " + parentId );
		
		//只显示顶层元素
		if (parentId == null)
		{
			list = departmentService.listTop();
		}
		else //显示子部门
		{
			list = departmentService.listSub(parentId);
		}
		
		ActionContext.getContext().put("list", list);
		return "list";
	}
	
	public String delete()
	{
		departmentService.delete(department.getId());
		return "toList";
	}
	
	public String add()
	{	
		departmentService.save(department);
		return "toList";
	}
	
	public String addUI()
	{
		//显示所有部门信息
		List<Department> list = departmentService.findAllShow();
		ActionContext.getContext().put("list", list);
		
		//显示所有的列表
		return "saveUI";
	}
	
	public String edit()
	{
		departmentService.update(department);	
		return "toList";
	}
	
	public String editUI()
	{
		//显示所有部门信息
		List<Department> list = departmentService.findAll();
		ActionContext.getContext().put("list", list);
		department = departmentService.getById(department.getId());
		ActionContext.getContext().getValueStack().push(department);
		return "saveUI";
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
