package com.adalto.anotao;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ListaActivity extends AppCompatActivity {

    ListView lvLista;
    List<Anotacao> lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvLista = (ListView) findViewById(R.id.lvAnotacoes);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        ListaActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final Anotacao notaSelecionada = lista.get(position);
                AlertDialog.Builder alerta =
                        new AlertDialog.Builder(ListaActivity.this);
                alerta.setTitle("Excluir Anotação...");
                alerta.setMessage("Confirma a exclusão da anotação " +
                    notaSelecionada.getTitulo() + "?");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AnotacaoDAO.excluir(notaSelecionada.getId(),
                                ListaActivity.this);
                        carregarLista();

                   //     lista.remove( position);
                   //     adapter.notifyDataSetChanged();

                    }
                });
                alerta.setNeutralButton("Cancelar", null);
                alerta.show();



                return true;
            }
        });

    }

    private void carregarLista(){

        lista = AnotacaoDAO.listar(this);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, lista );
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
