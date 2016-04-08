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

public class TmplateAction extends ActionSupport{

	
	private static final long serialVersionUID = 1L;

	public String list()
	{
	
		return "list";
	}
	
	public String delete()
	{
		
		return "toList";
	}
	
	public String add()
	{
		
		return "toList";
	}
	
	public String addUI()
	{
		return "saveUI";
	}
	
	public String edit()
	{
		
		return "toList";
	}
	
	public String editUI()
	{
		
		return "saveUI";
	}

}
