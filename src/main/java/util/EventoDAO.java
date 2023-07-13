package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Concerto;
import model.Evento;
import model.TipoConcerto;

public class EventoDAO {

	private static final Logger log = LoggerFactory.getLogger(EventoDAO.class);

	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento ev) {
		EntityTransaction t = this.em.getTransaction();
		try {
			t.begin();
			em.persist(ev);

			t.commit();

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void delete(Evento ev) {
		EntityTransaction t = this.em.getTransaction();
		try {
			t.begin();
			em.remove(ev);

			t.commit();

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public Evento getById(Long id) {
		return em.find(Evento.class, id);
	}

	public void refresh(Evento ev) {
		em.refresh(ev);
	}

	public List<Concerto> getConcertiPerGenere(List<TipoConcerto> val) {
		TypedQuery<Concerto> query = em.createQuery("SELECT c FROM Concerto c WHERE c.genere IN :lista",
				Concerto.class);
		query.setParameter("lista", val);
		return query.getResultList();
	}

	public List<Concerto> getConcertiInStreaming() {
		TypedQuery<Concerto> query = em.createQuery("SELECT c FROM Concerto c WHERE c.isStreaming = true",
				Concerto.class);
		return query.getResultList();
	}

	public void close() {
		this.em.close();
	}

}
