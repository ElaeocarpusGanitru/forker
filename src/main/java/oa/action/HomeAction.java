package oa.action;

import java.util.List;

import javax.annotation.Resource;

import oa.domain.Privilege;
import oa.service.PrivilegeService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("homeAction")
@Scope("prototype")  
public class HomeAction extends ActionSupport{
	
	@Resource
	PrivilegeService privilegeService;
	
	public String index()
	{
		return "index";
	}
	
	public String top()
	{
		return "top";
	}
	
	public String bottom()
	{
		return "bottom";
	}
	
	public String left()
	{
		return "left";
	}
	
	public String right()
	{
		return "right";
	}
	

}
