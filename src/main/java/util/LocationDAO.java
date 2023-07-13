package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Location;

public class LocationDAO {
	private static final Logger log = LoggerFactory.getLogger(EventoDAO.class);

	private final EntityManager em;

	public LocationDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Location l) {
		EntityTransaction t = this.em.getTransaction();
		try {
			t.begin();
			em.persist(l);

			t.commit();

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void delete(Location l) {
		EntityTransaction t = this.em.getTransaction();
		try {
			t.begin();
			em.remove(l);

			t.commit();

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public Location getById(Long id) {
		return em.find(Location.class, id);
	}

	public void refresh(Location l) {
		em.refresh(l);
	}

	public void close() {
		this.em.close();
	}
}
