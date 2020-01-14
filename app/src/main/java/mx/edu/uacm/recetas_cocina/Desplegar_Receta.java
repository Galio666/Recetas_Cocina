package mx.edu.uacm.recetas_cocina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

//implements AdaptadorRecetaFirebase.OnItemClickListener
public class Desplegar_Receta extends AppCompatActivity  {

    TextView titulo;

    String prueba;
public static final String EXTRA_CREATOR = "crearNombre";

    TextView tipo;
    TextView ingredientes;
    TextView preparacion;
    RatingBar dificultad;
    ImageView imagen;


    RecyclerView recycler;
    AdaptadorRecetaFirebase adaptadorRecetaFirebase;
    DatabaseReference reference;
    ArrayList<Receta_Detalles> list;
    private Object Desplegar_Receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplegar__receta);

/*
        Intent intent = getIntent();
         prueba = intent.getStringExtra(EXTRA_CREATOR);
        titulo = findViewById(R.id.titulo);

       titulo.setText(prueba);


        prueba = getIntent().getExtras().get("Nombre").toString();



        Toast.makeText(Desplegar_Receta.this,prueba,Toast.LENGTH_SHORT).show();

        titulo= findViewById(R.id.tituloReceta);
        tipo=findViewById(R.id.detalleReceta);
        ingredientes=findViewById(R.id.ingredientesReceta);
        preparacion=findViewById(R.id.preparacionReceta);
        dificultad=findViewById(R.id.dificultadReceta);
        imagen=findViewById(R.id.imagenReceta);

        Intent intent = getIntent();
        String nombre=getIntent().getStringExtra("Nombre");
        String tipo1=getIntent().getStringExtra("Categoria");
        String ingredientes1=getIntent().getStringExtra("Ingredientes");
        String preparacion1=getIntent().getStringExtra("Preparacion");
        int dificultad1=getIntent().getIntExtra("Calificacion",0);
        String urlImagen=getIntent().getStringExtra("Foto");

       // System.out.println("Nuemero de estrellas es "+dificultad1);
        //System.out.println("Ingredientes"+ingredientes1);

        titulo.setText(nombre);
        tipo.setText(tipo1);
        ingredientes.setText(ingredientes1);
        preparacion.setText(preparacion1);
        dificultad.setNumStars(dificultad1);
        imagen.setImageBitmap(dislayImagen(urlImagen));

       // titulo.setText(intent.getStringExtra("titulo"));


        titulo.setText(intent.getStringExtra("titulo"));
*/


       // recycler=(RecyclerView) findViewById(R.id.my_recyclerView);
        //recycler.setLayoutManager(new LinearLayoutManager(this));

        //FirebaseDatabase database=FirebaseDatabase.getInstance();
        //reference=database.getReference().child("Usuario");

        //reference=FirebaseDatabase.getInstance().getReference().child("Usuario");



        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                    list.add(d);
                }
                adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(Desplegar_Receta.this,list);
                recycler.setAdapter(adaptadorRecetaFirebase);

                //adaptadorRecetaFirebase.setOnItemClickListener(Desplegar_Receta.this);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Desplegar_Receta.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });*/





    }


    /*
//checar lo de las clases que mando a llamar
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,Desplegar_Receta.class);
        Receta_Detalles clickedItem =  list.get(position);
        intent.putExtra(EXTRA_CREATOR, clickedItem.getNombre());

        startActivity(intent);
    }*/

    public Bitmap dislayImagen(String url){
        Bitmap bmp = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes= Base64.decode(url,Base64.DEFAULT);
        Bitmap deBitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);

        return deBitmap;
    }

}
