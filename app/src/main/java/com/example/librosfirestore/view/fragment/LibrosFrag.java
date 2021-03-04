package com.example.librosfirestore.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.librosfirestore.R;
import com.example.librosfirestore.model.pojo.Libro;
import com.example.librosfirestore.view.adapter.RecyclerLibrosAdapter;
import com.example.librosfirestore.viewmodel.ViewModel;

import java.util.List;

public class LibrosFrag extends Fragment {

    private RecyclerView recyclerView;
    private ViewModel viewModel;
    private List<Libro> libroList;
    private NavController navController;
    private Button btAddLibro, btVolver;

    public LibrosFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_libros, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvLibros);
        btAddLibro = view.findViewById(R.id.btAddLibro);
        btVolver = view.findViewById(R.id.btVolverLibrosFrag);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        navController = Navigation.findNavController(view);

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_librosFrag_to_inicioFrag);

            }
        });

        btAddLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_librosFrag_to_addLibroFrag);

            }
        });

        viewModel.mostrarLibros().observe(getActivity(), new Observer<List<Libro>>() {
            @Override
            public void onChanged(List<Libro> libros) {
                libroList = libros;
                initRecycler(view);
            }
        });

    }

    private void initRecycler(View view) {

        RecyclerLibrosAdapter adapter = new RecyclerLibrosAdapter(libroList, getContext(), getActivity(), view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

    }
}