package mx.edu.uacm.recetas_cocina;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AgregarRecetas extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //referencia a la base de datos del nodo principal
    DatabaseReference mRootReference;
    Button btnSubirDatosFirebase,galeria;
    EditText etNombre,varSpin,etIngredientes,etPreparacion;
    Spinner spin;
    RatingBar ratingBar;
    ImageView imagen;
    static final int GALLERI_INTENT = 1;
    private Bitmap bitmap;
    String tit = "usuario";
    String bod = "se agrego receta";
    FirebaseUser user;

    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID = "NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_recetas);

        // inicia en el nodo principal
        mRootReference = FirebaseDatabase.getInstance().getReference();
        //final MyFirebaseMessagingService m1 = new MyFirebaseMessagingService();

        //Para obtener el usuario actual en la aplicacion
        user = FirebaseAuth.getInstance().getCurrentUser();

        /***Relacionando variables back con front***/
        btnSubirDatosFirebase = findViewById(R.id.btn_agregar);
        etNombre =(EditText) findViewById(R.id.edt_nombre);
        varSpin =(EditText) findViewById(R.id.edt_aux);
        spin = (Spinner)findViewById(R.id.menuCat);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        etIngredientes = (EditText)findViewById(R.id.edt_ingredientes);
        etPreparacion = (EditText)findViewById(R.id.edt_preparacion);
        imagen = (ImageView)findViewById(R.id.imagenRec);
        galeria=(Button)findViewById(R.id.bt_galeria);

        /***Boton de la galeria***/

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();

            }
        });




        /****Evento al pulsar el boton*****/
        btnSubirDatosFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //createNotificationChannel();
                //createNotification();

                //m1.showNotification(tit,bod);

                String nombre = etNombre.getText().toString();
                String categoria = varSpin.getText().toString();
                Float calificacion = ratingBar.getRating();
                String ingredientes = etIngredientes.getText().toString();
                String preparacion = etPreparacion.getText().toString();
                String foto = getStringImagen(bitmap);
                String nombreUsuario=user.getDisplayName();
                String correo=user.getEmail();

                // cargar los valores y guardarlos en firebase

                Map<String, Object> datosUsuario = new HashMap<>();
                datosUsuario.put("Nombre",nombre);
                datosUsuario.put("Categoria",categoria);
                datosUsuario.put("Calificacion",calificacion);
                datosUsuario.put("Ingredientes",ingredientes);
                datosUsuario.put("Preparacion",preparacion);
                datosUsuario.put("Foto",foto);
                //Agregan los datos del usuario
                datosUsuario.put("Email",correo);
                datosUsuario.put("Usuario",nombreUsuario);

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


    private void cargarImagen(){

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"selecione la aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){  // verifica si obtuvimos bien la imagen
            Uri path = data.getData();
            imagen.setImageURI(path);




            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                //Configuración del mapa de bits en ImageView
                imagen.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //DatabaseReference destino = mRootReference.child("foto").child(Uri.)
        }
    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.JPEG, 30, baos);

        byte[] imageBytes = baos.toByteArray();
        String encodedImage =  Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }
/*
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Noticacion";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_stat_ic_notification);
        builder.setContentTitle("Notificacion Android");
        builder.setContentText("Apuntate a mis Cursos de Udemy");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000, 1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);


        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
    }
*/



}
