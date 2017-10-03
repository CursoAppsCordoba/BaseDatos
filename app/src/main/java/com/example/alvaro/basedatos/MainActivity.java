package com.example.alvaro.basedatos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText id, nombre, apellido;
    final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);
        Button btnInsertar = (Button)findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(this);
        Button btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(this);
        Button btnBorrar = (Button)findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(this);

        id = (EditText)findViewById(R.id.id);
        nombre = (EditText)findViewById(R.id.nombre);
        apellido = (EditText)findViewById(R.id.apellido);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuscar:

                break;
            case R.id.btnInsertar:
                // Gets the data repository in write mode
                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.COLUMN_NAME_ID, Integer.parseInt(id.getText().toString()));
                values.put(Estructura_BBDD.COLUMN_NAME_NOMBRE, nombre.getText().toString());
                values.put(Estructura_BBDD.COLUMN_NAME_APELLIDO, apellido.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

                Toast.makeText(this, "Se ha guardado " + newRowId, Toast.LENGTH_LONG).show();
                break;
            case R.id.btnActualizar:

                break;
            case R.id.btnBorrar:

                break;
        }
    }
}