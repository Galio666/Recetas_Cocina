package mx.edu.uacm.recetas_cocina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AgregarRecetas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //referencia a la base de datos del nodo principal
    DatabaseReference mRootReference;
    Button btnSubirDatosFirebase;
    EditText etNombre,varSpin,etIngredientes,etPreparacion;
    Spinner spin;
    RatingBar ratingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recetas);

        // inicia en el nodo principal
        mRootReference = FirebaseDatabase.getInstance().getReference();

        /***Relacionando variables back con front***/
        btnSubirDatosFirebase = findViewById(R.id.btn_agregar);
        etNombre =(EditText) findViewById(R.id.edt_nombre);
        varSpin =(EditText) findViewById(R.id.edt_aux);
        spin = (Spinner)findViewById(R.id.menuCat);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        etIngredientes = (EditText)findViewById(R.id.edt_ingredientes);
        etPreparacion = (EditText)findViewById(R.id.edt_preparacion);






        /****Evento al pulsar el boton*****/
        btnSubirDatosFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString();
                String categoria = varSpin.getText().toString();
                Float calificacion = ratingBar.getRating();
                String ingredientes = etIngredientes.getText().toString();
                String preparacion = etPreparacion.getText().toString();


                // cargar los valores y guardarlos en firebase

                Map<String, Object> datosUsuario = new HashMap<>();
                datosUsuario.put("Nombre",nombre);
                datosUsuario.put("Categoria",categoria);
                datosUsuario.put("Calificacion",calificacion);
                datosUsuario.put("Ingredientes",ingredientes);
                datosUsuario.put("Preparacion",preparacion);

                mRootReference.child("Usuario").push().setValue(datosUsuario); /*verificar si guarda los datos como oauth*/

            }
        });




        //logica del menu de categoria
        Spinner spinner = findViewById(R.id.menuCat);
                                                                                                   /*desde aqui mando a llamr el xml de las categorias o se podria desde el layout**/
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categorias,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       /*****Mostramos la notificacion de lo que elegimos pero en este caso no es necesario*****/
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();

        /***Guardamos el dato selecionado para mandarlo a la base***/
        varSpin.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
