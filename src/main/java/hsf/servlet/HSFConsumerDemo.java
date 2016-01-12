package hsf.servlet;

import hsf.HSFDemoService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class HSFConsumerDemo extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 8547896360069299006L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		HSFDemoService hsfDemoServiceConsumer = (HSFDemoService) context.getBean("hsfDemoServiceConsumer");
		String name = req.getParameter("name");
		PrintWriter out = resp.getWriter();
		out.println(hsfDemoServiceConsumer.sayHello(name));
		return;
	}
}