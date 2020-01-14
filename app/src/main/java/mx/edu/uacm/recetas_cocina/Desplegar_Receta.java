package mx.edu.uacm.recetas_cocina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

public class Desplegar_Receta extends AppCompatActivity {

    TextView titulo;

    RecyclerView recycler;
    AdaptadorRecetaFirebase adaptadorRecetaFirebase;
    DatabaseReference reference;
    ArrayList<Receta_Detalles> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplegar__receta);







        recycler=(RecyclerView) findViewById(R.id.my_recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Usuario");

        //reference=FirebaseDatabase.getInstance().getReference().child("Usuario");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                    list.add(d);
                }
                adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(Desplegar_Receta.this,list);






                recycler.setAdapter(adaptadorRecetaFirebase);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Desplegar_Receta.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });




















    }
}
