package com.example.alvaro.basedatos;

import android.provider.BaseColumns;

/**
 * Created by Alvaro on 03/10/2017.
 */

public class Estructura_BBDD {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Estructura_BBDD() {}

    /* Inner class that defines the table contents */
    //public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "datosPersonales";
        public static final String COLUMN_NAME_ID = "Id";
        public static final String COLUMN_NAME_NOMBRE = "Nombre";
        public static final String COLUMN_NAME_APELLIDO = "Apellido";
    //}

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BBDD.TABLE_NAME + " (" +
                    Estructura_BBDD.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Estructura_BBDD.COLUMN_NAME_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    Estructura_BBDD.COLUMN_NAME_APELLIDO + TEXT_TYPE + " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;
}