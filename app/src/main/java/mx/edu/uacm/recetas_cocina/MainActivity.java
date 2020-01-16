package mx.edu.uacm.recetas_cocina;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;


public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static final int MY_CODIGO = 666;
    List<AuthUI.IdpConfig> opcionesregistro;
    ImageView imagen;
    TextView usuario;
    private FirebaseUser user;
    TextView header;
    MultiSnapRecyclerView recycler,recyclerPostres,recyclerPlatof,recyclerSopas;
    AdaptadorRecetaFirebase adaptadorRecetaFirebase;
    DatabaseReference reference;
    ArrayList<Receta_Detalles> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler=(MultiSnapRecyclerView) findViewById(R.id.my_recyclerViewEntradas);
        recyclerSopas=(MultiSnapRecyclerView) findViewById(R.id.my_recyclerViewSopas);
        recyclerPostres=(MultiSnapRecyclerView) findViewById(R.id.my_recyclerViewPostres);
        recyclerPlatof=(MultiSnapRecyclerView) findViewById(R.id.my_recyclerViewPlatoFuerte);


        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerSopas.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerPostres.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerPlatof.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));


        FirebaseDatabase database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Usuario");
        Query entrada=reference.orderByChild("Categoria").equalTo("Entradas");
        Query sopas= reference.orderByChild("Categoria").equalTo("Sopas");
        Query postres= reference.orderByChild("Categoria").equalTo("Postres");
        Query platoFuerte= reference.orderByChild("Categoria").equalTo("Plato Fuerte");

        entrada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                    list.add(d);
                }
                adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(MainActivity.this,list);
                recycler.setAdapter(adaptadorRecetaFirebase);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        sopas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                    list.add(d);
                }
                adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(MainActivity.this,list);
                recyclerSopas.setAdapter(adaptadorRecetaFirebase);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        postres.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                    list.add(d);
                }
                adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(MainActivity.this,list);
                recyclerPostres.setAdapter(adaptadorRecetaFirebase);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        platoFuerte.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list=new ArrayList<>();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                    list.add(d);
                }
                adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(MainActivity.this,list);
                recyclerPlatof.setAdapter(adaptadorRecetaFirebase);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //reference=FirebaseDatabase.getInstance().getReference().child("Usuario");



       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               list=new ArrayList<>();
               for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                   Receta_Detalles d =dataSnapshot1.getValue(Receta_Detalles.class);
                   list.add(d);
               }
               adaptadorRecetaFirebase=new AdaptadorRecetaFirebase(MainActivity.this,list);
               recycler.setAdapter(adaptadorRecetaFirebase);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(MainActivity.this, "Algo salio mal", Toast.LENGTH_SHORT).show();
           }
       });


        setToolBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navview);
        // imagen=(ImageView) findViewById(R.id.navview);
        usuario = (TextView) findViewById(R.id.UsuarioNombre);
        imagen = (ImageView) findViewById(R.id.UsuarioImagen);



        opcionesregistro = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build()


        );
        mostrarOpcionesRegistro();


        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

    }


    //Metodo del boton flotante
    public void Flotante(View view) {

        Intent var1 = new Intent(this, AgregarRecetas.class);
        startActivity(var1);
    }


/*
    public void ImDetalle(View view) {

        Intent var2 = new Intent(this, Desplegar_Receta.class);
        startActivity(var2);
    }

*/

    public void mostrarOpcionesRegistro() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().
                        setAvailableProviders(opcionesregistro).setLogo(R.drawable.r_coina)
                        .setTheme(R.style.RegistroTema).build(), MY_CODIGO


        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_CODIGO) {

            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                //setContentView(R.layout.header_navigation_drawer);
                //usuario=(TextView) findViewById(R.id.UsuarioNombre);

                //Obtenemos el usuario
                user = FirebaseAuth.getInstance().getCurrentUser();

                //Desplegamos el email en el toast
                Toast.makeText(this, "" + user.getEmail(), Toast.LENGTH_SHORT).show();
                View header = navigationView.getHeaderView(0);
                TextView text = (TextView) header.findViewById(R.id.UsuarioNombre);
                ImageView imagen = (ImageView) header.findViewById(R.id.UsuarioImagen);

                Uri uri = user.getPhotoUrl().normalizeScheme();

                text.setText(user.getEmail());


                Glide.with(getBaseContext()).load(uri + "?height=500").into(imagen);


                System.out.println(uri);


                //usuario.setText(user.getEmail());

            } else {
                Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id=menuItem.getItemId();

        if(id==R.id.cerrar_sesion){
            AuthUI.getInstance()
                    .signOut( MainActivity.this).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText( MainActivity.this ,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        return true;
    }
}



