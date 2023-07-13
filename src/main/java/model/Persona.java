package model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "persone")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Persona {

	@Id
	@SequenceGenerator(name = "seq_p", sequenceName = "persone_sequence", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_p")
	private Long id;

	private String nome, cognome, email;

	@Column(name = "data_di_nascita")
	private LocalDate dataDiNascita;

	@Enumerated(EnumType.STRING)
	private Sesso sesso;

	@OneToMany(mappedBy = "persona")
	@OrderColumn(name = "eventi.dataevento")
	private Set<Partecipazione> listaPartecipazioni;

	public Persona(String nome, String cognome, String email, LocalDate dataDiNascita, Sesso sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.dataDiNascita = dataDiNascita;
		this.sesso = sesso;

	}

}
