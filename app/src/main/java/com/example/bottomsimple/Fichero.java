package com.example.bottomsimple;

import android.util.JsonReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Fichero {

    /* funcion para saber si existe un archivo */
    public static boolean existe_archivo(String archivos[], String nombreArchivo){
        for(int i=0; i < archivos.length; i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;

        return false;
    }


    /* funcion para abrir fichero */
    public static String abrir_fichero( String nombreFichero ) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(nombreFichero));

        String content = "";
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            content=content + line;
        }

        return  content;
    }

}
