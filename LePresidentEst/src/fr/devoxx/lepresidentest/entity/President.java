package fr.devoxx.lepresidentest.entity;
import javax.persistence.Id;


public class President {
	@Id
	public Long id = null;
	public String name;
	public String image;
	public boolean active;
	

	public static President getPresident() {
		return new ObjectifyDAO().ofy().query(President.class).filter("active", true).get();
	}
}
