package oa.intercepter;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.domain.Privilege;
import oa.domain.Role;
import oa.domain.User;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class PrivilegeInterceptor implements Interceptor {

	@Override
	public String intercept(ActionInvocation action) throws Exception {
		// TODO Auto-generated method stub
		
//		已经过了LoginFilter这里没有必要再重新判断用户是否登录，只需要判断用户是否有权限即可
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null || "admin".equals(user.getLoginName()))
		{
			return action.invoke(); 
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String nameSpace = action.getProxy().getNamespace();
		String actionName = action.getProxy().getActionName();
		String priUrl = nameSpace + actionName;
		
		//公共访问页面
		List<String> urls = (List<String>) ActionContext.getContext().getApplication().get("privilegeUrls");
		if (!urls.contains(priUrl))
		{
			return action.invoke(); 
		}
		
		//权限相关页面，需要进行控制
		Set<Role> roles = user.getRoles();
		outer:for (Role r : roles)
		{
			Set<Privilege> privileges = r.getPrivileges();
			for (Privilege p : privileges)
			{
				if (priUrl.equals(p.getUrl()))
				{
					return action.invoke();
				}
			}
		}
			
		System.out.println("sorry no privilege!!");
		return "";
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}