package com.atm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atm.dataobj.User;
import com.atm.model.InformUserForm;
import com.atm.service.UserService;

/**
 * Servlet Filter implementation class ActiveFilter
 */
public class ActiveFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ActiveFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userObj");
		
		UserService service = new UserService();
		User userUpdate = service.getUserByUsername(user.getUname());
		if (userUpdate.isActive()) {
			chain.doFilter(request, response);
		}
		else {
			InformUserForm iuForm = new InformUserForm();
			iuForm.setMsg("This account has been deactivated");
			session.removeAttribute("userObj");
			iuForm.setReturnURL("/AtmStruts/view/user/login.jsp");
			session.setAttribute("iuForm", iuForm);
			res.sendRedirect("/AtmStruts/view/informUser.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
