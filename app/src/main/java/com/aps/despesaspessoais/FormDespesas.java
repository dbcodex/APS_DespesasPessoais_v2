package com.aps.despesaspessoais;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Date;

import kotlinx.coroutines.Delay;

public class FormDespesas extends AppCompatActivity {

    private Button btCancelar, bt_cadastrar_despesas;

    String[] mensagens = {"Preencha todos os campos","Cadastro realizado com sucesso"};

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_despesas);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_cadastrar_despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText descricaoDespesa = findViewById(R.id.edit_descricao);
                EditText valorDespesa = findViewById(R.id.edit_valor);
                EditText dtVencimento = findViewById(R.id.edit_vencimento);

                String descricao = descricaoDespesa.getText().toString();
                String valor = valorDespesa.getText().toString();
                String vencimento = dtVencimento.getText().toString();

                if (descricao.isEmpty() || valor.isEmpty() || vencimento.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    cadastrar(view);

                    Intent intent = new  Intent(FormDespesas.this, TelaPrincipal.class);
                    startActivity((intent));
                    finish();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new  Intent(FormDespesas.this, TelaPrincipal.class);
                startActivity((intent));
                finish();
            }
        });
    }

    public void cadastrar(View view){
        EditText descricaoDespesa = findViewById(R.id.edit_descricao);
        EditText valorDespesa = findViewById(R.id.edit_valor);
        EditText dtVencimento = findViewById(R.id.edit_vencimento);

        Despesas despesas = new Despesas();
        despesas.chaveFirebase = FirebaseAuth.getInstance().getCurrentUser().getUid();;
        despesas.descricaoDespesa = descricaoDespesa.getText().toString();
        despesas.valorDespesa = valorDespesa.getText().toString();;
        despesas.dtVencimento = dtVencimento.getText().toString();;

        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DespesasDB db = Room.databaseBuilder(getApplicationContext(), DespesasDB.class, "Despesas").build();
                    DespesasDAO dao = db.despesasDAO();

                    dao.insert(despesas);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FormDespesas.this);
                            builder.setMessage(mensagens[1]);
                            builder.create().show();
                        }
                    });
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void IniciarComponentes(){
        btCancelar = findViewById(R.id.btCancelar);
        bt_cadastrar_despesas = findViewById(R.id.bt_cadastrar_despesas);
    }
}