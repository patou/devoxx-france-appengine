package fr.devoxx.lepresidentest;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 4146977531107161426L;
	Logger log = Logger.getLogger(VoteServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String vote = req.getPathInfo();
		if ("-1".equals(vote)) {
			log.info("vote -1");
			System.out.println("vode -1");
		}
		if ("+1".equals(vote)) {
			log.info("vote +1");
			System.out.println("vode +1");
		}
		resp.sendRedirect("/index.jsp");
	}
}
