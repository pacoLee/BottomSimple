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
        baseDeDatos.execSQL("CREATE TABLE MAZO (ID_MAZO INT NOT NULL PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT,IMAGEID TEXT)");
        baseDeDatos.execSQL
                ("CREATE TABLE MAZO_CARTA (ID_MAZO INT NOT NULL, ID_CARTA TEXT NOT NULL, CANTIDAD INT," +
                        " CONSTRAINT PK_MAZO PRIMARY KEY (ID_MAZO, ID_CARTA)," +
                        " FOREIGN KEY (ID_MAZO) REFERENCES MAZO(ID_MAZO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}