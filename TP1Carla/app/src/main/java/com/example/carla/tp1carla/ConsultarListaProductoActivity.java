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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Carla on 12/11/2017.
 */
import com.example.carla.tp1carla.entidades.Producto;
import com.example.carla.tp1carla.utilidades.Utilidades;

public class ConsultarListaProductoActivity extends AppCompatActivity {
    private Button btnBuscar;
    private Button btnCancelar;
    private EditText campoNombre;
    private EditText campoCodigo;
    private CheckBox ckbNombre;
    private CheckBox ckbCodigo;
    ListView listViewProductos;
    ArrayList<String> listaInformacion;
    ArrayList<Producto> listaProductos;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_producto);
        btnBuscar = (Button) findViewById(R.id.btn_buscar);
        btnCancelar = (Button) findViewById(R.id.btn_cancelar);
        campoNombre = (EditText) findViewById(R.id.txt_nombre);
        campoCodigo = (EditText) findViewById(R.id.txt_codigo);
        ckbNombre = (CheckBox) findViewById(R.id.ckb_nombre);
        ckbCodigo = (CheckBox) findViewById(R.id.ckb_codigo);
        ckbNombre.setChecked(false);
        ckbCodigo.setChecked(false);
        listViewProductos = (ListView) findViewById(R.id.listViewProductos);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_feriantes", null, 1);
        consultarListaProductos();
        ckbNombre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                limpiar();
            }
        });
        ckbCodigo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                limpiar();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (ckbNombre.isChecked()){
                    consultarPorNombreProductos();
                }
                else if (ckbCodigo.isChecked()){
                    consultarPorCodigoProductos();
                }
                limpiar();
                ckbNombre.setChecked(false);
                ckbCodigo.setChecked(false);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                consultarListaProductos();
                limpiar();
                ckbNombre.setChecked(false);
                ckbCodigo.setChecked(false);
            }
        });

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String informacion = "Id: " + listaProductos.get(pos).getIdP() + "\n";
                informacion += "Nombre: " + listaProductos.get(pos).getNombreP() + "\n";
                informacion += "Codigo: " + listaProductos.get(pos).getCodigo() + "\n";
                informacion += "Descripcion: " + listaProductos.get(pos).getDescripcion() + "\n";
                informacion += "Precio: " + listaProductos.get(pos).getPrecio() + "\n";
                informacion += "Cantidad: " + listaProductos.get(pos).getCantidad() + "\n";
                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();
                Producto producto = listaProductos.get(pos);
                Intent intent = new Intent(ConsultarListaProductoActivity.this, DetalleProductoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("producto", producto);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto producto = null;
        listaProductos = new ArrayList<Producto>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PRODUCTO, null);
        while (cursor.moveToNext()) {
            producto = new Producto();
            producto.setIdP(cursor.getInt(0));
            producto.setNombreP(cursor.getString(1));
            producto.setCodigo(cursor.getInt(2));
            producto.setDescripcion(cursor.getString(3));
            producto.setPrecio(cursor.getDouble(4));
            producto.setCantidad(cursor.getInt(5));
            producto.setImagenP(cursor.getBlob(6));
            listaProductos.add(producto);
        }
        obtenerLista();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewProductos.setAdapter(adaptador);
    }

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
            producto.setPrecio(cursor.getDouble(4));
            producto.setCantidad(cursor.getInt(5));
            producto.setImagenP(cursor.getBlob(6));
            listaProductos.add(producto);
        }
        obtenerLista();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewProductos.setAdapter(adaptador);
    }

    private void consultarPorNombreProductos() {
        Producto producto = null;
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros={campoNombre.getText().toString()};
        String[] campos={Utilidades.CAMPO_IDPRODUCTO,Utilidades.CAMPO_NOMBREP,Utilidades.CAMPO_CODIGO,Utilidades.CAMPO_DESCRIPCION,Utilidades.CAMPO_PRECIO,Utilidades.CAMPO_CANTIDAD_P,Utilidades.CAMPO_IMAGEN};
        listaProductos = new ArrayList<Producto>();
        Cursor cursor =db.query(Utilidades.TABLA_PRODUCTO,campos, Utilidades.CAMPO_NOMBREP+"=?",parametros,null,null,null);
        while(cursor.moveToNext()){
            producto = new Producto();
            producto.setIdP(cursor.getInt(0));
            producto.setNombreP(cursor.getString(1));
            producto.setCodigo(cursor.getInt(2));
            producto.setDescripcion(cursor.getString(3));
            producto.setPrecio(cursor.getDouble(4));
            producto.setCantidad(cursor.getInt(5));
            producto.setImagenP(cursor.getBlob(6));
            listaProductos.add(producto);
        }
        obtenerLista();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewProductos.setAdapter(adaptador);
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i < listaProductos.size(); i++) {
            listaInformacion.add(listaProductos.get(i).getIdP() + " - Nombre: "
                    + listaProductos.get(i).getNombreP() + " - Cod: "
                    + listaProductos.get(i).getCodigo() + " - $: "
                    + listaProductos.get(i).getPrecio() + " - U: "
                    + listaProductos.get(i).getCantidad());
        }
        if (listaProductos.isEmpty()){
            Toast toastV= Toast.makeText(this, "No hay productos registrados", Toast.LENGTH_LONG);
            toastV.setGravity(Gravity.CENTER,0,0);
            toastV.show();
        }
    }

    private void limpiar() {
        campoNombre.setText("");
        campoCodigo.setText("");
    }
}