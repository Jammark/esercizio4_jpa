package model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partecipazioni")
@Getter
@Setter
@NoArgsConstructor
public class Partecipazione {

	@Id
	@SequenceGenerator(name = "seq_part", sequenceName = "partecipazioni_sequence", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_part")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
	private Persona persona;

	@ManyToOne
	@JoinColumn(name = "evento_id", referencedColumnName = "id", nullable = false)
	@Cascade({ CascadeType.PERSIST, CascadeType.DELETE })
	private Evento evento;

	@Enumerated(EnumType.STRING)
	private StatoPartecipazione stato;

	public Partecipazione(Persona persona, Evento evento, StatoPartecipazione stato) {
		super();
		this.persona = persona;
		this.evento = evento;
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Partecipazione [id=" + id + ", persona=" + persona.getId() + ", evento=" + evento.getId() + ", stato="
				+ stato + "]";
	}

}
