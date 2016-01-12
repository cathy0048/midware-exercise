package tbssesion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taobao.session.SessionKeyConstants;

public class SecurityCheckValveServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 4093903998302822255L;
	private static final String rootDomain = "daily.taobao.net";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean logined = "true".equals(req.getSession().getAttribute(SessionKeyConstants.ATTRIBUTE_LOGIN));

		// 检查是否已经登录，如果没有，则直接跳转到登录页面，其中rootDomain为autoconfig配置，线下为:daily.taobao.net，线上为taobao.com
		if (!logined) {
			String redirectUrl = "http://login." + rootDomain + "/member/login.jhtml?redirectURL=";
			redirectUrl = redirectUrl + URLEncoder.encode("http://jbm." + rootDomain + req.getRequestURI(), "UTF-8");
			resp.sendRedirect(redirectUrl);
			return;
		}
		// 如果已经登录，则可以读取到用户的id。用于其他信息可以接入UIC读取
		String userName = (String) req.getSession().getAttribute(SessionKeyConstants.ATTRIBUTE_NICK);
		PrintWriter out = resp.getWriter();
		out.println(userName);
	}
}
