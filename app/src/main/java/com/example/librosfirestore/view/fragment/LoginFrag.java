package com.example.librosfirestore.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.librosfirestore.viewmodel.ViewModel;

public class LoginFrag extends Fragment {

    private ViewModel viewModel;
    private NavController navController;

    public LoginFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(view);

        EditText etEmail, etPassword;
        Button btLogin = view.findViewById(R.id.btLogin);
        TextView tvAlerta = view.findViewById(R.id.tvAlertaLogin);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {

                    tvAlerta.setText(R.string.textTienesQueRellenarTodosLosCampos);

                } else {

                    viewModel.login(email, password).observe(getActivity(), new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {

                            if (aBoolean) {

                                navController.navigate(R.id.action_loginFrag_to_inicioFrag);

                            } else {

                                tvAlerta.setText(R.string.textErrorIniciarSesion);

                            }
                        }
                    });
                }
            }
        });
    }
}