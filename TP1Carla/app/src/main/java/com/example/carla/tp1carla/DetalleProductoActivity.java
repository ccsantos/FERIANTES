package com.example.carla.tp1carla;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.carla.tp1carla.entidades.Producto;

public class DetalleProductoActivity extends AppCompatActivity {
    TextView campoId, campoNombre, campoCodigo,campoDescripcion,campoPrecio, campoCantidad;
    ImageView campoImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        campoId = (TextView) findViewById(R.id.campoId);
        campoNombre = (TextView) findViewById(R.id.campoNombre);
        campoCodigo = (TextView) findViewById(R.id.campoCodigo);
        campoDescripcion=(TextView)findViewById(R.id.campoDescripcion);
        campoPrecio=(TextView)findViewById(R.id.campoPrecio);
        campoCantidad=(TextView)findViewById(R.id.campoCantidad);
        campoImagen = (ImageView)findViewById(R.id.campoImagen);

        Bundle objetoEnviado=getIntent().getExtras();
        Producto producto=null;
        if(objetoEnviado!=null){
            producto= (Producto) objetoEnviado.getSerializable("producto");
            campoId.setText(producto.getIdP().toString());
            campoNombre.setText(producto.getNombreP().toString());
            campoCodigo.setText(producto.getCodigo().toString());
            campoDescripcion.setText(producto.getDescripcion().toString());
            campoPrecio.setText(producto.getPrecio().toString());
            campoCantidad.setText(producto.getCantidad().toString());
            if (producto.getImagenP() != null){
                byte[] bitmapdata = producto.getImagenP(); // let this be your byte array
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata , 0, bitmapdata .length);
                campoImagen.setImageBitmap(bitmap);
                campoImagen.setVisibility(View.VISIBLE);
            }
        }
    }
}