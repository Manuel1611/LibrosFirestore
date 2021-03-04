package com.example.librosfirestore.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.librosfirestore.model.pojo.Libro;
import com.example.librosfirestore.model.pojo.Venta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Repository {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MutableLiveData<Boolean> login(String email, String password) {

        MutableLiveData<Boolean> siono = new MutableLiveData<>();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            @Override
            public void onSuccess(AuthResult authResult) {
                currentUser = firebaseAuth.getCurrentUser();
                siono.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                currentUser = null;
                siono.setValue(false);
            }
        });

        return siono;
    }

    public MutableLiveData<List<Libro>> mostrarLibros() {

        MutableLiveData<List<Libro>> libros = new MutableLiveData<>();

        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    libros.setValue(task.getResult().toObjects(Libro.class));
                }
            }
        });
        return libros;

    }

    public void insertarLibro(Libro libro) {

        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).set(libro).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Log.v("xyz", "libro insertado");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.v("xyz", "libro no insertado");

            }
        });

    }

    public void editarLibro(Libro libro) {

        String autor = libro.getAutor();
        String editorial = libro.getEditorial();
        long paginas = libro.getPaginas();
        String titulo = libro.getTitulo();
        String url = libro.getUrl();

        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).update("autor", autor);
        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).update("editorial", editorial);
        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).update("paginas", paginas);
        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).update("titulo", titulo);
        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).update("url", url);

    }

    public void borrarLibro(Libro libro) {

        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).delete();
        if (libro.getNumVentas() != 0) {

            borrarVentas(libro);

        }

    }

    public void borrarVentas(Libro libro) {

        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/venta").document(libro.getTitulo()).delete();

    }

    public void insertarVenta(Venta venta, Libro libro) {

        db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/venta").document(libro.getTitulo()).set(venta).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                libro.setNumVentas(libro.getNumVentas() + 1);
                int numVentas = libro.getNumVentas();
                db.collection("user/" + firebaseAuth.getCurrentUser().getUid() + "/libro").document(libro.getTitulo()).update("numVentas", numVentas);
                Log.v("xyz", "venta insertada");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Log.v("xyz", "venta no insertada");

            }
        });

    }

}