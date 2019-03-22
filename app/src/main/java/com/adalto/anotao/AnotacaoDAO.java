package com.adalto.anotao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AnotacaoDAO {

    public static final void inserir(Anotacao nota, Context context){
        Banco banco = new Banco(context);
        ContentValues valores = new ContentValues();
        valores.put("titulo", nota.getTitulo() );
        valores.put("texto", nota.getTexto() );
        SQLiteDatabase db = banco.getWritableDatabase();
        db.insert("anotacoes", null, valores);
    }

    public static final void excluir(int idNota, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("anotacoes", "id = "+idNota, null);
    }

    public static final List<Anotacao> listar(Context context){
        List<Anotacao> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        String sql = "SELECT * FROM anotacoes ORDER BY id DESC ";
        Cursor cursor = db.rawQuery(sql, null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Anotacao nota = new Anotacao();
                nota.setId( cursor.getInt( 0 ) );
                nota.setTitulo( cursor.getString( 1 ) );
                nota.setTexto( cursor.getString( 2 ) );
                lista.add( nota );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

}








