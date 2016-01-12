package tair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tair.TairHandler;

public class GetTairServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -7130991631942693028L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		TairHandler tairHandler = (TairHandler) context.getBean("tairHandler");
		String key = req.getParameter("key");
		PrintWriter out = resp.getWriter();
		out.println(tairHandler.getTair(key));
		return;
	}
}
