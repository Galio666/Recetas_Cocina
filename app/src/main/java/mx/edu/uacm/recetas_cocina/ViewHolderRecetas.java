package mx.edu.uacm.recetas_cocina;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderRecetas extends RecyclerView.ViewHolder {
    private TextView textViewNombreReceta;
    private TextView textViewTipoReceta;
    private ImageView imagen;
    private RatingBar dificultad;

    ViewHolderRecetas(@NonNull View itemView) {
        super(itemView);
        textViewNombreReceta = itemView.findViewById(R.id.nombreReceta);
        textViewTipoReceta = itemView.findViewById(R.id.TipoReceta);
        imagen=itemView.findViewById(R.id.imagenReceta);
        dificultad=itemView.findViewById(R.id.Dificultad);
    }


    public TextView getTextViewNombreReceta() {
        return textViewNombreReceta;
    }

    public void setTextViewNombreReceta(TextView textViewNombreReceta) {
        this.textViewNombreReceta = textViewNombreReceta;
    }

    public TextView getTextViewTipoReceta() {
        return textViewTipoReceta;
    }

    public void setTextViewTipoReceta(TextView textViewTipoReceta) {
        this.textViewTipoReceta = textViewTipoReceta;
    }

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public RatingBar getDificultad() {
        return dificultad;
    }

    public void setDificultad(RatingBar dificultad) {
        this.dificultad = dificultad;
    }
}