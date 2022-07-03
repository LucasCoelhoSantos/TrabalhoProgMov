package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaVitoriaActivity extends AppCompatActivity {
    Button btnSalvarResultado,btnOutroQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_vitoria);

        btnOutroQuiz = (Button) findViewById(R.id.btnOutroQuiz);
        btnSalvarResultado = (Button) findViewById(R.id.btnSalvarResultado);
    
        btnOutroQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),quizHomeActivity.class);
                startActivity(intent );            }
        });

        btnSalvarResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}