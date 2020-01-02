package mx.edu.uacm.recetas_cocina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText nombreUsuario;
    private EditText contraseña;
    private Button btnRegistrar;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombreUsuario=(EditText)findViewById(R.id.txtUsuario);
        contraseña=(EditText) findViewById(R.id.txtPass);
        btnRegistrar=(Button) findViewById(R.id.btnRegsitrar);





    }
}
