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
        if (intent.hasExtra("nombre")) {
            String nombre = intent.getStringExtra("nombre");

            SQLiteDatabase db = helper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    //Estructura_BBDD.COLUMN_NAME_ID,
                    Estructura_BBDD.COLUMN_NAME_NOMBRE,
                    Estructura_BBDD.COLUMN_NAME_APELLIDO
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = Estructura_BBDD.COLUMN_NAME_ID + " = ?";
            String[] selectionArgs = {nombre};

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

                ListView lista = (ListView) findViewById(R.id.lista);
                int[]  to = new int[] { R.id.txtNombre };
                SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.activity_resultados, c, projection, to);
                lista.setAdapter(adaptador);
                /*nombre.setText(c.getString(0));
                apellido.setText(c.getString(1));*/
            }
        }
    }
}
