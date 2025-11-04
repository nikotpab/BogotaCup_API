package co.edu.unbosque.bogotacup.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "EQUIPO")
public class Equipo {
    @Id
    @Column(name = "id_equipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipo;
    @Column(name = "color_secundario", nullable = false, length = 50)
    private String colorSecundario;
    @Column(name = "director_tecnico", nullable = false, length = 50)
    private String directorTecnico;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Column(name = "color_primario", nullable = false, length = 50)
    private String colorPrimario;

    @ManyToMany
    @JoinTable(name = "EQUIPO-PARTIDO", joinColumns = @JoinColumn(name = "EQUIPO_id_equipo"), inverseJoinColumns = @JoinColumn(name = "PARTIDO_id_partido"))
    @JsonManagedReference
    private List<Partido> partidos = new ArrayList<>();

    @ManyToMany(mappedBy = "equipos")
    @JsonBackReference
    private List<Usuario> usuarios = new ArrayList<>();

    public Equipo() {

    }

    public Equipo(String colorSecundario, String directorTecnico, String nombre, String colorPrimario,
                  List<Partido> partidos, List<Usuario> usuarios) {
        super();
        this.colorSecundario = colorSecundario;
        this.directorTecnico = directorTecnico;
        this.nombre = nombre;
        this.colorPrimario = colorPrimario;
        this.partidos = partidos;
        this.usuarios = usuarios;
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

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void agregarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.getEquipos().add(this);
    }

    public void removerUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.getEquipos().remove(this);
    }

    public void agregarPartido(Partido partido) {
        this.partidos.add(partido);
        partido.getEquipos().add(this);
    }

    public void removerPartido(Partido partido) {
        this.partidos.remove(partido);
        partido.getEquipos().remove(this);
    }

    @Override
    public String toString() {
        return "Equipo [idEquipo=" + idEquipo + ", colorSecundario=" + colorSecundario + ", directorTecnico="
                + directorTecnico + ", nombre=" + nombre + ", colorPrimario=" + colorPrimario + "]";
    }

}

