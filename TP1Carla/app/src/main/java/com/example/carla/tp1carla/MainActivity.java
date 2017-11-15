package com.example.carla.tp1carla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"base_feriantes",null,1);
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnProductos:
                miIntent=new Intent(MainActivity.this,MainProductoActivity.class);
                Toast toastP= Toast.makeText(this, "Menu Productos", Toast.LENGTH_SHORT);
                toastP.setGravity(Gravity.TOP,0,0);
                toastP.show();
                break;

            case R.id.btnVentas:
               // miIntent=new Intent(MainActivity.this,MainVentaActivity.class);
                Toast toastV= Toast.makeText(this, "Menu Ventas", Toast.LENGTH_SHORT);
                toastV.setGravity(Gravity.TOP,0,0);
                toastV.show();
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}