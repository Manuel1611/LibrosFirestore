package com.example.librosfirestore.view.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.librosfirestore.R;
import com.example.librosfirestore.model.pojo.Libro;
import com.example.librosfirestore.viewmodel.ViewModel;

public class EditLibroFrag extends Fragment {

    Button btVolver, btEdit, btBorrar;
    ViewModel viewModel;
    NavController navController;
    EditText etTitulo, etEditorial, etPaginas, etAutor, etUrl;
    TextView tvAlertaEditLibro;

    public EditLibroFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_libro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(view);

        etTitulo.setText(viewModel.getLibro().getTitulo());
        etEditorial.setText(viewModel.getLibro().getEditorial());
        etPaginas.setText("" + viewModel.getLibro().getPaginas());
        etAutor.setText(viewModel.getLibro().getAutor());
        etUrl.setText(viewModel.getLibro().getUrl());

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_editLibroFrag_to_librosFrag);

            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = etTitulo.getText().toString();
                String editorial = etEditorial.getText().toString();
                String paginas = etPaginas.getText().toString();
                String autor = etAutor.getText().toString();
                String url = etUrl.getText().toString();

                if (titulo.isEmpty() || editorial.isEmpty() || paginas.isEmpty() || autor.isEmpty() || url.isEmpty()) {

                    tvAlertaEditLibro.setText(R.string.textTienesQueRellenarTodosLosCampos);

                } else {

                    long paginasLong = Long.parseLong(paginas);

                    Libro libro = new Libro(titulo, editorial, paginasLong, autor, url);

                    viewModel.editarLibro(libro);
                    navController.navigate(R.id.action_editLibroFrag_to_librosFrag);

                }

            }
        });

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Confirmar");
                builder.setMessage("¿Seguro que quieres eliminar este libro?");
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        viewModel.borrarLibro(viewModel.getLibro());
                        navController.navigate(R.id.action_editLibroFrag_to_librosFrag);

                    }
                });

                builder.setNegativeButton("Cancelar", null);
                builder.show();

            }
        });

    }

    private void init(View view) {

        btVolver = view.findViewById(R.id.btVolverEditLibroFrag);
        btEdit = view.findViewById(R.id.btEditLibro2);
        btBorrar = view.findViewById(R.id.btBorrarLibro);

        etTitulo = view.findViewById(R.id.etTituloLibroEdit);
        etEditorial = view.findViewById(R.id.etEditorialLibroEdit);
        etPaginas = view.findViewById(R.id.etPaginasLibroEdit);
        etAutor = view.findViewById(R.id.etAutorLibroEdit);
        etUrl = view.findViewById(R.id.etUrlLibroEdit);
        tvAlertaEditLibro = view.findViewById(R.id.tvAlertaEditLibro);

    }

}