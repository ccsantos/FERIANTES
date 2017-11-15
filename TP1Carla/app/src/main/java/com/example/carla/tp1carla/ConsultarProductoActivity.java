package com.example.carla.tp1carla;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.carla.tp1carla.utilidades.Utilidades;

/**
 * Created by Carla on 12/11/2017.
 */

public class ConsultarProductoActivity extends AppCompatActivity {
    EditText campoIdP,campoNombre,campoCodigo,campoDescripcion,campoPrecio, campoCantidad;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_productos);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_feriantes",null,1);

        //campoIdP= (EditText) findViewById(R.id.campoIdPConsulta);
        campoNombre= (EditText) findViewById(R.id.campoNombreConsulta);
        campoCodigo= (EditText) findViewById(R.id.campoCodigoConsulta);
        campoDescripcion= (EditText) findViewById(R.id.campoDescripcionConsulta);
        campoPrecio= (EditText) findViewById(R.id.campoPrecioConsulta);
        campoCantidad= (EditText) findViewById(R.id.campoCantidadPConsulta);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnConsultar:consultar();
                break;
            case R.id.btnActualizar: actualizarProducto();
                break;
            case R.id.btnEliminar: eliminarProducto();
                break;
        }
    }

    private void eliminarProducto() {
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);
        dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogEliminar.setTitle(getResources().getString(R.string.eliminar_titulo));
        dialogEliminar.setMessage(getResources().getString(R.string.eliminar_mensaje));
        dialogEliminar.setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros={campoCodigo.getText().toString()};
                db.delete(Utilidades.TABLA_PRODUCTO, Utilidades.CAMPO_CODIGO+"=?",parametros);
                setResult(RESULT_OK);
                Toast.makeText(getApplicationContext(),"Producto eliminado",Toast.LENGTH_LONG).show();
                limpiar();
            }
        });
        dialogEliminar.setNegativeButton(android.R.string.no, null);
        dialogEliminar.show();
    }

    private void actualizarProducto() {
        AlertDialog.Builder dialogActualizar = new AlertDialog.Builder(this);
        dialogActualizar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogActualizar.setTitle(getResources().getString(R.string.actualizar_titulo));
        dialogActualizar.setMessage(getResources().getString(R.string.actualizar_mensaje));
        dialogActualizar.setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int boton) {
                SQLiteDatabase db=conn.getWritableDatabase();
                String[] parametros={campoCodigo.getText().toString()};
                ContentValues values = new ContentValues();
                values.put(Utilidades.CAMPO_NOMBREP,campoNombre.getText().toString());
                //values.put(Utilidades.CAMPO_CODIGO,campoCodigo.getText().toString());
                values.put(Utilidades.CAMPO_DESCRIPCION,campoDescripcion.getText().toString());
                values.put(Utilidades.CAMPO_PRECIO,campoPrecio.getText().toString());
                values.put(Utilidades.CAMPO_CANTIDAD_P,campoCantidad.getText().toString());
                db.update(Utilidades.TABLA_PRODUCTO,values,Utilidades.CAMPO_CODIGO+"=?",parametros);
                setResult(RESULT_OK);
                Toast.makeText(getApplicationContext(),"Producto actualizado",Toast.LENGTH_LONG).show();
            }
        });
        dialogActualizar.setNegativeButton(android.R.string.no, null);
        dialogActualizar.show();
    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        //CAMPOS POR EL QUE QUIERO BUSCAR
        String[] parametros={campoCodigo.getText().toString()};
        //CAMPOS QUE ME DEVUELVE
        String[] campos={Utilidades.CAMPO_NOMBREP,Utilidades.CAMPO_DESCRIPCION,Utilidades.CAMPO_PRECIO,Utilidades.CAMPO_CANTIDAD_P};
        try {
            Cursor cursor =db.query(Utilidades.TABLA_PRODUCTO,campos, Utilidades.CAMPO_CODIGO+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoDescripcion.setText(cursor.getString(1));
            campoPrecio.setText(cursor.getString(2));
            campoCantidad.setText(cursor.getString(3));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El codigo no existe",Toast.LENGTH_SHORT).show();
            limpiarE();
        }
    }

    private void limpiar() {
        //campoIdP.setText("");
        campoNombre.setText("");
        campoCodigo.setText("");
        campoDescripcion.setText("");
        campoPrecio.setText("");
        campoCantidad.setText("");
    }
    private void limpiarE() {
        campoNombre.setText("");
        //campoCodigo.setText("");
        campoDescripcion.setText("");
        campoPrecio.setText("");
        campoCantidad.setText("");
    }
}