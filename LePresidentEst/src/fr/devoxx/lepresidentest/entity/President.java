package fr.devoxx.lepresidentest.entity;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Unindexed;

/**
 * Une entité qui stocke le nom et l'image du président.
 * Un Boolean active permet d'indiquer si le Président doit être affiché ou pas
 * @author sfeir
 *
 */
public class President {
	@Id
	public Long id = null;
	public String name;
	@Unindexed
	public String image;
	public boolean active;
	

	/**
	 * Récupère le Président actif
	 * @return le Président actif ou null si aucun Président actif
	 */
	public static President getPresident() {
		return new ObjectifyDAO().ofy().query(President.class).filter("active", true).get();
	}
}
