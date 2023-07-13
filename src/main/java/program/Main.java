package program;

import java.time.LocalDate;
import java.util.Scanner;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Evento;
import model.Location;
import model.Partecipazione;
import model.Persona;
import model.Sesso;
import model.StatoPartecipazione;
import model.TipoEvento;
import util.EventoDAO;
import util.JpaUtil;
import util.LocationDAO;
import util.PartecipazioneDAO;
import util.PersonaDAO;

public class Main {

	private static final Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		Persona p1 = new Persona("Mario", "Rossi", "mario.rossi@gmail.com", LocalDate.of(1980, 10, 3), Sesso.MASCHIO);
		Persona p2 = new Persona("Maria", "Valle", "maria.valle@gmail.com", LocalDate.of(1982, 11, 5), Sesso.FEMMINA);

		Location l1 = new Location("Colosseo", "Roma");
		Location l2 = new Location("porto", "Napoli");

		Evento ev1 = new Evento("Evento 4", LocalDate.of(2001, 6, 11), "Quarto evento", TipoEvento.PRIVATO, 60, l1);
		Evento ev2 = new Evento("Evento 5", LocalDate.of(2000, 8, 11), "Quinto evento", TipoEvento.PUBBLICO, 160, l2);



		EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();

		EventoDAO dao = new EventoDAO(em);
		PersonaDAO daoP = new PersonaDAO(em);
		LocationDAO daoL = new LocationDAO(em);
		PartecipazioneDAO daoPart = new PartecipazioneDAO(em);

		Scanner sc = new Scanner(System.in);

		try {
			dao.save(ev1);
			dao.save(ev2);

			Partecipazione part1 = new Partecipazione(p1, ev1, StatoPartecipazione.CONFERMATA);
			Partecipazione part2 = new Partecipazione(p1, ev2, StatoPartecipazione.DA_CONFERMARE);
			log.info("Lettura tabella eventi: " + dao.getById(ev2.getId()));

			daoP.save(p2);
			daoP.save(p1);

			daoPart.save(part2);
			daoPart.save(part1);

			dao.refresh(ev1);
			dao.refresh(ev2);
			daoP.refresh(p1);

			log.info("eventi: " + ev1);
			log.info("eventi: " + ev2);

			p1.getListaPartecipazioni().forEach(p -> log.info("Partecipazione: " + p));

			log.info("Inserire...");
			sc.nextLine();
			// dao.delete(ev1);
		} finally {
			sc.close();
			dao.close();
			JpaUtil.close();
		}

	}

}
