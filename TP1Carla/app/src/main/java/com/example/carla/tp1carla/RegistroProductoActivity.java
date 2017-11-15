package com.example.carla.tp1carla;

import com.example.carla.tp1carla.utilidades.*;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by Carla on 12/11/2017.
 */

public class RegistroProductoActivity extends AppCompatActivity {

    EditText campoIma,campoId,campoNombre,campoCodigo,campoDescripcion, campoPrecio,campoCantidad;
    ImageView campoImagen;
    String mensaje;
    Button btnImg;
    boolean saco_foto;
    Bitmap bMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_productos);

        //campoId= (EditText) findViewById(R.id.campoId);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoCantidad= (EditText) findViewById(R.id.campoCantidad);
        campoCodigo= (EditText) findViewById(R.id.campoCodigo);
        campoDescripcion= (EditText) findViewById(R.id.campoDescripcion);
        campoPrecio= (EditText) findViewById(R.id.campoPrecio);

        campoIma = (EditText) findViewById(R.id.txt_Imagen);
        btnImg = (Button) findViewById(R.id.btn_Imagen);
        campoImagen = (ImageView) findViewById(R.id.iv_imagen);
        saco_foto = false;

        btnImg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1);
            }
        });

    }

    public void onClick(View view) {
        registrarProductos();
    }

    private void registrarProductos() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_feriantes",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(TextUtils.isEmpty(campoNombre.getText().toString())){
            Toast.makeText(this, "Ingresar Nombre! ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(campoCodigo.getText().toString())){
            Toast.makeText(this, "Ingresar Codigo! ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(campoPrecio.getText().toString())){
            Toast.makeText(this, "Ingresar Precio! ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(campoCantidad.getText().toString())){
            Toast.makeText(this, "Ingresar Cantidad! ", Toast.LENGTH_SHORT).show();
            return;
        }
        values.put(Utilidades.CAMPO_NOMBREP,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_CODIGO,campoCodigo.getText().toString());
        values.put(Utilidades.CAMPO_DESCRIPCION,campoDescripcion.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO,campoPrecio.getText().toString());
        values.put(Utilidades.CAMPO_CANTIDAD_P,campoCantidad.getText().toString());
        if (saco_foto){
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bMap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                values.put(Utilidades.CAMPO_IMAGEN,byteArray);
        }

        Long idResultante = db.insert(Utilidades.TABLA_PRODUCTO,Utilidades.CAMPO_CODIGO,values);
        if (idResultante!=-1) {
            Toast.makeText(getApplicationContext(), "Producto: " + idResultante + " guardado! ", Toast.LENGTH_SHORT).show();
            limpiar();
        }
        else{
            Toast.makeText(getApplicationContext(), "Producto no guardado! El Codigo ya existe! ", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    private void limpiar() {
        //campoId.setText("");
        campoNombre.setText("");
        campoCodigo.setText("");
        campoDescripcion.setText("");
        campoPrecio.setText("");
        campoCantidad.setText("");
        campoIma.setText("");
    }
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            saco_foto = true;
            Bundle extras = data.getExtras();
            bMap = (Bitmap) extras.get("data");
            //Añadimos el bitmap al imageView para
            // mostrarlo por pantalla
            campoImagen.setImageBitmap(bMap);

        }
    }


}