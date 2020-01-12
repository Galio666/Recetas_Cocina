package mx.edu.uacm.recetas_cocina;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderRecetas extends RecyclerView.ViewHolder {
    private TextView textViewNombreReceta, textViewTipoReceta;
    private ImageView imagen;
    private

    ViewHolderRecetas(@NonNull View itemView) {
        super(itemView);
        textViewNombreReceta = itemView.findViewById(R.id.nombreReceta);
        textViewTipoReceta = itemView.findViewById(R.id.TipoReceta);
    }



}