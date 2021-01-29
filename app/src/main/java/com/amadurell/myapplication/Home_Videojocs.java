package com.amadurell.myapplication;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.amadurell.myapplication.databinding.HomeVideojocsBinding;
import com.amadurell.myapplication.databinding.HorizontalContingutBinding;

import java.util.List;

public class Home_Videojocs extends Fragment {

    HomeVideojocsBinding binding;
    private JuegosViewModel juegosViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = HomeVideojocsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //AQUI VA EL CODIGO
        NavController navController = Navigation.findNavController(view);

        juegosViewModel = new ViewModelProvider(this).get(JuegosViewModel.class);




        JuegosNewAdapter juegosNewAdapter = new JuegosNewAdapter();


        juegosViewModel.obtenerJuegosNuevos().observe(getViewLifecycleOwner(), albums -> {
            juegosNewAdapter.setAlbumList(albums);
        });


    }


    class JuegosNewAdapter extends RecyclerView.Adapter<JuegoNewViewHolder> {

        List<Juego> albumList;


        @NonNull
        @Override
        public JuegoNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JuegoNewViewHolder(HorizontalContingutBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull JuegoNewViewHolder holder, int position) {
            Juego album = albumList.get(position);

            Glide.with(holder.itemView).load(album.imagen).into(holder.binding.portada);
        }

        @Override
        public int getItemCount() {
            return albumList == null ? 0 : albumList.size();
        }

        public void setAlbumList(List<Juego> albumList) {
            this.albumList = albumList;
            notifyDataSetChanged();
        }
    }




    static class JuegoNewViewHolder extends RecyclerView.ViewHolder {
        HorizontalContingutBinding binding;

        public JuegoNewViewHolder(@NonNull HorizontalContingutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}