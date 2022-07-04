package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class quizHomeActivity extends AppCompatActivity {
    ImageView btnQuiz1,btnQuiz2,imgTeste;
    CircleImageView btnPerfil;
    DBHelper myDB;

    protected static int quizInt;

    public static int GetQuizInt() {
        return quizInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);
        myDB = new DBHelper(this);

        byte[] fotoEmBytes;
        String userLogado = MainActivity.getUserLogado();


        btnQuiz1 = (ImageButton)findViewById(R.id.btnQuiz1);
        btnQuiz2 = (ImageButton)findViewById(R.id.btnQuiz2);
        btnPerfil = (FloatingActionButton)findViewById(R.id.btnPerfil);


        String fotoEmString = myDB.retornarFotoPerfil(userLogado);

        Toast.makeText(quizHomeActivity.this, fotoEmString, Toast.LENGTH_SHORT).show();
        fotoEmBytes = Base64.decode(fotoEmString,Base64.DEFAULT);
        Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(fotoEmBytes,0,fotoEmBytes.length);
        btnQuiz1 = (ImageView)findViewById(R.id.btnQuiz1);
        btnQuiz2 = (ImageView)findViewById(R.id.btnQuiz2);
        btnPerfil = (CircleImageView) findViewById(R.id.btnPerfil);
        btnPerfil.setImageBitmap(imagemDecodificada);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PerfilActivity.class);
                startActivity(intent );
            }
        });

        btnQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizInt=1;
                Intent intent = new Intent(getApplicationContext(),TelaQuiz.class);
                startActivity(intent);
            }
        });
        btnQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizInt=2;
                Intent intent = new Intent(getApplicationContext(),TelaQuiz.class);
                startActivity(intent);
            }
        });

    }
}