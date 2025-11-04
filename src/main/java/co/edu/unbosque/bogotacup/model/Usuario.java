package co.edu.unbosque.bogotacup.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "rol", nullable = false, length = 50)
    private String rol;

    @Column(name = "clave", nullable = false, length = 255)
    private String clave;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "numero_camiseta", nullable = false)
    private Integer numeroCamiseta;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @ManyToMany
    @JoinTable(name = "USUARIO-EQUIPO", joinColumns = @JoinColumn(name = "USUARIO_id_usuario"), inverseJoinColumns = @JoinColumn(name = "EQUIPO_id_equipo"))
    @JsonManagedReference
    private List<Equipo> equipos = new ArrayList<>();

    public Usuario() {

    }

    public Usuario(String rol, String clave, String correo, String nombre, Integer numeroCamiseta,
                   LocalDate fechaNacimiento, String apellido, List<Equipo> equipos) {
        super();
        this.rol = rol;
        this.clave = clave;
        this.correo = correo;
        this.nombre = nombre;
        this.numeroCamiseta = numeroCamiseta;
        this.fechaNacimiento = fechaNacimiento;
        this.apellido = apellido;
        this.equipos = equipos;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(Integer numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        this.equipos.add(equipo);
        equipo.getUsuarios().add(this);
    }

    public void removerEquipo(Equipo equipo) {
        this.equipos.remove(equipo);
        equipo.getUsuarios().remove(this);
    }

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", rol=" + rol + ", clave=" + clave + ", correo=" + correo
                + ", nombre=" + nombre + ", numeroCamiseta=" + numeroCamiseta + ", fechaNacimiento=" + fechaNacimiento
                + ", apellido=" + apellido + "]";
    }

}

