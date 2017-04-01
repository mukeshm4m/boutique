package com.boutique.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			String context = request.getContextPath();
			if (context.length() == 0) {
				context += "/";
			}

			String modifiedContext = getModifiedContext(request);

			String serverHome = getProtocol(request) + request.getServerName() + ":" + request.getServerPort();
			String serverHomeWithContext = getProtocol(request) + request.getServerName() + ":" + request.getServerPort() + context;

			if (!serverHome.endsWith("/")) {
				serverHome += "/";
			}

			if (!serverHomeWithContext.endsWith("/")) {
				serverHomeWithContext += "/";
			}
			HttpSession session = request.getSession(true);

			String uri = request.getRequestURI();

			response.setHeader("X-UA-Compatible", "IE=9");

			if (modifiedContext.equals(uri) || uri.contains(".xhtml")) {

				if (session.getAttribute("loginUser") != null && uri.endsWith("index.xhtml") || modifiedContext.equals(uri)) {
					if (session.getAttribute("isAdmin") != null) {
						response.sendRedirect(serverHomeWithContext + "private/admin/productManagement.xhtml");
					} else {
						response.sendRedirect(serverHomeWithContext + "private/cashier/shoppingCart.xhtml");
					}
					return;
				}

				if (session.getAttribute("loginUser") == null && uri.contains("private/")) {
					redirectPage(request, response, serverHomeWithContext + "index.xhtml");
					return;
				}
			}

			filterChain.doFilter(servletRequest, servletResponse);

		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	private void redirectPage(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {

		try {
			String facesRequest = request.getHeader("Faces-Request");

			if (facesRequest != null && facesRequest.equals("partial/ajax")) {

				PrintWriter pw = response.getWriter();

				pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				pw.println("<partial-response><redirect url=\"" + page + "\"></redirect></partial-response>");
				pw.flush();

			} else {
				response.sendRedirect(page);
			}

		} catch (IOException e) {
			throw e;
		}
	}

	private String getProtocol(HttpServletRequest request) {

		String requestURL = request.getRequestURL().toString();
		return requestURL.substring(0, requestURL.indexOf(":")) + "://";
	}

	public static String getModifiedContext(HttpServletRequest request) {
		String modifiedContext = "/boutique/";

		return modifiedContext;
	}
}
