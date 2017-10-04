package com.example.alvaro.basedatos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ResultadosActivity extends AppCompatActivity {
    final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent intent = getIntent();
        if (intent.hasExtra("id") && intent.hasExtra("nombre") && intent.hasExtra("apellido")) {
            String id = "%"+ intent.getStringExtra("id") +"%";
            String nombre = "%" + intent.getStringExtra("nombre") + "%";
            String apellido = "%" + intent.getStringExtra("apellido") + "%";


            SQLiteDatabase db = helper.getReadableDatabase();

            String[] projection = {
                    Estructura_BBDD.COLUMN_NAME_ID,
                    Estructura_BBDD.COLUMN_NAME_NOMBRE,
                    Estructura_BBDD.COLUMN_NAME_APELLIDO
            };

            String selection = Estructura_BBDD.COLUMN_NAME_ID + " LIKE ? AND " + Estructura_BBDD.COLUMN_NAME_NOMBRE + " LIKE ? AND " + Estructura_BBDD.COLUMN_NAME_APELLIDO + " LIKE ?";
            String[] selectionArgs = {id, nombre, apellido};

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
                ListView lista = (ListView) findViewById(R.id.lista);

                String[] from = new String[]{"nombre", "apellido"};
                int[]  to = new int[] { R.id.txtNombre, R.id.txtApellido };

                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.texto_lista, c, from, to);
                lista.setAdapter(adaptador);
                /*nombre.setText(c.getString(0));
                apellido.setText(c.getString(1));*/
            }
        }
    }
}
