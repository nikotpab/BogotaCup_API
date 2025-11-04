package co.edu.unbosque.bogotacup.dto;

import java.time.LocalDate;

public class PartidoDTO {

    private Integer idPartido;
    private LocalDate fecha;
    private String estadioPartido;
    private Integer golesVisitante;
    private Integer golesLocal;
    private Integer tiempoExtra;
    private Integer penalesVisitante;
    private Integer penalesLocal;

    private Integer idArbitro;
    private Integer idCancha;

    public PartidoDTO() {
    }

    public Integer getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
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

    public Integer getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(Integer idArbitro) {
        this.idArbitro = idArbitro;
    }

    public Integer getIdCancha() {
        return idCancha;
    }

    public void setIdCancha(Integer idCancha) {
        this.idCancha = idCancha;
    }
}