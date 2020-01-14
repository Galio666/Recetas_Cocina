package mx.edu.uacm.recetas_cocina;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.squareup.picasso.Picasso.*;


public class AdaptadorRecetaFirebase extends RecyclerView.Adapter <AdaptadorRecetaFirebase.ViewHolderRecetas>{

    Context context;
    ArrayList<Receta_Detalles> detallesrecetas;
    private View.OnClickListener listener;
    int []arr;
    private OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener=listener;
    }

    public AdaptadorRecetaFirebase(Context context, ArrayList<Receta_Detalles> detallesrecetas) {
        this.context = context;
        this.detallesrecetas = detallesrecetas;
    }

    public Bitmap dislayImagen(String url){
        Bitmap bmp = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes=Base64.decode(url,Base64.DEFAULT);
        Bitmap deBitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

        return deBitmap;
    }

    @NonNull
    @Override
    public AdaptadorRecetaFirebase.ViewHolderRecetas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorRecetaFirebase.ViewHolderRecetas(LayoutInflater.from(context).inflate(R.layout.detalle_receta,parent,false));

    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolderRecetas holder, final int position) {

        holder.textViewNombreReceta.setText(detallesrecetas.get(position).getNombre());
        holder.textViewTipoReceta.setText(detallesrecetas.get(position).getCategoria());
        get().load(detallesrecetas.get(position).getFoto()).into(holder.imagen);
        holder.imagen.setImageBitmap(dislayImagen(detallesrecetas.get(position).getFoto()));


        holder.dificultad.setNumStars(detallesrecetas.get(position).getCalificacion());
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent var2 = new Intent(v.getContext(),Desplegar_Receta.class);
                var2.putExtra("Nombre",detallesrecetas.get(position).getNombre());
                var2.putExtra("Categoria",detallesrecetas.get(position).getCategoria());
                var2.putExtra("Foto",detallesrecetas.get(position).getFoto());
                //var2.putExtra("Calificacion",detallesrecetas.get(position).getCalificacion());
                var2.putExtra("Ingredientes",detallesrecetas.get(position).getIngredientes());
                var2.putExtra("Preparacion",detallesrecetas.get(position).getPreparacion());
                var2.putExtra("Calificacion",detallesrecetas.get(position).getCalificacion());

                v.getContext().startActivity(var2);
            }
        });


    }

    @Override
    public int getItemCount() {
        return detallesrecetas.size();
    }



    public class ViewHolderRecetas extends RecyclerView.ViewHolder {


         TextView textViewNombreReceta;
         TextView textViewTipoReceta;
         ImageView imagen;
         RatingBar dificultad;
         TextView prueba;


        public ViewHolderRecetas(@NonNull final View itemView) {
            super(itemView);
           textViewNombreReceta =(TextView) itemView.findViewById(R.id.nombre);
           textViewTipoReceta = (TextView) itemView.findViewById(R.id.TipoReceta);
           imagen = (ImageView)itemView.findViewById(R.id.imagenReceta);
           dificultad = (RatingBar)itemView.findViewById(R.id.Dificultad);


           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (mlistener != null){
                       int position = getAdapterPosition();
                       if (position != RecyclerView.NO_POSITION){
                           mlistener.onItemClick(position);
                       }
                   }
               }
           });

          // itemView.setOnClickListener(this);
/*
=======
           //itemView.setOnClickListener(this);

           /*
>>>>>>> 1fa19f7f70949db006003d86089f8799342e4411
imagen.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {

        Intent var2 = new Intent(view.getContext(),Desplegar_Receta.class);
        var2.putExtra("Nombre",detallesrecetas.)
        view.getContext().startActivity(var2);




    }
});
        }


/*
=======


        @Override
        public void onClick(View v){

            Intent intent = new Intent(context,Desplegar_Receta.class);
            intent.putExtra("Nombre",""+getAdapterPosition());
            context.startActivity(intent);

        }*/


    }




}}
