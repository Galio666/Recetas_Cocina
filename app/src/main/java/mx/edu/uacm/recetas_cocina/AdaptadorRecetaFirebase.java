package mx.edu.uacm.recetas_cocina;

import android.content.Context;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;


public class AdaptadorRecetaFirebase extends FirebaseRecyclerAdapter <Receta_Detalles,ViewHolderRecetas>{

    Context context;

    public AdaptadorRecetaFirebase(Class<Receta_Detalles> modelClass, int modelLayout, Class<ViewHolderRecetas> viewHolderClass, DatabaseReference ref,Context c) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        context=c;
    }

    @Override
    protected void populateViewHolder(ViewHolderRecetas viewHolderRecetas, Receta_Detalles receta_detalles, int i) {
                viewHolderRecetas.getTextViewNombreReceta().setText(receta_detalles.getNombreReceta());
                viewHolderRecetas.getTextViewTipoReceta().setText(receta_detalles.getTipoReceta());
                viewHolderRecetas.getDificultad().setNumStars(receta_detalles.getRating());
        Picasso.with(context).load(receta_detalles.getImagen()).into(viewHolderRecetas.getImagen());
    }
}
