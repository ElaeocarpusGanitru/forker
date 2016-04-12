package oa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oa.domain.User;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		Object object = request.getSession().getAttribute("user");
		User user = (User)object;
		String url = request.getRequestURL().toString();
		String actionName = url.substring(url.lastIndexOf("/"));
		Boolean isLogin = actionName.contains("user_login");
		
		//用户已经登录或者尝试登录
		if ((object != null) || isLogin) 
		{
			chain.doFilter(req, resp);
		}
		else //用户没有登录
		{
			response.sendRedirect("user_login.action");
			return ;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}

}
