package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class quizHomeActivity extends AppCompatActivity {
    ImageButton btnQuiz1,btnQuiz2;
    FloatingActionButton btnPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
=======
>>>>>>> c9291865de4f08f0cb5298347682666c88057547

        btnQuiz1 = (ImageButton)findViewById(R.id.btnQuiz1);
        btnQuiz2 = (ImageButton)findViewById(R.id.btnQuiz2);
        btnPerfil = (FloatingActionButton)findViewById(R.id.btnPerfil);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PerfilActivity.class);
                startActivity(intent );
            }
        });
<<<<<<< HEAD
        btnQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TelaQuiz.class);
                startActivity(intent);
            }
        });
        btnQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TelaQuiz.class);
                startActivity(intent);
            }
        });


>>>>>>> Stashed changes
=======

>>>>>>> c9291865de4f08f0cb5298347682666c88057547
    }
}