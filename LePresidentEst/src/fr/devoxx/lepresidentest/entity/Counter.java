package fr.devoxx.lepresidentest.entity;

import java.util.Random;

import javax.persistence.Id;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

/**
 * Counter partagé.
 * @author sfeir
 *
 */
public class Counter {
	public Counter() {
	}

	public Counter(String name, Integer shard, Long value)
	{
		this.name = name;
		this.value = value;
		key = name + "_shard" + shard.toString();
	}

	/**
	 * Incrémente le compteur directement dans la mémoire cache, puis stocke la valeur dans la base
	 * @param name
	 */
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

	/**
	 * Récupère la valeur du compteur
	 * Regarde si la valeur est dans le cache mémoire sinon va calculer la valeur du compteur et la stocker dans le cache 
	 * @param name
	 * @return
	 */
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

	/**
	 * On cherche le compteur du nom donnée avec la valeur maximum.
	 * @param name
	 * @return
	 */
	static Long read(String name)
	{
		ObjectifyDAO dao = new ObjectifyDAO();
		Counter c = dao.ofy().query(Counter.class).filter("name", name).order("-value").get();
		return (c == null ? 0L : c.value);
	}

	/**
	 * Stocke la valeur dans un compteur en choisissant un compteur aléatoire sur les 10 compteurs
	 * @param name
	 * @param value
	 */
	static void write(String name, Long value)
	{
		Random r = new Random();
		Integer shard = r.nextInt(10);
		ObjectifyDAO dao = new ObjectifyDAO();
		dao.ofy().put(new Counter(name, shard, value));
	}

	/**
	 * Clé du compteur composé du nom et du numéro aléatoire
	 */
	@Id
	String key;
	/**
	 * Nom du compteur
	 */
	String name;
	/**
	 * Valeur du compteur
	 */
	Long value;
}