package fr.devoxx.lepresidentest.entity;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

public class ObjectifyDAO extends DAOBase {
	static {
		ObjectifyService.register(President.class);
		ObjectifyService.register(Counter.class);
	}
}
