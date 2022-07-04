package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;
import android.security.keystore.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    EditText email,senha;
    Button btnRegistrar,btnLogar;
    DBHelper myDB;



    public static String getUserLogado() {
        return userLogado;
    }

    static String userLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=(EditText)findViewById(R.id.edtEmail);
        senha=(EditText)findViewById(R.id.edtSenha);

        btnLogar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);

        myDB = new DBHelper(this);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailAux = email.getText().toString();
                String senhaAux = senha.getText().toString();

                if(emailAux.equals("") || senhaAux.equals("") ){
                    Toast.makeText(MainActivity.this,"Preencha todos os campos.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean validarCredenciais = myDB.checkUserPassword(emailAux,senhaAux);

                    if (validarCredenciais){
                        userLogado = emailAux;
                        Intent intent = new Intent(getApplicationContext(),quizHomeActivity.class);
                        startActivity(intent );
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Credenciais inválidas!",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent );
            }
        });
    }
}