package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TelaQuizActivity extends AppCompatActivity {
    Button btnProximaQuestao;
    RadioGroup btnQuestao;
    RadioButton btnQuestao1, btnQuestao2, btnQuestao3, btnQuestao4;
    TextView txtQuestao;
    int i = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);

        txtQuestao = (TextView)findViewById(R.id.txtPergunta);
        btnQuestao = (RadioGroup)findViewById(R.id.radioGroupQuestoes);
        btnQuestao1 = (RadioButton)findViewById(R.id.btnQuestao1);
        btnQuestao2 = (RadioButton)findViewById(R.id.btnQuestao2);
        btnQuestao3 = (RadioButton)findViewById(R.id.btnQuestao3);
        btnQuestao4 = (RadioButton)findViewById(R.id.btnQuestao4);
        btnProximaQuestao = (Button)findViewById(R.id.btnProximaQuestao);

        Resources res = getResources();
        int quizIntAux = QuizHomeActivity.GetQuizInt();

        String dados [];
        if (quizIntAux == 1) {
            dados = res.getStringArray(R.array.array_quiz1);
        } else {
            dados = res.getStringArray(R.array.array_quiz2);
        }

        txtQuestao.setText(dados[0]);
        btnQuestao1.setText(dados[1]);
        btnQuestao2.setText(dados[2]);
        btnQuestao3.setText(dados[3]);
        btnQuestao4.setText(dados[4]);

        btnProximaQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarDados(dados);
            }
        });
    }

    public int alterarDados(String dados[]){
        // Tem que colocar um and radio button is checked
        if (i == 20) {
            Intent intent = new Intent(getApplicationContext(), TelaVitoriaActivity.class);
            startActivity(intent);
        }
        txtQuestao.setText(dados[i]);
        i+=1;
        btnQuestao1.setText(dados[i]);
        i+=1;
        btnQuestao2.setText(dados[i]);
        i+=1;
        btnQuestao3.setText(dados[i]);
        i+=1;
        btnQuestao4.setText(dados[i]);
        i+=1;
        return i;
    }
}