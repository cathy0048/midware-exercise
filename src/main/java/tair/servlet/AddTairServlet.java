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

public class AddTairServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 5142822712993805766L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		TairHandler tairHandler = (TairHandler) context.getBean("tairHandler");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		PrintWriter out = resp.getWriter();
		if (tairHandler.addTair(key, value)) {
			out.println("add tair success");
		} else {
			out.println("add tair failed");
		}

		return;
	}
}
