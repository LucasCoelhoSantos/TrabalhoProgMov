package com.example.ufmsquizz;

<<<<<<< Updated upstream
import androidx.appcompat.app.AppCompatActivity;

=======
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
>>>>>>> Stashed changes
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
<<<<<<< Updated upstream
=======
import android.widget.Switch;
import android.widget.TextView;
>>>>>>> Stashed changes
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class PerfilActivity extends AppCompatActivity {
    ImageView fotoPerfil;
    FloatingActionButton btnCamera;
<<<<<<< Updated upstream
    DBHelper myDB;

=======
    EditText edtEmailPerfil;
    DBHelper myDB;
    Button btnExcluirConta,btnSalvarMudancas;


    TextView txtResultQuiz2,txtResultQuiz1;

>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
<<<<<<< Updated upstream

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

=======

        myDB = new DBHelper(this);

        fotoPerfil = (ImageView)findViewById(R.id.fotoPerfil);
        btnCamera = (FloatingActionButton)findViewById(R.id.btnCamera);
        txtResultQuiz1 = (TextView)findViewById(R.id.txtResultQuizCachorro);
        txtResultQuiz2 = (TextView)findViewById(R.id.txtResultQuizVingadores);
        edtEmailPerfil = (EditText)findViewById(R.id.edtEmailPerfil);
        btnExcluirConta = (Button)findViewById(R.id.btnExcluirConta);
        btnSalvarMudancas = (Button)findViewById(R.id.btnSalvarPerfil);
        edtEmailPerfil.setText(MainActivity.getUserLogado());


        String emailUser = MainActivity.getUserLogado();
        Boolean teste2 = myDB.checarFoto(emailUser);
        if (teste2) {
            byte[] imageBytes = Base64.decode(myDB.retornarFotoPerfil(emailUser), Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            fotoPerfil.setImageBitmap(imagemDecodificada);
        }

>>>>>>> Stashed changes

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });
<<<<<<< Updated upstream
    }


=======

        btnExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder popUp = new AlertDialog.Builder(PerfilActivity.this);
                    popUp.setMessage("Você tem certeza que deseja excluir sua conta?");
                    popUp.setTitle("Atenção!");
                    popUp.setCancelable(false);
                    popUp.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String email = MainActivity.getUserLogado();
                            myDB.deleteData(email);
                            Toast.makeText(PerfilActivity.this,"Conta deletada com sucesso.",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    popUp.setNegativeButton("Não",null);
                    popUp.show();

            }
        });
        btnSalvarMudancas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = MainActivity.getUserLogado();
                myDB.updateData(email);
                Toast.makeText(PerfilActivity.this,"Conta salva com sucesso.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), quizHomeActivity.class);
                startActivity(intent);
            }
        });
    }


>>>>>>> Stashed changes
    protected void onActivityResult(int requestCode, int resultCode, Intent dados) {
        super.onActivityResult(requestCode, resultCode, dados);
        if (requestCode == 1) {
            try {
                Bitmap fotoRegistrada = (Bitmap) dados.getExtras().get("data");
                fotoPerfil.setImageBitmap(fotoRegistrada);
                byte[] fotoEmBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();
<<<<<<< Updated upstream
                fotoRegistrada.compress(Bitmap.CompressFormat.PNG, 70, streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();

                String emailUser = MainActivity.getUserLogado();
=======
                fotoRegistrada.compress(Bitmap.CompressFormat.PNG,70,streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();
                String fotoEmString = Base64.encodeToString(fotoEmBytes,Base64.DEFAULT);
>>>>>>> Stashed changes


            } catch (Exception e) {

            }
        }
    }
<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
}