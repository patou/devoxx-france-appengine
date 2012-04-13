package fr.devoxx.lepresidentest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import fr.devoxx.lepresidentest.entity.President;

public class ReleaseDateCron extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4119453875625910502L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		President president = new President();
		president.name = "Sarkozy";
		president.image = "/images/sarkozy.png";
		ObjectifyService.begin().put(president);
//		List<President> list = ObjectifyService.begin().query(President.class).limit(1).list();
//		if (list.size() == 1) {
//			President president = list.get(0);
//			president.active = true;
//			ObjectifyService.begin().put(president);
//		}
	}
}
