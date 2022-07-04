package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class PerfilActivity extends AppCompatActivity {
    ImageView fotoPerfil;
    FloatingActionButton btnCamera;
    DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        myDB = new DBHelper(this);

        fotoPerfil = findViewById(R.id.fotoPerfil);
        btnCamera = findViewById(R.id.btnCamera);


        /*
        Tá com bug, acho que não tá retornando, o valor booleano está dizendo que sempre tem imagem.
        */
        String emailUser = MainActivity.getUserLogado();
        Boolean teste2 = myDB.checarFoto(emailUser);
        if (teste2) {
            Toast.makeText(PerfilActivity.this, "Tem imagem", Toast.LENGTH_SHORT).show();

            byte[] imageBytes = Base64.decode(myDB.retornarFotoPerfil(emailUser), Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            fotoPerfil.setImageBitmap(imagemDecodificada);
        } else {
            Toast.makeText(PerfilActivity.this, " Não Tem imagem", Toast.LENGTH_SHORT).show();

        }


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent dados) {
        super.onActivityResult(requestCode, resultCode, dados);
        if (requestCode == 1) {
            try {
                Bitmap fotoRegistrada = (Bitmap) dados.getExtras().get("data");
                fotoPerfil.setImageBitmap(fotoRegistrada);
                byte[] fotoEmBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();
                fotoRegistrada.compress(Bitmap.CompressFormat.PNG, 70, streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();

                String emailUser = MainActivity.getUserLogado();


            } catch (Exception e) {

            }
        }
    }
}