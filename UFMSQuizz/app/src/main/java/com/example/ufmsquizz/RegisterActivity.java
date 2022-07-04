package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class RegisterActivity extends AppCompatActivity {

    EditText emailReg, senhaReg, confirmarSenhaReg;
    Button btnConfirmarReg;
    ImageView fotoPerfil2;
    FloatingActionButton btnCamera2;
    DBHelper myDB;
    String fotoPerfil = "";
    static String fotoLogada;

    public static String getFotoLogada() {
        return fotoLogada;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_register);

        emailReg=(EditText)findViewById(R.id.edtRegistroEmail);
        senhaReg=(EditText)findViewById(R.id.edtRegistroSenha);
        confirmarSenhaReg=(EditText)findViewById(R.id.edtConfirmarSenha);
        fotoPerfil2=(ImageView)findViewById(R.id.fotoPerfil2);
        btnCamera2=(FloatingActionButton)findViewById(R.id.btnCamera2);
        btnConfirmarReg=(Button)findViewById(R.id.btnRegistroConfirmar);

        myDB = new DBHelper(this);


        btnConfirmarReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {



                   // MessageDigest md = MessageDigest.getInstance("SHA-256");

                String email = emailReg.getText().toString();
                String senha = senhaReg.getText().toString();
                String senhaConfirmada = confirmarSenhaReg.getText().toString();

                if(email.equals("") || senha.equals("") || senhaConfirmada.equals("")|| fotoPerfil.equals("")){
                    Toast.makeText(RegisterActivity.this,"Preencha todos os campos.",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(senha.equals(senhaConfirmada)){
                        Boolean validarExistencia = myDB.checkUser(email);

                        if (!validarExistencia){
                            Boolean Sucesso = myDB.insertData(email,senha,fotoPerfil);
                            fotoLogada=fotoPerfil;

                            if (Sucesso){
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


                }

            }

        });

        btnCamera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });
    }   protected void onActivityResult(int requestCode, int resultCode, Intent dados){
        super.onActivityResult(requestCode,resultCode,dados);
        if (requestCode==1){
            try {
                Bitmap fotoRegistrada = (Bitmap) dados.getExtras().get("data");
                fotoPerfil2.setImageBitmap(fotoRegistrada);
                byte[] fotoEmBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();
                fotoRegistrada.compress(Bitmap.CompressFormat.PNG,70,streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();
                String fotoEmString = Base64.encodeToString(fotoEmBytes,Base64.DEFAULT);
                fotoPerfil=fotoEmString;
            }catch (Exception e){
            }
        }
    }

    }
