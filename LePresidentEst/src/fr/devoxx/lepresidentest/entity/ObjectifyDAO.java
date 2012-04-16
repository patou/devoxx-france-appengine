package fr.devoxx.lepresidentest.entity;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

/**
 * DAO Objectify
 * @author sfeir
 *
 */
public class ObjectifyDAO extends DAOBase {
	static {
		ObjectifyService.register(Comment.class);
		ObjectifyService.register(President.class);
		ObjectifyService.register(Counter.class);
	}
}
