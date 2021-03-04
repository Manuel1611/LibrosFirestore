package com.example.librosfirestore.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.librosfirestore.model.pojo.Venta;
import com.example.librosfirestore.viewmodel.ViewModel;

public class AddVentaFrag extends Fragment {

    private NavController navController;
    private Button btVolver, btVender;
    private ViewModel viewModel;
    private EditText etPrecio;
    private TextView tvAlertaAddVenta;

    public AddVentaFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_venta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btVolver = view.findViewById(R.id.btVolverAddVenta);
        btVender = view.findViewById(R.id.btVender2);
        etPrecio = view.findViewById(R.id.etPrecioAddVenta);
        tvAlertaAddVenta = view.findViewById(R.id.tvAlertaAddVenta);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(view);

        Libro libro = viewModel.getLibro();

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_addVentaFrag_to_ventasFrag);

            }
        });

        btVender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String precio = etPrecio.getText().toString();

                if(precio.isEmpty()) {

                    tvAlertaAddVenta.setText(R.string.textTienesQueRellenarTodosLosCampos);

                } else {

                    double precioDouble = Double.parseDouble(precio);

                    Venta venta = new Venta(libro.getTitulo(), precioDouble);

                    viewModel.insertarVenta(venta, libro);
                    navController.navigate(R.id.action_addVentaFrag_to_ventasFrag);

                }

            }
        });

    }

}