package fr.devoxx.lepresidentest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import fr.devoxx.lepresidentest.entity.President;

public class CronServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119453875625910502L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<President> list = ObjectifyService.begin().query(President.class).limit(1).list();
		if (list.size() == 1) {
			President president = list.get(0);
			president.active = true;
			ObjectifyService.begin().put(president);
		}
	}
}
