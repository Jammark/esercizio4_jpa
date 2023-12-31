package model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "gare")

@NoArgsConstructor

@ToString
@Getter
@Setter
@NamedQuery(name = "garePerVincitore", query = "SELECT g FROM GaraDiAtletica g WHERE g.vincitore.id = :id")
@NamedQuery(name = "garePerPartecipante", query = "SELECT g FROM GaraDiAtletica g WHERE :id IN (SELECT p.id FROM Persona p WHERE p IN g.atleti)")
public class GaraDiAtletica extends Evento {

	@ManyToOne
	private Persona vincitore;

	@ManyToMany
	@JoinTable(name = "gare_atleti", joinColumns = @JoinColumn(name = "gara_id"), inverseJoinColumns = @JoinColumn(name = "atleta_id"))
	private Set<Persona> atleti;

	public GaraDiAtletica(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			Integer numeroMassimoPartecipanti, Location location, Persona vincitore, Set<Persona> atleti) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
		this.vincitore = vincitore;
		this.atleti = atleti;
	}

}
