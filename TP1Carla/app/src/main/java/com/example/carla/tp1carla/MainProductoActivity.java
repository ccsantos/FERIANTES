package com.example.carla.tp1carla;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Carla on 12/11/2017.
 */

public class MainProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_producto);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"base_feriantes",null,1);
    }

    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainProductoActivity.this,RegistroProductoActivity.class);
                break;
            case R.id.btnEditarIndividual:
                miIntent=new Intent(MainProductoActivity.this,ConsultarProductoActivity.class);
                break;
            case R.id.btnConsultaLista:
                miIntent=new Intent(MainProductoActivity.this,ConsultarListaProductoActivity.class);
                break;

        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }
}