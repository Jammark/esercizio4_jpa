package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "location")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Location {

	@Id
	@SequenceGenerator(name = "seq_l", sequenceName = "location_sequence", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_l")
	private Long id;

	private String nome, città;

	public Location(String nome, String città) {
		super();
		this.nome = nome;
		this.città = città;
	}

}
