package co.edu.unbosque.bogotacup.dto;

public class CanchaDTO {

	private Integer idCancha;
	private String direccion;
	private String nombre;

	public CanchaDTO() {

	}

	public CanchaDTO(String direccion, String nombre) {
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
