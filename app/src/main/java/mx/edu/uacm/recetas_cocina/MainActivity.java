package mx.edu.uacm.recetas_cocina;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_CODIGO = 666;
    List<AuthUI.IdpConfig> opcionesregistro;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opcionesregistro= Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()


        );
        mostrarOpcionesRegistro();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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
                //Obtenemos el usuario
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                //Desplegamos el email en el toast
                Toast.makeText(this , ""+user.getEmail(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
