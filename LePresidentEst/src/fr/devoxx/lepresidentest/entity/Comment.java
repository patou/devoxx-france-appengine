package fr.devoxx.lepresidentest.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Comment implements Serializable {
	private static final long serialVersionUID = 8930256812773415265L;
	@Id
	Long id;
	Date date;
	public String user;
	public String text;

	public Comment() {
	}

	public Comment(String text, String user) {
		this.user = user;
		this.text = text;
		this.date = new Date();
	}

	public static void store(String text, String user) {
		ObjectifyDAO dao = new ObjectifyDAO();
		dao.ofy().put(new Comment(text, user));
		MemcacheServiceFactory.getMemcacheService().delete("100Comments");
	}

	public static List<Comment> retrieveAll() {
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		@SuppressWarnings("unchecked")
		List<Comment> comments = (List<Comment>) cache.get("100Comments");
		if (comments == null) {
			ObjectifyDAO dao = new ObjectifyDAO();
			comments = dao.ofy().query(Comment.class).order("-date").limit(100).list();
			if (comments != null)
				cache.put("100Comments", comments); // store in memcache
		}
		return comments;
	}
}