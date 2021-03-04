package com.example.librosfirestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.librosfirestore.model.Repository;
import com.example.librosfirestore.model.pojo.Libro;
import com.example.librosfirestore.model.pojo.Venta;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private Libro libro;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    public MutableLiveData<Boolean> login(String email, String password) {
        return repository.login(email, password);
    }

    public MutableLiveData<List<Libro>> mostrarLibros() {
        return repository.mostrarLibros();
    }

    public void insertarLibro(Libro libro) {
        repository.insertarLibro(libro);
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public void editarLibro(Libro libro) {
        repository.editarLibro(libro);
    }

    public void borrarLibro(Libro libro) {
        repository.borrarLibro(libro);
    }

    public void insertarVenta(Venta venta, Libro libro) {
        repository.insertarVenta(venta, libro);
    }

}