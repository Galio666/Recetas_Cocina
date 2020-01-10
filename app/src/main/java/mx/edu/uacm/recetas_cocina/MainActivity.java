package mx.edu.uacm.recetas_cocina;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static final int MY_CODIGO = 666;
    List<AuthUI.IdpConfig> opcionesregistro;
    ImageView imagen;
    TextView usuario;
    private FirebaseUser user;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navview);
       // imagen=(ImageView) findViewById(R.id.navview);
        usuario=(TextView)findViewById(R.id.UsuarioNombre);





        opcionesregistro= Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build()


        );
        mostrarOpcionesRegistro();


        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);

    }


    //Metodo del boton flotante
    public void Flotante(View view){

        Intent int1 = new Intent(this,AgregarRecetas.class);
        startActivity(int1);
    }


    public void mostrarOpcionesRegistro(){
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().
                        setAvailableProviders(opcionesregistro).setLogo(R.drawable.r_coina)
                .setTheme(R.style.RegistroTema).build(),MY_CODIGO



        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_CODIGO){

            IdpResponse response =IdpResponse.fromResultIntent(data);
            if (resultCode==RESULT_OK){
                //setContentView(R.layout.header_navigation_drawer);
                //usuario=(TextView) findViewById(R.id.UsuarioNombre);

                //Obtenemos el usuario
                user= FirebaseAuth.getInstance().getCurrentUser();

                //Desplegamos el email en el toast
                Toast.makeText(this , ""+user.getEmail(), Toast.LENGTH_SHORT).show();
                View header=navigationView.getHeaderView(0);
                TextView text=(TextView) header.findViewById(R.id.UsuarioNombre);
                ImageView imagen=(ImageView)header.findViewById(R.id.UsuarioImagen);

                Uri uri=user.getPhotoUrl();



                text.setText(user.getEmail());




                imagen.setImageURI(uri);
                System.out.println(uri);



                //usuario.setText(user.getEmail());

            }
        }
    }

    private void setToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
