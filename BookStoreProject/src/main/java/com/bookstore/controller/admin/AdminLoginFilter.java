package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLoginFilter() {
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
		System.out.println("AdminLoginFilter is invoked.");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false); //如果没有这个session的话，也不会create，反之则会create一个new session 
		boolean loggedIn = session != null && session.getAttribute("useremail") != null ; //‼️useremail居然为null
		// 第一次登录是null是ok的，只要从login page authenticated了就可以；后面再access别的页面就有这个信息 就可以了  
		String loginURI = httpRequest.getContextPath()+"/admin/login";
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		// 如果是login request 或者是jsp的话 只要是logged in的还是去admin主页 
		if(loggedIn && (loginRequest||loginPage)) {
			System.out.print("first");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		}
		//得加上loginRequest这个判断，不然filter把login本身也给filter了；确保了从login就可以直接登陆 
		// 出问题这一块主要是 path的问题 
		// 如果确保了logged in  就可以去任何request的地方 
		else if (loggedIn||loginRequest) {
			System.out.print("logged in");
			chain.doFilter(request, response);
		}else {
			// 没有logged in  就都在login页面 
			System.out.print("not logged");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

		// pass the request along the filter chain
//		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
