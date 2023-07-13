package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Partecipazione;

public class PartecipazioneDAO {
	private static final Logger log = LoggerFactory.getLogger(EventoDAO.class);

	private final EntityManager em;

	public PartecipazioneDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Partecipazione p) {
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

	public void delete(Partecipazione p) {
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

	public Partecipazione getById(Long id) {
		return em.find(Partecipazione.class, id);
	}

	public void refresh(Partecipazione p) {
		em.refresh(p);
	}

	public void close() {
		this.em.close();
	}
}
