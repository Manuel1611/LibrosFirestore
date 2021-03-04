package com.example.librosfirestore.model.pojo;

public class Libro {

    private String titulo;
    private String editorial;
    private long paginas;
    private String autor;
    private String url;
    private int numVentas;

    public Libro(String titulo, String editorial, long paginas, String autor, String url, int numVentas) {
        this.titulo = titulo;
        this.editorial = editorial;
        this.paginas = paginas;
        this.autor = autor;
        this.url = url;
        this.numVentas = numVentas;
    }

    public Libro(String titulo, String editorial, long paginas, String autor, String url) {
        this.titulo = titulo;
        this.editorial = editorial;
        this.paginas = paginas;
        this.autor = autor;
        this.url = url;
    }

    public Libro() {

        this("", "", 0, "", "");

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public long getPaginas() {
        return paginas;
    }

    public void setPaginas(long paginas) {
        this.paginas = paginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumVentas() {
        return numVentas;
    }

    public void setNumVentas(int numVentas) {
        this.numVentas = numVentas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", paginas=" + paginas +
                ", autor='" + autor + '\'' +
                ", url='" + url + '\'' +
                ", numVentas=" + numVentas +
                '}';
    }

}