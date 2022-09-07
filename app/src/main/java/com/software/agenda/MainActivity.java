package com.software.agenda;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.software.bancoDados.ContatoDB;
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

    DBHelper dbHelper;
    ContatoDB contatoDB;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(MainActivity.this);
        contatoDB = new ContatoDB(dbHelper);

        campoNome = findViewById(R.id.campoNome);
        campoTelefone = findViewById(R.id.campoTelefone);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        listagemContatos = findViewById(R.id.listagemContatos);

        dadosContatos = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.support.constraint.R.layout.support_simple_spinner_dropdown_item, dadosContatos);

        listagemContatos.setAdapter(adapter);
        contatoDB.listar(dadosContatos);
        acaoComponente();
    }

    private void acaoComponente() {
        listagemContatos.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                        new AlertDialog.Builder(view.getContext())
                                .setMessage("Deseja realmente remover")
                                .setPositiveButton("Confirmar",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface,
                                                                int j) {
                                                contatoDB.remover(dadosContatos.get(i).getId());
                                                contatoDB.listar(dadosContatos);
                                                adapter.notifyDataSetChanged();
                                            }
                                        })
                                .setNegativeButton("cancelar", null)
                                .create().show();
                        return false;
                    }
                }
        );

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contato contato = new Contato();

                contato.setNome(campoNome.getText().toString());
                contato.setTelefone(campoTelefone.getText().toString());

                contatoDB.inserirContato(contato);
                contatoDB.listar(dadosContatos);
                adapter.notifyDataSetChanged();

                Toast.makeText(MainActivity.this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}