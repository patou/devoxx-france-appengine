package fr.devoxx.lepresidentest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.ObjectifyService;

import fr.devoxx.lepresidentest.entity.President;

/**
 * Tache planifié qui active le Président en cours
 * @author sfeir
 *
 */
@SuppressWarnings("serial")
public class ReleaseDateCron extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//On réccupére le premier Président
		President president = ObjectifyService.begin().query(President.class).get();
		//Si il existe pas, on le crée
		if (president == null) {
			president = new President();
			president.name = "Sarkozy";
			president.image = "/images/sarkozy.png";
		}
		//On active le président
		president.active = true;
		//On enregistre le tout
		ObjectifyService.begin().put(president);
	}
}
