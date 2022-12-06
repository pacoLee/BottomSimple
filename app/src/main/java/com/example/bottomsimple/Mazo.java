package com.example.bottomsimple;

public class Mazo {
    private int idMazo, imagen;
    private String nombreMazo;

    public Mazo(int idMazo, int imagen, String nombreMazo) {
        this.idMazo = idMazo;
        this.imagen = imagen;
        this.nombreMazo = nombreMazo;
    }

    public int getIdMazo() {
        return idMazo;
    }

    public void setIdMazo(int idMazo) {
        this.idMazo = idMazo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombreMazo() {
        return nombreMazo;
    }

    public void setNombreMazo(String nombreMazo) {
        this.nombreMazo = nombreMazo;
    }
}