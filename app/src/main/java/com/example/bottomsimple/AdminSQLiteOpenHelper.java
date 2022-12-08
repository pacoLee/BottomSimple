package com.example.bottomsimple;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDeDatos) {
        //baseDeDatos.execSQL("Drop TABLE MAZO ");
        baseDeDatos.execSQL("CREATE TABLE MAZO (ID_MAZO INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT,IMAGEID INTEGER)");
        //baseDeDatos.execSQL("INSERT INTO MAZO (NOMBRE,IMAGEID) VALUES ('Lars',1)");
        //baseDeDatos.execSQL("Drop TABLE MAZO_CARTA ");
        baseDeDatos.execSQL
                ("CREATE TABLE MAZO_CARTA (ID_MAZO INTEGER NOT NULL, ID_CARTA TEXT NOT NULL, CANTIDAD INT," +
                        " CONSTRAINT PK_MAZO PRIMARY KEY (ID_MAZO, ID_CARTA)," +
                        " FOREIGN KEY (ID_MAZO) REFERENCES MAZO(ID_MAZO))");
        //baseDeDatos.execSQL("INSERT INTO MAZO_CARTA (ID_MAZO, ID_CARTA , CANTIDAD) VALUES (1,'c55d896f-66d1-5e26-ac69-049d5d996a75',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}