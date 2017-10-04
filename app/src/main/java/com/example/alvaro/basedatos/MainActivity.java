package com.example.alvaro.basedatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
                Intent intent = new Intent (this, ResultadosActivity.class);
                intent.putExtra("id", id.getText().toString());
                intent.putExtra("nombre", nombre.getText().toString());
                intent.putExtra("apellido", apellido.getText().toString());
                startActivity(intent);

                /*SQLiteDatabase db = helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //Estructura_BBDD.COLUMN_NAME_ID,
                        Estructura_BBDD.COLUMN_NAME_NOMBRE,
                        Estructura_BBDD.COLUMN_NAME_APELLIDO
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = Estructura_BBDD.COLUMN_NAME_ID + " = ?";
                String[] selectionArgs = { id.getText().toString() };

                // How you want the results sorted in the resulting Cursor
                String sortOrder =
                        Estructura_BBDD.COLUMN_NAME_NOMBRE + " DESC";

                Cursor c = db.query(
                        Estructura_BBDD.TABLE_NAME,                     // The table to query
                        projection,                               // The columns to return
                        selection,                                // The columns for the WHERE clause
                        selectionArgs,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        sortOrder                                 // The sort order
                );
                if (c.getCount() > 0) {
                    c.moveToFirst();
                    String itemId = c.getString(
                            c.getColumnIndexOrThrow(Estructura_BBDD.COLUMN_NAME_NOMBRE)
                    );
                    Toast.makeText(this, itemId, Toast.LENGTH_LONG).show();
                    nombre.setText(c.getString(0));
                    apellido.setText(c.getString(1));
                }*/
                break;
            case R.id.btnInsertar:
                // Gets the data repository in write mode
                SQLiteDatabase db2 = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.COLUMN_NAME_ID, Integer.parseInt(id.getText().toString()));
                values.put(Estructura_BBDD.COLUMN_NAME_NOMBRE, nombre.getText().toString());
                values.put(Estructura_BBDD.COLUMN_NAME_APELLIDO, apellido.getText().toString());

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db2.insert(Estructura_BBDD.TABLE_NAME, null, values);

                Toast.makeText(this, "Se ha guardado " + newRowId, Toast.LENGTH_LONG).show();
                break;
            case R.id.btnActualizar:

                break;
            case R.id.btnBorrar:

                break;
        }
    }
}