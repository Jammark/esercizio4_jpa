package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Persona;

public class PersonaDAO {
	private static final Logger log = LoggerFactory.getLogger(EventoDAO.class);

	private final EntityManager em;

	public PersonaDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Persona p) {
		EntityTransaction t = this.em.getTransaction();
		try {
			t.begin();
			em.persist(p);

			t.commit();

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void delete(Persona p) {
		EntityTransaction t = this.em.getTransaction();
		try {
			t.begin();
			em.remove(p);

			t.commit();

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public Persona getById(Long id) {
		return em.find(Persona.class, id);
	}

	public void refresh(Persona p) {
		em.refresh(p);
	}

	public void close() {
		this.em.close();
	}
}
