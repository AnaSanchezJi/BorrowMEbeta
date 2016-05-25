package com.example.ana.borrowmebeta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apc-ubu on 24/05/16.
 */
public class ConexionBD extends SQLiteOpenHelper {
    public ConexionBD (Context c, String nombreBd, SQLiteDatabase.CursorFactory  cf, int version){
        super(c,nombreBd,cf,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Prestamos (IDprestamo INTEGER PRIMARY KEY,Categoria VARCHAR(50),ObjetoPres VARCHAR(100),Prestatario varchar(100),FechPrest DATE,FechRec DATE,Estatus INTEGER)");
        db.execSQL("Create Table Recuperados (IDprestamo INTEGER PRIMARY KEY,Categoria VARCHAR(50),ObjetoPres VARCHAR(100),Prestatario varchar(100),FechPrest DATE,FechRec DATE,FechRealRec DATE) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
