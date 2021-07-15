package com.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
//@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.jsf"})
public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		  try {
	            // check whether session variable is set
	            HttpServletRequest req = (HttpServletRequest) request;
	            HttpServletResponse res = (HttpServletResponse) response;
	            HttpSession ses = req.getSession(true);
	            //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
	            String reqURI = req.getRequestURI();
	            if(ses.getAttribute("username")!=null && reqURI.contains("login.jsf") && ses.getAttribute("username").equals("admin"))
	            {
	                 res.sendRedirect(req.getContextPath() + "/Dashboard.jsf");
	            }else if(ses.getAttribute("username")!=null && reqURI.contains("login.jsf") && ses.getAttribute("username").equals("Employee")) {
	            	 res.sendRedirect(req.getContextPath() + "/Orphanage.jsf");
	            }else if(ses.getAttribute("username")!=null && reqURI.contains("login.jsf") && ses.getAttribute("username").equals("Applicant")) {
	            	 res.sendRedirect(req.getContextPath() + "/ApplicantApply.jsf");
	            }

	            if ( reqURI.indexOf("/login.jsf") >= 0 || (ses != null && ses.getAttribute("username") != null)
	                                       || reqURI.indexOf("/resources/") >= 0 || reqURI.indexOf("/ApplicantSign.jsf") >= 0 
	                                       || reqURI.indexOf("/Simple.jsf") >= 0 || reqURI.contains("javax.faces.resource") ) {
	                   chain.doFilter(request, response);
	            } 
	            else   // user didn't log in but asking for a page that is not allowed so take user to login page
	                   res.sendRedirect(req.getContextPath() + "/login.jsf");  // Anonymous user. Redirect to login page
	      }
	     catch(Throwable t) {
	         System.out.println( t.getMessage());
	     }


	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
