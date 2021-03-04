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
import android.widget.TextView;

import com.example.librosfirestore.R;
import com.example.librosfirestore.model.pojo.Libro;
import com.example.librosfirestore.view.adapter.RecyclerVentasAdapter;
import com.example.librosfirestore.viewmodel.ViewModel;

import java.util.List;

public class VentasFrag extends Fragment {

    private RecyclerView recyclerView;
    private ViewModel viewModel;
    private List<Libro> libroList;
    private NavController navController;
    private TextView tvRvVentasVacio;
    private Button btVolver;

    public VentasFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ventas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvVentas);
        tvRvVentasVacio = view.findViewById(R.id.tvRvVentasVacio);
        btVolver = view.findViewById(R.id.btVolverVentasFrag);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);

        navController = Navigation.findNavController(view);

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_ventasFrag_to_inicioFrag);

            }
        });

        viewModel.mostrarLibros().observe(getActivity(), new Observer<List<Libro>>() {
            @Override
            public void onChanged(List<Libro> libroLista) {

                libroList = libroLista;
                initRecycler(view);

            }
        });

    }

    private void initRecycler(View view) {

        RecyclerVentasAdapter adapter = new RecyclerVentasAdapter(libroList, getContext(), getActivity(), view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        if(adapter.getItemCount() == 0) {

            tvRvVentasVacio.setText(R.string.textNoSePuedeVenderPorqueNoHay);

        }

    }

}