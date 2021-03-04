package com.example.librosfirestore.model.pojo;

public class Venta {

    private String titulolibro;
    private double precio;

    public Venta(String titulolibro, double precio) {
        this.titulolibro = titulolibro;
        this.precio = precio;
    }

    public Venta() {

        this("", 0);

    }

    public String getTitulolibro() {
        return titulolibro;
    }

    public void setTitulolibro(String titulolibro) {
        this.titulolibro = titulolibro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Venta{" +
                ", titulolibro=" + titulolibro +
                ", precio=" + precio +
                '}';
    }

}