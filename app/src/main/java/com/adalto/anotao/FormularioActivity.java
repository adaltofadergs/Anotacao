package com.adalto.anotao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    private EditText etTitulo, etTexto;
    private Button btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etTitulo = (EditText) findViewById(R.id.etTitulo);
        etTexto = (EditText) findViewById(R.id.etAnotacao);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar(){
        Anotacao nota = new Anotacao();
        nota.setTexto( etTexto.getText().toString() );
        nota.setTitulo( etTitulo.getText().toString() );
        AnotacaoDAO.inserir(nota, this);
        this.finish();
    }
}
