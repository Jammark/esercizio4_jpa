package model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "eventi")
//@DiscriminatorColumn(name = "tipo_evento", discriminatorType = DiscriminatorType.STRING, columnDefinition = "varchar default 'Nessuna'", length = 20)
@Inheritance(strategy = InheritanceType.JOINED)

@NamedQuery(name = "eventiSoldOut", query = "SELECT e FROM Evento e WHERE e.numeroMassimoPartecipanti = (SELECT COUNT(p) FROM Evento e1 INNER JOIN e1.partecipazioni p ON e1.id = p.evento.id WHERE e1.id = e.id)")
public class Evento {

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String titolo;

	@Column
	private LocalDate dataEvento;

	@Column
	private String descrizione;

	@Enumerated(EnumType.STRING)
	private TipoEvento tipoEvento;

	@Column
	private Integer numeroMassimoPartecipanti;

	@ManyToOne
	@Cascade(CascadeType.MERGE)
	private Location location;

	@OneToMany(mappedBy = "evento")
	@Cascade({ CascadeType.PERSIST, CascadeType.DELETE })
	private Set<Partecipazione> partecipazioni;

	public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			Integer numeroMassimoPartecipanti) {
		this.titolo = titolo;
		this.dataEvento = dataEvento;
		this.descrizione = descrizione;
		this.tipoEvento = tipoEvento;
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
	}

	public Evento(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			Integer numeroMassimoPartecipanti, Location location) {
		super();
		this.titolo = titolo;
		this.dataEvento = dataEvento;
		this.descrizione = descrizione;
		this.tipoEvento = tipoEvento;
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
		this.location = location;
	}

	public Evento() {
		super();
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(LocalDate dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Integer getNumeroMassimoPartecipanti() {
		return numeroMassimoPartecipanti;
	}

	public void setNumeroMassimoPartecipanti(Integer numeroMassimoPartecipanti) {
		this.numeroMassimoPartecipanti = numeroMassimoPartecipanti;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", titolo=" + titolo + ", dataEvento=" + dataEvento + ", descrizione=" + descrizione
				+ ", tipoEvento=" + tipoEvento + ", numeroMassimoPartecipanti=" + numeroMassimoPartecipanti + "]";
	}

}
