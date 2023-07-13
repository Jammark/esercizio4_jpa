package model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "partite")
@Entity


@NoArgsConstructor
@ToString
@Getter
@Setter
@NamedQuery(name = "partiteVinteInCasa", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraDiCasa = p.squadraVincente")
@NamedQuery(name = "partiteVinteInTrasferta", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite")
@NamedQuery(name = "partitePareggiate", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente IS NULL")
public class PartitaDiCalcio extends Evento {

	@Column(name = "squadra_ospite", nullable = false)
	private String squadraOspite;
	@Column(name = "squadra_di_casa", nullable = false)
	private String squadraDiCasa;
	@Column(name = "squadra_vincente", nullable = true)
	private String squadraVincente;
	@Column(name = "numero_gol_sdcasa")
	private Integer numeroGolSquadraDiCasa;
	@Column(name = "numero_gol_sospite")
	private Integer numeroGolSquadraOspite;

	public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			Integer numeroMassimoPartecipanti, Location location, String squadraOspite, String squadraDiCasa,
			String squadraVincente, Integer numeroGolSquadraDiCasa, Integer numeroGolSquadraOspite) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimoPartecipanti, location);
		this.squadraOspite = squadraOspite;
		this.squadraDiCasa = squadraDiCasa;
		this.squadraVincente = squadraVincente;
		this.numeroGolSquadraDiCasa = numeroGolSquadraDiCasa;
		this.numeroGolSquadraOspite = numeroGolSquadraOspite;
	}

}
