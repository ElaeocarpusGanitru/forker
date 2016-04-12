package oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import oa.domain.Privilege;
import oa.service.PrivilegeService;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;

public class InitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		PrivilegeService privilegeService  = (PrivilegeService)ac.getBean("privilegeService");
		List<Privilege> privilege = privilegeService.listTop();  
		event.getServletContext().setAttribute("privilegeList", privilege);
	}  
}
