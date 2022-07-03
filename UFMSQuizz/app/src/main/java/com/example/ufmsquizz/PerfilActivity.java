package com.example.ufmsquizz;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.ufmsquizz.db.Usuario;
import com.example.ufmsquizz.db.DBHelperUsuario;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class PerfilActivity extends AppCompatActivity {
    private ImageView image;
    private EditText edtNome, edtEmail;
    private Button btnSair, btnSalvar, btnExcluirConta;
    private FloatingActionButton btnCamera;
    private Usuario usuario;
    private String usuarioDel;

    private static final int PERMISSION_REQUEST_CODE = 200;
    static final int GALLERY = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    byte foto[];
    private static final String IMAGE_DIRECTORY = "/UFMSQuizz";

    private DBHelperUsuario helperUsuario = new DBHelperUsuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        image = findViewById(R.id.fotoPerfil);
        btnCamera = findViewById(R.id.btnCamera);
        btnSair = (Button)findViewById(R.id.btnSairConta);
        btnSalvar = (Button)findViewById(R.id.btnSalvarConta);
        btnExcluirConta = (Button)findViewById(R.id.btnExcluirConta);

        Usuario u = new Usuario();
        /*
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();

        u.setNome(nome);
        u.setEmail(email);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEmail = (TextView) findViewById(R.id.edtRegistroNome);
                int id = Integer.parseInt(edtEmail.getText().toString());
                u.setEmail(email);
                helperUsuario.updateUsuario(u);
                Snackbar.make(view, "Time atualizado!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperUsuario.excluirUsuario(u);
                Snackbar.make(view, "Time Cadastrado!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/

        /*
        //Tá com bug, acho que não tá retornando, o valor booleano está dizendo que sempre tem imagem.
        String emailUser = MainActivity.getEmailUser();
        Boolean teste2 = myDB.checarFoto(emailUser);
        if (teste2) {
            Toast.makeText(PerfilActivity.this,"Tem imagem",Toast.LENGTH_SHORT).show();
            byte[] imageBytes = Base64.decode(myDB.retornarFotoPerfil(emailUser), Base64.DEFAULT);
            Bitmap imagemDecodificada = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            image.setImageBitmap(imagemDecodificada);
        }
        else {
            Toast.makeText(PerfilActivity.this," Não Tem imagem",Toast.LENGTH_SHORT).show();

        }
         *
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermission()) {
            // Success
        } else {
            requestPermission();
        }
    }

    public void tirarFoto(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(resizeImage(bitmap, 300, 600));
            saveImage(bitmap);
            Toast.makeText(PerfilActivity.this, "Imagem Salva!", Toast.LENGTH_SHORT).show();
        }
        /*
        if (requestCode==1) {
            try {
                Bitmap fotoRegistrada = (Bitmap) dados.getExtras().get("data");
                image.setImageBitmap(fotoRegistrada);

                byte[] fotoEmBytes;
                ByteArrayOutputStream streamDaFotoEmBytes = new ByteArrayOutputStream();

                fotoRegistrada.compress(Bitmap.CompressFormat.PNG,70,streamDaFotoEmBytes);
                fotoEmBytes = streamDaFotoEmBytes.toByteArray();
                String fotoEmString = Base64.encodeToString(fotoEmBytes,Base64.DEFAULT);
                String emailUser = MainActivity.getEmailUser();
                // Precisa especificar o email do cara, para poder salvar na linha dele.Não sei onde tá salvando.
                /*
                Boolean teste = myDB.salvarFoto(fotoEmString);
                    if (teste){
                        Toast.makeText(PerfilActivity.this,"Deu bom",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PerfilActivity.this,"Deu ruim",Toast.LENGTH_SHORT).show();
                    }*

            } catch (Exception e) {
            }
       }*/
    }

    public String saveImage(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        foto = bytes.toByteArray();

        File directory = new File(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);

        // Criando o diretório caso ele não exista!
        if (!directory.exists()) {
            directory.mkdirs();
        }
        try {
            File fileImage = new File(directory, Calendar.getInstance().getTimeInMillis() +".jpg");
            fileImage.createNewFile();
            FileOutputStream fo = new FileOutputStream(fileImage);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this, new String[]{fileImage.getPath()}, new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved:->" + fileImage.getAbsolutePath());
            return fileImage.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public static Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {
        Matrix m = new Matrix();
        m.setScale((float) newWidth / bitmap.getWidth(), (float) newHeight / bitmap.getHeight());
        m.postRotate(0, 150, 300);
        Bitmap output = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    private void requestPermission() {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    // main logic
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            showMessageOKCancel("You need to allow access permissions", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermission();
                                    }
                                }
                            });
                        }
                    }
                }
            break;
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(PerfilActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void sairConta() {

    }

    public void salvarConta() {

    }

    public void excluirConta() {

    }
}