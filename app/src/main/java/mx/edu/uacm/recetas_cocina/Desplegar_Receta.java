package mx.edu.uacm.recetas_cocina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//implements AdaptadorRecetaFirebase.OnItemClickListener
public class Desplegar_Receta extends AppCompatActivity  {

    TextView titulo;
    String prueba;
public static final String EXTRA_CREATOR = "crearNombre";
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

        Intent intent = getIntent();
        String nombre=getIntent().getStringExtra("Nombre");
        titulo.setText(nombre);

       // titulo.setText(intent.getStringExtra("titulo"));

<<<<<<< HEAD
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
}
