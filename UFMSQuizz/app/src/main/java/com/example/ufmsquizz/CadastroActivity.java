package com.example.ufmsquizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ufmsquizz.db.Usuario;

public class CadastroActivity extends AppCompatActivity {
    private EditText edtNome, edtEmail, edtSenha, edtConfirmarSenha;
    private Button btnRegistrar;
    private Usuario usuario, altUsuario;
    View fragmentUsuario;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cadastro_usuario);

        edtNome = (EditText)findViewById(R.id.edtRegistroNome);
        edtEmail = (EditText)findViewById(R.id.edtRegistroEmail);
        edtSenha = (EditText)findViewById(R.id.edtRegistroSenha);
        edtConfirmarSenha = (EditText)findViewById(R.id.edtConfirmarSenha);

        btnRegistrar = (Button)findViewById(R.id.btnRegistro);

        myDB = new DBHelper(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                */

                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                String senhaConfirmada = edtConfirmarSenha.getText().toString();

                if (nome.equals("") || email.equals("") || senha.equals("") || senhaConfirmada.equals("")) {
                    Toast.makeText(CadastroActivity.this,"Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (senha.equals(senhaConfirmada)) {
                        Boolean validarExistencia = myDB.checkUser(email);

                        if (validarExistencia == false) {
                            Boolean Sucesso = myDB.insertData(nome, email, senha);

                            if (Sucesso == true) {
                                Toast.makeText(CadastroActivity.this,"Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(CadastroActivity.this,"Registro falhou!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(CadastroActivity.this,"Usuário já existe\nTente logar.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(CadastroActivity.this,"Senhas não coencidem.", Toast.LENGTH_SHORT).show();
                    }
                }//} catch (NoSuchAlgorithmException e) {
                   // e.printStackTrace();
                //}
            }
        });
    }
}