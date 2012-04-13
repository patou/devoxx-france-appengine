package fr.devoxx.lepresidentest.entity;

import java.util.Random;

import javax.persistence.Id;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

public class Counter {
	public Counter() {
	}

	public Counter(String name, Integer shard, Long value)
	{
		this.name = name;
		this.value = value;
		key = name + "_shard" + shard.toString();
	}

	public static void increment(String name)
	{
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		Long val = cache.increment(name, 1);
		if (val == null)
		{
			val = Counter.read(name);
			if (val == null)
				val = cache.increment(name, 1, 0L); // creates the memcache entry if it does not exist
			else
				val++;
		}
		Counter.write(name, val);
	}

	public static Long value(String name)
	{
		MemcacheService cache = MemcacheServiceFactory.getMemcacheService();
		Long val = (Long) cache.get(name);
		if (val == null)
		{
			val = Counter.read(name);
			cache.put(name, val);
		}
		return val;
	}

	static Long read(String name)
	{
		ObjectifyDAO dao = new ObjectifyDAO();
		Counter c = dao.ofy().query(Counter.class).filter("name", name).order("-value").get();
		return (c == null ? 0L : c.value);
	}

	static void write(String name, Long value)
	{
		Random r = new Random();
		Integer shard = r.nextInt(10);
		ObjectifyDAO dao = new ObjectifyDAO();
		dao.ofy().put(new Counter(name, shard, value));
	}

	@Id
	String key;
	String name;
	Long value;
}