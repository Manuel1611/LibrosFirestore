package com.example.librosfirestore.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class RecyclerVentasAdapter extends RecyclerView.Adapter<RecyclerVentasAdapter.ViewHolder> {

    private List<Libro> libroList;
    private ViewModel viewModel;
    private Context context;
    private Activity activity;
    View view;

    public RecyclerVentasAdapter(List<Libro> libroList, Context context, Activity activity, View view) {

        this.libroList = libroList;
        this.context = context;
        this.activity = activity;
        this.view = view;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libros_ventas, parent,false);
        RecyclerVentasAdapter.ViewHolder holder = new RecyclerVentasAdapter.ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVentasAdapter.ViewHolder holder, int position) {

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

        holder.cons.setOnClickListener(new View.OnClickListener() {

            final NavController navController = Navigation.findNavController(view);

            @Override
            public void onClick(View v) {

                viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);

                Libro lib = libroList.get(position);

                viewModel.setLibro(lib);
                navController.navigate(R.id.action_ventasFrag_to_addVentaFrag);

            }
        });

    }

    @Override
    public int getItemCount() {
        return libroList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgLibro;
        TextView tvTitulo, tvEditorial, tvPaginas, tvAutor;
        ConstraintLayout cons;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imgLibro = itemView.findViewById(R.id.imgLibro);
            tvTitulo = itemView.findViewById(R.id.tvTituloLibro);
            tvEditorial = itemView.findViewById(R.id.tvEditorialLibro);
            tvPaginas = itemView.findViewById(R.id.tvPaginasLibro);
            tvAutor = itemView.findViewById(R.id.tvAutorLibro);
            cons = itemView.findViewById(R.id.consLibros);
        }

    }

}