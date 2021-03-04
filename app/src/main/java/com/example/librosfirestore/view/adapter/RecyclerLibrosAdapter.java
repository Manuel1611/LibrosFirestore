package com.example.librosfirestore.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.librosfirestore.R;
import com.example.librosfirestore.model.pojo.Libro;
import com.example.librosfirestore.viewmodel.ViewModel;

import java.util.List;

public class RecyclerLibrosAdapter extends RecyclerView.Adapter<RecyclerLibrosAdapter.ViewHolder> {

    private List<Libro> libroList;
    private ViewModel viewModel;
    private Context context;
    private Activity activity;
    View view;

    public RecyclerLibrosAdapter(List<Libro> libroList, Context context, Activity activity, View view) {

        this.libroList = libroList;
        this.context = context;
        this.activity = activity;
        this.view = view;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libros, parent,false);
        RecyclerLibrosAdapter.ViewHolder holder = new RecyclerLibrosAdapter.ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerLibrosAdapter.ViewHolder holder, int position) {

        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);

        holder.tvTitulo.setText("Título: " + libroList.get(position).getTitulo());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.cargando)
                .error(R.drawable.cerdo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context).load(libroList.get(position).getUrl())
                .apply(options)
                .into(holder.imgLibro);

        holder.tvEditorial.setText("Editorial: " + libroList.get(position).getEditorial());

        holder.tvPaginas.setText("Num. páginas: " + libroList.get(position).getPaginas());

        holder.tvAutor.setText("Autor: " + libroList.get(position).getAutor());

        holder.tvNumVentas.setText("Veces vendido: " + libroList.get(position).getNumVentas());

        holder.cons.setOnClickListener(new View.OnClickListener() {

            final NavController navController = Navigation.findNavController(view);

            @Override
            public void onClick(View v) {

                viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);

                Libro lib = libroList.get(position);

                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Información");
                builder.setMessage("¿Deseas editar este libro?");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        viewModel.setLibro(lib);
                        navController.navigate(R.id.action_librosFrag_to_editLibroFrag);

                    }
                });

                builder.setNegativeButton("Cancelar", null);
                builder.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return libroList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgLibro;
        TextView tvTitulo, tvEditorial, tvPaginas, tvAutor, tvNumVentas;
        ConstraintLayout cons;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgLibro = itemView.findViewById(R.id.imgLibro);
            tvTitulo = itemView.findViewById(R.id.tvTituloLibro);
            tvEditorial = itemView.findViewById(R.id.tvEditorialLibro);
            tvPaginas = itemView.findViewById(R.id.tvPaginasLibro);
            tvAutor = itemView.findViewById(R.id.tvAutorLibro);
            tvNumVentas = itemView.findViewById(R.id.tvNumVentasLibro);
            cons = itemView.findViewById(R.id.consLibros);
        }

    }

}