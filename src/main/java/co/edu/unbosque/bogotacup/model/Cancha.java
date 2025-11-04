package co.edu.unbosque.bogotacup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CANCHA")
public class Cancha {
	@Id
	@Column(name = "id_cancha")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCancha;
	@Column(name = "direccion", nullable = false, length = 50)
	private String direccion;
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	public Cancha() {

	}

	public Cancha(String direccion, String nombre) {
		super();
		this.direccion = direccion;
		this.nombre = nombre;
	}

	public Integer getIdCancha() {
		return idCancha;
	}

	public void setIdCancha(Integer idCancha) {
		this.idCancha = idCancha;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cancha [idCancha=" + idCancha + ", direccion=" + direccion + ", nombre=" + nombre + "]";
	}

}
