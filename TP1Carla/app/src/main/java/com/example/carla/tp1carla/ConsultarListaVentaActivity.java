package com.example.carla.tp1carla;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.carla.tp1carla.entidades.Producto;
import com.example.carla.tp1carla.entidades.Venta;
import com.example.carla.tp1carla.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.Date;

public class ConsultarListaVentaActivity extends AppCompatActivity {
    private Button btnBuscar;
    private Button btnCancelar;
    private EditText campoFecha;
    private EditText campoIdp;
    private CheckBox ckbFecha;
    private CheckBox ckbIdp;
    ListView listViewVentas;
    ArrayList<String> listaInformacion;
    ArrayList<Venta> listaVentas;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_venta);
        btnBuscar = (Button) findViewById(R.id.btn_buscar);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);
        campoFecha = (EditText) findViewById(R.id.txt_fecha);
        campoIdp = (EditText) findViewById(R.id.txt_idp);
        ckbFecha = (CheckBox) findViewById(R.id.ckb_fecha);
        ckbIdp = (CheckBox) findViewById(R.id.ckb_idp);
        ckbFecha.setChecked(false);
        ckbIdp.setChecked(false);
        listViewVentas = (ListView) findViewById(R.id.listViewVentas);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_feriantes", null, 1);
        consultarListaVentas();
        ckbFecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                limpiar();
            }
        });
        ckbIdp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                limpiar();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (ckbFecha.isChecked()){
                    //consultarPorFechaVentas();
                }
                else if (ckbIdp.isChecked()){
                    // consultarPorCodigoProductos();
                }
                limpiar();
                ckbFecha.setChecked(false);
                ckbIdp.setChecked(false);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                consultarListaVentas();
                limpiar();
                ckbFecha.setChecked(false);
                ckbIdp.setChecked(false);
            }
        });

        listViewVentas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "Id: " + listaVentas.get(pos).getIdVenta() + "\n";
                informacion += "Fecha: " + listaVentas.get(pos).getFechaVenta() + "\n";
                informacion += "Cantidad: " + listaVentas.get(pos).getCantidadV() + "\n";
                informacion += "Total: " + listaVentas.get(pos).getMontoTotal() + "\n";
                informacion += "Id Producto: " + listaVentas.get(pos).getId_Producto() + "\n";
                //informacion += "Nombre del producto vendiddo: " + listaProductos.get(pos).getCantidad() + "\n";
                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();
                Venta venta = listaVentas.get(pos);
                Intent intent = new Intent(ConsultarListaVentaActivity.this, DetalleVentaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("venta", venta);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void consultarListaVentas() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Venta venta = null;
        listaVentas = new ArrayList<Venta>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_VENTA, null);
        while (cursor.moveToNext()) {
            venta = new Venta();
            venta.setIdVenta(cursor.getInt(0));
            //ver como se obtiene el campo date con el cursor
            //venta.setFechaVenta(cursor.getString(1));
           // venta.setFechaVenta((loadDate(cursor, campoFecha)));
            venta.setMontoTotal(cursor.getFloat(2));
            venta.setCantidadV(cursor.getInt(3));
            venta.setId_Producto(cursor.getInt(4));
            listaVentas.add(venta);
        }
        obtenerLista();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewVentas.setAdapter(adaptador);
    }
/*
    private void consultarPorCodigoProductos() {
        Producto producto = null;
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros={campoCodigo.getText().toString()};
        String[] campos={Utilidades.CAMPO_IDPRODUCTO,Utilidades.CAMPO_NOMBREP,Utilidades.CAMPO_CODIGO,Utilidades.CAMPO_DESCRIPCION,Utilidades.CAMPO_PRECIO,Utilidades.CAMPO_CANTIDAD_P,Utilidades.CAMPO_IMAGEN};
        listaProductos = new ArrayList<Producto>();
        Cursor cursor =db.query(Utilidades.TABLA_PRODUCTO,campos, Utilidades.CAMPO_CODIGO+"=?",parametros,null,null,null);
        while(cursor.moveToNext()){
            producto = new Producto();
            producto.setIdP(cursor.getInt(0));
            producto.setNombreP(cursor.getString(1));
            producto.setCodigo(cursor.getInt(2));
            producto.setDescripcion(cursor.getString(3));
            producto.setPrecio(cursor.getFloat(4));
            producto.setCantidad(cursor.getInt(5));
            producto.setImagenP(cursor.getBlob(6));
            listaProductos.add(producto);
        }
        obtenerLista();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewProductos.setAdapter(adaptador);
    }
*//*
    private void consultarPorFechaVentas() {
        Venta venta = null;
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros={campoFecha.getText().toString()};
        String[] campos={Utilidades.CAMPO_IDVENTA,Utilidades.CAMPO_FECHAVENTA,Utilidades.CAMPO_MONTO,Utilidades.CAMPO_CANTIDAD_V,Utilidades.CAMPO_ID_PRODUCTO};
        listaVentas = new ArrayList<Venta>();
        Cursor cursor =db.query(Utilidades.TABLA_VENTA,campos, Utilidades.CAMPO_FECHAVENTA+"=?",parametros,null,null,null);
        while(cursor.moveToNext()){
            venta = new Venta();
            venta.setId_Producto(cursor.getInt(0));
            //ver como obbtengo el campo fecha con el cursor
            // venta.setFechaVenta(cursor.getString(1));
            venta.setCantidadV(cursor.getInt(2));
            venta.setMontoTotal(cursor.getFloat(3));
            venta.setId_Producto(cursor.getInt(4));
            listaVentas.add(venta);
        }
        obtenerLista();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewVentas.setAdapter(adaptador);
    }
*/
    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaVentas.size(); i++) {
            listaInformacion.add(listaVentas.get(i).getIdVenta() + " - Fecha: "
                    + listaVentas.get(i).getFechaVenta() + " - Cantidad: "
                    + listaVentas.get(i).getCantidadV() + " - Monto: "
                    + listaVentas.get(i).getMontoTotal() + " - Id Prod: "
                    + listaVentas.get(i).getId_Producto());
        }
        if (listaVentas.isEmpty()){
            Toast toastV= Toast.makeText(this, "No hay ventas realizadas", Toast.LENGTH_LONG);
            toastV.setGravity(Gravity.CENTER,0,0);
            toastV.show();
        }
    }
    private void limpiar() {
        campoFecha.setText("");
        campoIdp.setText("");
    }
}