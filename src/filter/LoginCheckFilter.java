	package filter;
	
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
	 * Servlet Filter implementation class LoginCheckFilter
	 */
	//@WebFilter("/LoginCheckFilter")
	public class LoginCheckFilter implements Filter {
	
	    /**
	     * Default constructor. 
	     */
	    public LoginCheckFilter() {
	    }
	
		/**
		 * @see Filter#destroy()
		 */
		public void destroy() {
		}
	
		/**
		 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
		 */
		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
			// place your code here
			HttpServletRequest request =(HttpServletRequest) req;
			HttpSession session= request .getSession(false);
			if(session == null || session.getAttribute("authUser")==null) {
				HttpServletResponse response = (HttpServletResponse) res;
				response.sendRedirect(request.getContextPath() + "/login.do");
			} else {
			// pass the request along the filter chain
				chain.doFilter(req, res);
			}
		}
	
		/**
		 * @see Filter#init(FilterConfig)
		 */
		public void init(FilterConfig fConfig) throws ServletException {
		}
	
	}
