package com.company.myapplication.models;

import android.text.Editable;

import java.io.Serializable;

public class Partido implements Serializable {
    private String equipoLocal;
    private int golesLocal;
    private String equipoVisitante;
    private int golesVisitante;
    private String resumenPartido;

    public Partido(String equipoLocal, int golesLocal, String equipoVisitante, int golesVisitante) {
        this.equipoLocal = equipoLocal;
        this.golesLocal = golesLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesVisitante = golesVisitante;
        if(golesLocal>golesVisitante){
            this.resumenPartido = equipoLocal + " Ha ganado el partido";
        }else{
            this.resumenPartido = equipoVisitante + " Ha ganado el partido";
        }
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public String getResumenPartido() {
        return resumenPartido;
    }

    public void setResumenPartido(String resumenPartido) {
        this.resumenPartido = resumenPartido;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "equipoLocal='" + equipoLocal + '\'' +
                ", golesLocal=" + golesLocal +
                ", equipoVisitante='" + equipoVisitante + '\'' +
                ", golesVisitante=" + golesVisitante +
                ", resumenPartido='" + resumenPartido + '\'' +
                '}';
    }
}
