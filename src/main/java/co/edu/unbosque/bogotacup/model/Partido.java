package co.edu.unbosque.bogotacup.model;

import java.time.LocalDate;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PARTIDO")
public class Partido {
    @Id
    @Column(name = "id_partido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPartido;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @Column(name = "estadio_partido", nullable = false, length = 50)
    private String estadioPartido;
    @Column(name = "goles_visitante", nullable = false)
    private Integer golesVisitante;
    @Column(name = "goles_local", nullable = false)
    private Integer golesLocal;
    @Column(name = "tiempo_extra", nullable = false)
    private Integer tiempoExtra;
    @Column(name = "penales_visitante", nullable = false)
    private Integer penalesVisitante;
    @Column(name = "penales_local", nullable = false)
    private Integer penalesLocal;

    @ManyToOne
    @JoinColumn(name = "ARBITRO_id_arbitro", nullable = false)
    private Arbitro arbitro;

    @ManyToOne
    @JoinColumn(name = "CANCHA_id_cancha", nullable = false)
    private Cancha cancha;

    @ManyToMany(mappedBy = "partidos")
    @JsonBackReference
    private List<Equipo> equipos = new ArrayList<>();

    public Partido() {

    }

    public Partido(LocalDate fecha, String estadioPartido, Integer golesVisitante, Integer golesLocal,
                   Integer tiempoExtra, Integer penalesVisitante, Integer penalesLocal, Arbitro arbitro, Cancha cancha,
                   List<Equipo> equipos) {
        super();
        this.fecha = fecha;
        this.estadioPartido = estadioPartido;
        this.golesVisitante = golesVisitante;
        this.golesLocal = golesLocal;
        this.tiempoExtra = tiempoExtra;
        this.penalesVisitante = penalesVisitante;
        this.penalesLocal = penalesLocal;
        this.arbitro = arbitro;
        this.cancha = cancha;
        this.equipos = equipos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstadioPartido() {
        return estadioPartido;
    }

    public void setEstadioPartido(String estadioPartido) {
        this.estadioPartido = estadioPartido;
    }

    public Integer getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(Integer golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Integer getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public Integer getTiempoExtra() {
        return tiempoExtra;
    }

    public void setTiempoExtra(Integer tiempoExtra) {
        this.tiempoExtra = tiempoExtra;
    }

    public Integer getPenalesVisitante() {
        return penalesVisitante;
    }

    public void setPenalesVisitante(Integer penalesVisitante) {
        this.penalesVisitante = penalesVisitante;
    }

    public Integer getPenalesLocal() {
        return penalesLocal;
    }

    public void setPenalesLocal(Integer penalesLocal) {
        this.penalesLocal = penalesLocal;
    }

    public Arbitro getArbitro() {
        return arbitro;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        this.equipos.add(equipo);
        equipo.getPartidos().add(this);
    }

    public void removerEquipo(Equipo equipo) {
        this.equipos.remove(equipo);
        equipo.getPartidos().remove(this);
    }

    @Override
    public String toString() {
        return "Partido [idPartido=" + idPartido + ", fecha=" + fecha + ", estadioPartido=" + estadioPartido
                + ", golesVisitante=" + golesVisitante + ", golesLocal=" + golesLocal + ", tiempoExtra=" + tiempoExtra
                + ", penalesVisitante=" + penalesVisitante + ", penalesLocal=" + penalesLocal + ", arbitro=" + arbitro
                + ", cancha=" + cancha + "]";
    }

}

