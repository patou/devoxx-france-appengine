package fr.devoxx.lepresidentest.entity;

import java.util.List;

import javax.persistence.Id;

import com.google.appengine.api.blobstore.BlobKey;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class President {
	@Id
	public Long id = null;
	public String name;
	public BlobKey images;
	public boolean active;
	
	 
	static {
	    ObjectifyService.register(President.class);
	}

	public static President getPresident() {
		List<President> list = ObjectifyService.begin().query(President.class).filter("active", true).limit(1).list();
		if (list.size() == 1)
			return list.get(0);
		return null;
	}
}
