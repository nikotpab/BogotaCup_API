package co.edu.unbosque.bogotacup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ARBITRO")
public class Arbitro {
	@Id
	@Column(name = "id_arbitro")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idArbitro;
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	@Column(name = "apellido", nullable = false, length = 50)
	private String apellido;

	public Arbitro() {

	}

	public Arbitro(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Integer getIdArbitro() {
		return idArbitro;
	}

	public void setIdArbitro(Integer idArbitro) {
		this.idArbitro = idArbitro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Arbitro [idArbitro=" + idArbitro + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
