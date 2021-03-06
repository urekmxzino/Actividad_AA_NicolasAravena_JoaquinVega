package com.aravena.actividad_semana_aa.sqlite;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.aravena.actividad_semana_aa.models.Estante;

import java.util.ArrayList;


public class DbEstante extends DbHelper {
    Context context;

    public DbEstante(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public long insertarEstante(Estante estante) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        long res = 0;

        ContentValues valores = new ContentValues();
        valores.put("letra", estante.getLetra());
        valores.put("numero", estante.getNumero());
        valores.put("color", estante.getColor());

        res = db.insert(DbHelper.TABLE_ESTANTE, null, valores);

        return res;

    }

    public ArrayList<Estante> getEstantes() {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<Estante> estantes = new ArrayList<>();
        Cursor cursor = null;
        Estante estante = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_ESTANTE, null);
        if (cursor.moveToFirst()) {
            do {
                estante = new Estante(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)
                );
                estantes.add(estante);
            } while (cursor.moveToNext());

        }
        return estantes;
    }

    public Estante getEstante(int id) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        Estante estanteObtenido;
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_ESTANTE + " WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {

                estanteObtenido = new Estante(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)
                );
                return estanteObtenido;
            }else{
                return null;
            }

        }


    public int eliminarEstante(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        int res = db.delete(TABLE_ESTANTE,"id = ?",new String[]{ String.valueOf(id) });
        return res;
    }



    public int actualizarEstante(Estante estante){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();


        ContentValues valores = new ContentValues();
        valores.put("letra", estante.getLetra());
        valores.put("numero", estante.getNumero());
        valores.put("color", estante.getColor());

        int resultado = db.update(TABLE_ESTANTE,valores,
                "id = ?", new String[] { String.valueOf(estante.getId()) } );

        return resultado;

    }
    }







