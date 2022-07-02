package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class RegisterActivity extends AppCompatActivity {

    EditText emailReg, senhaReg, confirmarSenhaReg;
    Button btnConfirmarReg;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_register);

        emailReg=(EditText)findViewById(R.id.edtRegistroEmail);
        senhaReg=(EditText)findViewById(R.id.edtRegistroSenha);
        confirmarSenhaReg=(EditText)findViewById(R.id.edtConfirmarSenha);

        btnConfirmarReg=(Button)findViewById(R.id.btnRegistroConfirmar);

        myDB = new DBHelper(this);


        btnConfirmarReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {


                //try {
                    //MessageDigest md = MessageDigest.getInstance("SHA-256");

                String email = emailReg.getText().toString();
                String senha = senhaReg.getText().toString();
                String senhaConfirmada = confirmarSenhaReg.getText().toString();

                if(email.equals("") || senha.equals("") || senhaConfirmada.equals("")){
                    Toast.makeText(RegisterActivity.this,"Preencha todos os campos.",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(senha.equals(senhaConfirmada)){
                        Boolean validarExistencia = myDB.checkUser(email);

                        if (validarExistencia == false){
                            Boolean Sucesso = myDB.insertData(email,senha);

                            if (Sucesso == true){
                                Toast.makeText(RegisterActivity.this,"Registrado com sucesso!.",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"Registro falhou!.",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this,"Usuário já existe \n Tente logar.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent );
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Senhas não coencidem.",Toast.LENGTH_SHORT).show();
                    }
                }//} catch (NoSuchAlgorithmException e) {
                   // e.printStackTrace();
                //}

            }
        });

    }
}