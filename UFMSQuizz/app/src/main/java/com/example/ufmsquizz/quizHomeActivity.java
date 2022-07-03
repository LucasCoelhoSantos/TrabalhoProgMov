package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class quizHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);
<<<<<<< Updated upstream
=======

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
    }
}