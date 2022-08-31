package com.software.agenda;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.software.bancoDados.DBHelper;
import com.software.entidades.Contato;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText campoNome;
    EditText campoTelefone;
    Button botaoSalvar;

    List<Contato> dadosContatos;
    ListView listagemContatos;

    SQLiteDatabase conexaoBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(MainActivity.this);
        conexaoBanco = dbHelper.getWritableDatabase();

        campoNome = findViewById(R.id.campoNome);
        campoTelefone = findViewById(R.id.campoTelefone);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        listagemContatos = findViewById(R.id.listagemContatos);

        dadosContatos = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.support.constraint.R.layout.support_simple_spinner_dropdown_item, dadosContatos);

        listagemContatos.setAdapter(adapter);
        acaoComponente();
    }

    private void acaoComponente() {
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contato contato = new Contato();

                contato.setNome(campoNome.getText().toString());
                contato.setTelefone(campoTelefone.getText().toString());

                dadosContatos.add(contato);

                Toast.makeText(MainActivity.this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}