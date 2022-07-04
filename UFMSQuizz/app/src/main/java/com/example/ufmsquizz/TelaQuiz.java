package com.example.ufmsquizz;

import static java.lang.Integer.MAX_VALUE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TelaQuiz extends AppCompatActivity {
    Button btnProximaQuestao;
    RadioGroup btnQuestao;
    RadioButton btnQuestao1,btnQuestao2,btnQuestao3,btnQuestao4;
    TextView txtQuestao;
    ImageView imgPergunta;
    int i=5;
    int j=1;
    int check;

    public static int getResultado() {
        return resultado;
    }

    static int resultado;
    int arrayCorreto [];
    int determinarResultado []={0,0,0,0};
    int quizIntAux = quizHomeActivity.GetQuizInt();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_quiz);
        btnProximaQuestao = (Button)findViewById(R.id.btnProximaQuestao);
        btnQuestao = (RadioGroup)findViewById(R.id.radioGroupQuestoes);
        btnQuestao1 =(RadioButton)findViewById(R.id.btnQuestao1);
        btnQuestao2 =(RadioButton)findViewById(R.id.btnQuestao2);
        btnQuestao3 =(RadioButton)findViewById(R.id.btnQuestao3);
        btnQuestao4 =(RadioButton)findViewById(R.id.btnQuestao4);
        imgPergunta =(ImageView)findViewById(R.id.imgPergunta);

        txtQuestao = (TextView)findViewById(R.id.txtPergunta);
        Resources res = getResources();


        int figurasCachorro [];
        figurasCachorro = new int[]{R.drawable.imagem_achorro1, R.drawable.imagem_cachorro2,
                R.drawable.imagem_cachorro3, R.drawable.imagem_cachorro4, R.drawable.imagem_cachorro5};
        int figurasMarvel [];
        figurasMarvel = new int[]{R.drawable.imagem_marvel1, R.drawable.imagem_marvel2,
                R.drawable.imagem_marvel3, R.drawable.imagem_marvel4, R.drawable.imagem_marvel5};


        String dados [];
        if (quizIntAux == 1) {
            dados = res.getStringArray(R.array.array_quiz1);
            imgPergunta.setImageResource(figurasCachorro[0]);
            arrayCorreto = figurasCachorro;

        } else {
            dados = res.getStringArray(R.array.array_quiz2);
            imgPergunta.setImageResource(figurasMarvel[0]);
            arrayCorreto = figurasMarvel;

        }

        txtQuestao.setText(dados[0]);
        btnQuestao1.setText(dados[1]);
        btnQuestao2.setText(dados[2]);
        btnQuestao3.setText(dados[3]);
        btnQuestao4.setText(dados[4]);

        btnProximaQuestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check = btnQuestao.getCheckedRadioButtonId();
                String checar = String.valueOf(btnQuestao.getCheckedRadioButtonId());
                calcularResultado(checar);
                if (check !=-1){
                    if(i>20) {
                        resultado = apresentarResultado();
                        Intent intent = new Intent(getApplicationContext(),TelaVitoriaActivity.class);
                        startActivity(intent);
                    }else {
                        alterarDados(dados);
                    }
                }else{
                    Toast.makeText(TelaQuiz.this,"Selecione uma opção!",Toast.LENGTH_SHORT).show();
                }

            }
        }

        );

    }

    public void alterarDados(String dados[]){
        btnQuestao.clearCheck();
        imgPergunta.setImageResource(arrayCorreto[j]);
        txtQuestao.setText(dados[i]);
        i += 1;
        btnQuestao1.setText(dados[i]);
        i += 1;
        btnQuestao2.setText(dados[i]);
        i += 1;
        btnQuestao3.setText(dados[i]);
        i += 1;
        btnQuestao4.setText(dados[i]);
        i += 1;
        j = j + 1;
        }

    public void calcularResultado(String checar){

            switch (checar) {
                case "2131296355":
                    determinarResultado[0] +=1;
                    break;
                case "2131296356":
                    determinarResultado[1] +=1;
                    break;
                case"2131296357":
                    determinarResultado[2] += 1;
                    break;
                case "2131296358":
                    determinarResultado[3] +=1;
                    break;

            }
        }


    public int apresentarResultado(){
        int maior=0;
        int posi = 0;
        for(int x =0;x<determinarResultado.length;x++){
            if (maior < determinarResultado[x]){
                    maior=determinarResultado[x];
                    posi = x;
            }
        }
        return posi;
    }


}