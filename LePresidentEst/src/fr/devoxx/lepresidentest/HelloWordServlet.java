package fr.devoxx.lepresidentest;

import java.io.IOException;
import javax.servlet.http.*;

/**
 * Servlet Hello word
 * @author sfeir
 *
 */
@SuppressWarnings("serial")
public class HelloWordServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
