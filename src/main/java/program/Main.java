package program;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;

import lombok.extern.slf4j.Slf4j;
import model.Concerto;
import model.Evento;
import model.GaraDiAtletica;
import model.Location;
import model.Partecipazione;
import model.PartitaDiCalcio;
import model.Persona;
import model.Sesso;
import model.StatoPartecipazione;
import model.TipoConcerto;
import model.TipoEvento;
import util.EventoDAO;
import util.JpaUtil;
import util.LocationDAO;
import util.PartecipazioneDAO;
import util.PersonaDAO;

@Slf4j
public class Main {

	// private static final Logger log = LoggerFactory.getLogger(Main.class);

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
			daoL.save(l2);
			daoL.save(l1);
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
			dao.delete(ev1);

			populateDB(em);
			dao.getConcertiInStreaming().forEach(c -> log.info("Concerto in streamin: " + c));
			dao.getConcertiPerGenere(Arrays.asList(TipoConcerto.CLASSICO, TipoConcerto.ROCK))
					.forEach(c -> log.info("Concerto per genere: " + c));
		} finally {
			sc.close();
			dao.close();
			JpaUtil.close();
		}

	}

	public static void populateDB(EntityManager em) {
		Location l1 = new Location("stadio Olimpico", "Roma");

		PartitaDiCalcio partita1 = new PartitaDiCalcio("Evento 1", LocalDate.of(2001, 6, 11), "Roma - Milan",
				TipoEvento.PRIVATO, 600, l1, "Milan", "Roma", "Roma", 3, 1);
		PartitaDiCalcio partita2 = new PartitaDiCalcio("Evento 2", LocalDate.of(2002, 6, 11), "Lazio - Milan",
				TipoEvento.PRIVATO, 600, l1, "Milan", "Lazio", "Lazio", 3, 1);

		Persona p1 = new Persona("Mario", "Di Carlo", "mario.dicarlo@gmail.com", LocalDate.of(1985, 12, 3),
				Sesso.MASCHIO);
		Persona p2 = new Persona("Maria", "Di Bella", "maria.dibella@gmail.com", LocalDate.of(1989, 8, 5),
				Sesso.FEMMINA);

		GaraDiAtletica gara1 = new GaraDiAtletica("Evento 11", LocalDate.of(2006, 6, 11), "evento atletica",
				TipoEvento.PRIVATO, 460, l1, p2, Set.of(p1, p2));
		Concerto concerto = new Concerto("concerto 1", LocalDate.of(2012, 5, 11), "Orchestra sinfonica",
				TipoEvento.PRIVATO, 600, l1, TipoConcerto.CLASSICO, false);
		Concerto concerto2 = new Concerto("concerto 2", LocalDate.of(2022, 9, 11), "Lunapop", TipoEvento.PRIVATO, 600,
				l1, TipoConcerto.POP, true);
		EventoDAO dao = new EventoDAO(em);

		LocationDAO daoL = new LocationDAO(em);
		daoL.save(l1);

		PersonaDAO daoP = new PersonaDAO(em);
		daoP.save(p2);
		daoP.save(p1);

		dao.save(concerto);
		dao.save(concerto2);
		dao.save(gara1);
		dao.save(partita2);
		dao.save(partita1);
	}

}
