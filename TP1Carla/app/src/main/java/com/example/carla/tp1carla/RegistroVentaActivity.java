package com.example.carla.tp1carla;
/** public static final String TABLA_VENTA = "venta";
    public static final String CAMPO_IDVENTA = "idV";
    public static final String CAMPO_FECHAVENTA = "fechaVenta";
    public static final String CAMPO_MONTO = "montoTotal";
    public static final String CAMPO_CANTIDAD_V = "cantidad";
    public static final String CAMPO_ID_PRODUCTO = "id_producto";
*/
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import com.example.carla.tp1carla.entidades.Producto;
import com.example.carla.tp1carla.entidades.Venta;
import com.example.carla.tp1carla.utilidades.*;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RegistroVentaActivity extends AppCompatActivity {
    EditText campoMonto;
    EditText campoCantidadV;
    EditText campoFecha;
    Integer campoIDP;
    private Spinner cmbProductos;
    private Button btnFecha;
    private int campoDia, campoMes, campoAno;
    private float precioFinal;
    private float precio_txt_monto;
    ConexionSQLiteHelper conn;
    ListView listViewProductos;
    ArrayList<Producto> listaProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ventas);
        campoCantidadV = (EditText) findViewById(R.id.campoCantidadV);
        campoMonto = (EditText) findViewById(R.id.campoMonto);
        campoFecha = (EditText) findViewById(R.id.campoFecha);
        cmbProductos = (Spinner) findViewById(R.id.cmb_productos);
        btnFecha = (Button) findViewById(R.id.btn_fecha);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_feriantes", null, 1);
        listViewProductos = (ListView) findViewById(R.id.listViewProductos);

        ArrayList<String> valores = new ArrayList<>();
        precioFinal = 0;
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
            producto.setPrecio(cursor.getFloat(4));
            producto.setCantidad(cursor.getInt(5));
            producto.setImagenP(cursor.getBlob(6));
            listaProductos.add(producto);
        }
        for (Producto prod : listaProductos) {
            valores.add(prod.getNombreP());
        }

        cmbProductos.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores));

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                campoDia = c.get(Calendar.DAY_OF_MONTH);
                campoMes = c.get(Calendar.MONTH);
                campoAno = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistroVentaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        campoFecha.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        campoDia = dayOfMonth;
                        campoMes = month + 1;
                        campoAno = year;
                    }
                }
                        , campoDia, campoMes, campoAno);
                datePickerDialog.show();
            }
        });

        campoCantidadV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (!listaProductos.isEmpty()){
                    String nombreProducto = cmbProductos.getSelectedItem().toString();
                    for(Producto prod : listaProductos){
                        if (prod.getNombreP().equals(nombreProducto)){
                            precio_txt_monto = prod.getPrecio();
                            break;
                        }
                    }
                }
            }
        });

        campoCantidadV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    if (!listaProductos.isEmpty()){
                        String nombreProducto = cmbProductos.getSelectedItem().toString();
                        for(Producto prod : listaProductos){
                            if (prod.getNombreP().equals(nombreProducto)){
                                precio_txt_monto = prod.getPrecio();
                                break;
                            }
                        }
                    }
                }
            }
        });

        campoCantidadV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (campoCantidadV.getText().toString().compareToIgnoreCase("") == 0) {
                    campoMonto.setText("");
                } else {
                    int number = Integer.parseInt(s.toString());
                    campoMonto.setText("$" + number * precio_txt_monto);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cmbProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                campoCantidadV.setText("");
                campoMonto.setText("");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }

    public void onClick(View view) {
        registrarVenta();
    }

    private void registrarVenta() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_feriantes", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        if (listaProductos.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No hay productos registrados", Toast.LENGTH_SHORT).show();
            return;
        } else if (campoFecha.getText().toString().compareToIgnoreCase("") == 0) {
            Toast.makeText(getApplicationContext(), "Ingrese fecha", Toast.LENGTH_SHORT).show();
            return;
        } else if (campoCantidadV.getText().toString().compareToIgnoreCase("") == 0) {
            Toast.makeText(getApplicationContext(), "Ingrese cantidad", Toast.LENGTH_SHORT).show();
            return;
        }
        String nombreProducto = cmbProductos.getSelectedItem().toString();
        long pk = 0;
        int stock = 0;
        int txtstock = Integer.parseInt(campoCantidadV.getText().toString());
        float precio = 0;
        Producto pro = new Producto();
        for (Producto prod : listaProductos) {
            if (prod.getNombreP().equals(nombreProducto)) {
                pk = prod.getIdP();
                stock = prod.getCantidad();
                precio = prod.getPrecio();
                pro = prod;
                break;
            }
        }
        if ((stock == 0) || (txtstock > stock)) {
            Toast.makeText(getApplicationContext(), "No hay productos suficientes", Toast.LENGTH_SHORT).show();
        } else {
            Calendar myCal = Calendar.getInstance();
            myCal.set(Calendar.YEAR, campoAno);
            myCal.set(Calendar.MONTH, campoMes);
            myCal.set(Calendar.DAY_OF_MONTH, campoDia);
            Date fecha = myCal.getTime();
            Venta venta = new Venta();
            venta.setFechaVenta(fecha);
            Integer i = (int) (long) pk;
            venta.setId_Producto(i);
            venta.setCantidadV(txtstock);
            venta.setMontoTotal(precio * txtstock);
            pro.setCantidad(pro.getCantidad() - txtstock);

            campoIDP=pro.getIdP();
            precioFinal = precio * txtstock;
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_FECHAVENTA, campoFecha.getText().toString());
            values.put(Utilidades.CAMPO_CANTIDAD_V, campoCantidadV.getText().toString());
            values.put(Utilidades.CAMPO_MONTO, campoMonto.getText().toString());
            values.put(Utilidades.CAMPO_ID_PRODUCTO,campoIDP);
           // ContentValues v2 = new ContentValues();
           // v2.put(Utilidades.CAMPO_IDPRODUCTO,);
            Long idResultante = db.insert(Utilidades.TABLA_VENTA, Utilidades.CAMPO_IDVENTA, values);
            //db.update(Utilidades.TABLA_PRODUCTO,Utilidades.CAMPO_CANTIDAD_P,);
            if (idResultante != -1) {
                Toast.makeText(getApplicationContext(), "Venta: " + idResultante + " guardada! ", Toast.LENGTH_SHORT).show();
                limpiar();
            } else {
                Toast.makeText(getApplicationContext(), "Venta no guardada!! ", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }

    private void limpiar() {
        cmbProductos.setSelection(0);
        campoFecha.setText("");
        campoCantidadV.setText("");
    }
}
