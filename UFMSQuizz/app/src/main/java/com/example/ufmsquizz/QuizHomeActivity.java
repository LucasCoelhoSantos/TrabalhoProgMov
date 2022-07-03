package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuizHomeActivity extends AppCompatActivity {
    ImageButton btnQuiz1, btnQuiz2;
    FloatingActionButton btnPerfil;

    protected static int quizInt;

    public static int GetQuizInt() {
        return quizInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

        btnQuiz1 = (ImageButton)findViewById(R.id.btnQuiz1);
        btnQuiz2 = (ImageButton)findViewById(R.id.btnQuiz2);
        btnPerfil = (FloatingActionButton)findViewById(R.id.btnPerfil);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
            }
        });

        btnQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizInt = 1;
                Intent intent = new Intent(getApplicationContext(), TelaQuizActivity.class);
                startActivity(intent);
            }
        });

        btnQuiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizInt = 2;
                Intent intent = new Intent(getApplicationContext(), TelaQuizActivity.class);
                startActivity(intent);
            }
        });
    }
}