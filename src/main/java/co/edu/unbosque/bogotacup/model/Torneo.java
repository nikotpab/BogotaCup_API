package co.edu.unbosque.bogotacup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TORNEO")
public class Torneo {
	@Id
	@Column(name = "id_torneo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTorneo;
	@Column(name = "tipo", nullable = false, length = 50)
	private String tipo;
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;
	@Column(name = "categoria", nullable = false, length = 50)
	private String categoria;
	@Column(name = "estado", nullable = false, length = 1)
	private Character estado;
	@Column(name = "anio", nullable = false)
	private Integer anio;
	@ManyToOne
	@JoinColumn(name = "USUARIO_id_usuario", nullable = false)
	private Usuario usuario;

	public Torneo() {
		
	}

	public Torneo(String tipo, String nombre, String categoria, Character estado, Integer anio,
			Usuario usuario) {
		this.tipo = tipo;
		this.nombre = nombre;
		this.categoria = categoria;
		this.estado = estado;
		this.anio = anio;
		this.usuario = usuario;
	}

	public Integer getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(Integer idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Torneo [idTorneo=" + idTorneo + ", tipo=" + tipo + ", nombre=" + nombre + ", categoria=" + categoria
				+ ", estado=" + estado + ", anio=" + anio + ", usuario=" + usuario + "]";
	}
	
}
