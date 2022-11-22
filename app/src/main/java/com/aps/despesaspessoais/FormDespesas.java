package com.aps.despesaspessoais;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class FormDespesas extends AppCompatActivity {

    private Button btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_despesas);

        getSupportActionBar().hide();
        IniciarComponentes();

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(FormDespesas.this, TelaPrincipal.class);
                startActivity((intent));
                finish();
            }
        });
    }

    private void IniciarComponentes(){
        btCancelar = findViewById(R.id.btCancelar);
    }
}