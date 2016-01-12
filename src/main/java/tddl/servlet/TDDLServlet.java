package tddl.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tddl.TDDLDemo;

public class TDDLServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -2414131336030274055L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		TDDLDemo tddlDemo = (TDDLDemo) context.getBean("tddlDemo");
		String sql = req.getParameter("sql");
		try {
			String resultJson = tddlDemo.connectVFS(sql);
			PrintWriter out = resp.getWriter();
			out.println(resultJson);
		} catch (SQLException e) {
			throw new IllegalArgumentException("please check your sql." + e, e);
		}
	}
}
