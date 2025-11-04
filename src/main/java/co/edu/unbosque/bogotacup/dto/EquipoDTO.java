package co.edu.unbosque.bogotacup.dto;

import java.util.ArrayList;
import java.util.List;

public class EquipoDTO {

	private Integer idEquipo;
	private String colorSecundario;
	private String directorTecnico;
	private String nombre;
	private String colorPrimario;
	private List<PartidoDTO> partidos = new ArrayList<>();
	private List<UsuarioDTO> usuarios = new ArrayList<>();

	public EquipoDTO() {

	}

	public EquipoDTO(String colorSecundario, String directorTecnico, String nombre, String colorPrimario,
			List<PartidoDTO> partidos, List<UsuarioDTO> usuarios) {
		super();
		this.colorSecundario = colorSecundario;
		this.directorTecnico = directorTecnico;
		this.nombre = nombre;
		this.colorPrimario = colorPrimario;
		this.partidos = partidos;
		this.usuarios = usuarios;
	}
	
	public EquipoDTO(String colorSecundario, String directorTecnico, String nombre, String colorPrimario) {
		super();
		this.colorSecundario = colorSecundario;
		this.directorTecnico = directorTecnico;
		this.nombre = nombre;
		this.colorPrimario = colorPrimario;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getColorSecundario() {
		return colorSecundario;
	}

	public void setColorSecundario(String colorSecundario) {
		this.colorSecundario = colorSecundario;
	}

	public String getDirectorTecnico() {
		return directorTecnico;
	}

	public void setDirectorTecnico(String directorTecnico) {
		this.directorTecnico = directorTecnico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColorPrimario() {
		return colorPrimario;
	}

	public void setColorPrimario(String colorPrimario) {
		this.colorPrimario = colorPrimario;
	}

	public List<PartidoDTO> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<PartidoDTO> partidos) {
		this.partidos = partidos;
	}

	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", colorSecundario=" + colorSecundario + ", directorTecnico="
				+ directorTecnico + ", nombre=" + nombre + ", colorPrimario=" + colorPrimario + "]";
	}

}
