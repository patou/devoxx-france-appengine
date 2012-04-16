package fr.devoxx.lepresidentest;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.devoxx.lepresidentest.entity.Counter;

/**
 * Incrémente le vote
 * L'URL est de la forme /vote/nom_compteur (IE : /vote/+1 et /vote/-1)
 * Incrémente le compteur en fonction de son nom, puis redirige vers la première page
 * @author sfeir
 *
 */
@SuppressWarnings("serial")
public class VoteServlet extends HttpServlet {
	Logger log = Logger.getLogger(VoteServlet.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String vote = req.getPathInfo().substring(1);
		Counter.increment(vote);
		resp.sendRedirect("/");
	}
}
