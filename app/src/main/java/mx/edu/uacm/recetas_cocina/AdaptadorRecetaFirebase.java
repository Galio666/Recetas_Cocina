package mx.edu.uacm.recetas_cocina;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AdaptadorRecetaFirebase extends RecyclerView.Adapter <AdaptadorRecetaFirebase.ViewHolderRecetas>{

    Context context;
    ArrayList<Receta_Detalles> detallesrecetas;

    public AdaptadorRecetaFirebase(Context context, ArrayList<Receta_Detalles> detallesrecetas) {
        this.context = context;
        this.detallesrecetas = detallesrecetas;
    }

    @NonNull
    @Override
    public AdaptadorRecetaFirebase.ViewHolderRecetas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorRecetaFirebase.ViewHolderRecetas(LayoutInflater.from(context).inflate(R.layout.detalle_receta,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecetas holder, int position) {

        holder.getTextViewNombreReceta().setText(detallesrecetas.get(position).getNombre());
        holder.textViewTipoReceta.setText(detallesrecetas.get(position).getCategoria());
        Picasso.get().load(detallesrecetas.get(position).getFoto()).into(holder.imagen);
    }

    @Override
    public int getItemCount() {
        return detallesrecetas.size();
    }

    public class ViewHolderRecetas extends RecyclerView.ViewHolder {

        private TextView textViewNombreReceta;
        private TextView textViewTipoReceta;
        private ImageView imagen;
        private RatingBar dificultad;

        public ViewHolderRecetas(@NonNull View itemView, TextView textViewNombreReceta, TextView textViewTipoReceta, ImageView imagen, RatingBar dificultad) {
            super(itemView);
            this.textViewNombreReceta = textViewNombreReceta;
            this.textViewTipoReceta = textViewTipoReceta;
            this.imagen = imagen;
            this.dificultad = dificultad;
        }

        public ViewHolderRecetas(View inflate) {
            super(inflate);
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





   /* public AdaptadorRecetaFirebase(Class<Receta_Detalles> modelClass, int modelLayout, Class<ViewHolderRecetas> viewHolderClass, DatabaseReference ref,Context c) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context=c;
    }

    @Override
    protected void populateViewHolder(ViewHolderRecetas viewHolderRecetas, final Receta_Detalles receta_detalles, int i) {
                viewHolderRecetas.getTextViewNombreReceta().setText(receta_detalles.getNombre());
                viewHolderRecetas.getTextViewTipoReceta().setText(receta_detalles.getCategoria());
                viewHolderRecetas.getDificultad().setNumStars(receta_detalles.getCalificacion());
        Picasso.with(context).load(receta_detalles.getFoto()).into(viewHolderRecetas.getImagen());
    }*/
}
