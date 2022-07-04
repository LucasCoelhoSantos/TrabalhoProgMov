package com.example.ufmsquizz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TelaVitoriaActivity extends AppCompatActivity {
    Button btnSalvarResultado,btnOutroQuiz;
    ImageView imgVitoria;
    TextView txtNomeFinal;
    TextView txtDescricao;
    int resultadoFinal = TelaQuiz.getResultado();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_vitoria);

        imgVitoria = (ImageView) findViewById(R.id.imgVitoria);
        txtNomeFinal = (TextView) findViewById(R.id.txtNomeFinal);
        txtDescricao = (TextView) findViewById(R.id.txtDescricaoFinal);
        btnOutroQuiz = (Button) findViewById(R.id.btnOutroQuiz);
        btnSalvarResultado = (Button) findViewById(R.id.btnSalvarResultado);

        int temaQuiz = quizHomeActivity.GetQuizInt();

        if(temaQuiz==1){
            switch (resultadoFinal){
                case 0:
                    txtNomeFinal.setText("Beagle");
                    imgVitoria.setImageResource(R.drawable.beagle);
                    txtDescricao.setText("Você é animado, energético, adora ficar perto de outras pessoas e é aquela pessoa alegre!");
                    break;
                case 1:
                    txtNomeFinal.setText("Pastor Alemão");
                    imgVitoria.setImageResource(R.drawable.pastor_alemao);
                    txtDescricao.setText("Você é uma pessoa que gosta de todo mundo e todos gostam de você também.");
                    break;
                case 2:
                    txtNomeFinal.setText("Poodle");
                    imgVitoria.setImageResource(R.drawable.poddle);
                    txtDescricao.setText("Você é fiel, dedicado, paciente, forte mas mesmo assim é brincalhão e companheiro.");
                    break;
                case 3:
                    txtNomeFinal.setText("Pinscher");
                    imgVitoria.setImageResource(R.drawable.pinscher);
                    txtDescricao.setText("Você perde a paciência com frequência e não gosta que os outros, que apesar do seu tamanho tem muita coragem.\n");
                    break;
            }
        }else{
            switch (resultadoFinal){
                case 0:
                    txtNomeFinal.setText("Hulk");
                    imgVitoria.setImageResource(R.drawable.hulk);
                    txtDescricao.setText("Assim como Hulk, você é uma pessoa extremamente inteligente," +
                            "sem muita paciência, bondosa e que age de acordo com as suas emoções.");
                    break;
                case 1:
                    txtNomeFinal.setText("Capitão América");
                    imgVitoria.setImageResource(R.drawable.capitao_america);
                    txtDescricao.setText("Você é um líder nato, está sempre a frente de tudo e tomando as rédeas das coisas." +
                            " Você é tão bom no que faz que geralmente tem uma legião de pessoas e amigos que te" +
                            " seguem por confiar muito em você. Assim como o Capitão, você é corajoso e está pronto para" +
                            " enfrentar qualquer desafio.");
                    break;
                case 2:
                    txtNomeFinal.setText("Homem de Ferro");
                    imgVitoria.setImageResource(R.drawable.homem_de_ferro);
                    txtDescricao.setText("Assim como Homem de Ferro você é um gênio, que não teme o futuro, porém pensa antes de agir.");
                    break;
                case 3:
                    txtNomeFinal.setText("Doutor Estranho");
                    imgVitoria.setImageResource(R.drawable.doutor_estranho);
                    txtDescricao.setText("Assim como o Doutor Estranho, você é uma pessoa original," +
                            "irreverente, inteligente e que prefere sempre usar a força da mente invés da força física.");
                    break;
            }

        }
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