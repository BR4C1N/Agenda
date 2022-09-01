package com.software.bancoDados;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.software.entidades.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDB {

    private static SQLiteDatabase conexao;

    public static void inserirContato(Contato contato, DBHelper db){
        conexao = db.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("nome", contato.getNome());
        valores.put("telefone", contato.getTelefone());

        conexao.insertOrThrow("Agenda", null, valores);
        conexao.close();
    }

    public static List<Contato> listarContatos(DBHelper db){
        List<Contato> listaContatos = new ArrayList<>();
        conexao = db.getReadableDatabase();

        return null;
    }

}
