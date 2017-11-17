package com.example.carla.tp1carla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.carla.tp1carla.entidades.Venta;

public class DetalleVentaActivity extends AppCompatActivity {
    TextView campoIdV, campoFecha, campoCantidad,campoMonto,campoIdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_venta);
        campoIdV = (TextView) findViewById(R.id.campoIdV);
        campoFecha = (TextView) findViewById(R.id.campoFecha);
        campoMonto=(TextView)findViewById(R.id.campoMonto);
        campoCantidad = (TextView) findViewById(R.id.campoCantidad);
        campoIdp=(TextView)findViewById(R.id.campoIdp);

        Bundle objetoEnviado=getIntent().getExtras();
        Venta venta = null;
        if(objetoEnviado!=null){
            venta= (Venta) objetoEnviado.getSerializable("venta");
            campoIdV.setText(venta.getIdVenta().toString());
            campoFecha.setText(venta.getFechaVenta().toString());
            campoMonto.setText(venta.getMontoTotal().toString());
            campoCantidad.setText(venta.getCantidadV().toString());
            campoIdp.setText(venta.getId_Producto().toString());
        }
    }
}