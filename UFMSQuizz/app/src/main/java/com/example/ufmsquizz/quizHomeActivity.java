package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class quizHomeActivity extends AppCompatActivity {
    ImageView btnQuiz1,btnQuiz2;
    FloatingActionButton btnPerfil;

    protected static int quizInt;

    public static int GetQuizInt() {
        return quizInt;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);

<<<<<<< Updated upstream
        btnQuiz1 = (ImageButton)findViewById(R.id.btnQuiz1);
        btnQuiz2 = (ImageButton)findViewById(R.id.btnQuiz2);
=======

        btnQuiz1 = (ImageView) findViewById(R.id.btnQuiz1);
        btnQuiz2 = (ImageView) findViewById(R.id.btnQuiz2);
>>>>>>> Stashed changes
        btnPerfil = (FloatingActionButton)findViewById(R.id.btnPerfil);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PerfilActivity.class);
                startActivity(intent );
            }
        });
<<<<<<< Updated upstream
=======
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


>>>>>>> Stashed changes

    }
}