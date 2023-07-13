package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "concerti")


@NoArgsConstructor
@ToString
@Getter
@Setter
public class Concerto extends Evento {

	@Enumerated(EnumType.STRING)
	private TipoConcerto genere;

	@Column(name = "in_streaming")
	private boolean isStreaming;

	public Concerto(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			Integer numeroMassimoPartecipanti, Location location, TipoConcerto genere, boolean isStreaming) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
		this.genere = genere;
		this.isStreaming = isStreaming;
	}

}
