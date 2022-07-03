package com.example.ufmsquizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quizz.db";
    private static final String TABLE_NAME = "usuario";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_SENHA = "senha";
    private static final String COLUMN_FOTOPERFIL = "fotoPerfil";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE usuario" +
            "(nome text not null, email text primary key, senha text not null, fotoPerfil text);";

    // Possível erro no context abaixo
    public DBHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table users(email text primary key, nome text not null, senha text not null, fotoPerfil text)");
        /*
        db.execSQL(TABLE_CREATE);
        this.db = db;
         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("drop table if exists users");
        /*
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
        */
    }

    public Boolean insertData(String nome, String email, String senha) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("senha", senha);

        long result = myDB.insert("users", null, contentValues);
        if (result == 1) {
            return false;
        }
        else {
            return true;
        }
    }
    /*
    public void inserirUsuario(Usuario u) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_NOME, u.getNome());
        values.put(COLUMN_SENHA, u.getSenha());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    */
    // Provavelmente tá com bug
    public String salvarFoto(String fotoPerfil, String email) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fotoPerfil", fotoPerfil);

        String sql = "INSERT OR REPLACE INTO users (fotoPerfil) VALUES("+contentValues+") WHERE email ="+email;
        myDB.execSQL(sql);
        String fotoTeste = "SELECT fotoPerfil FROM users WHERE email ="+email;
        return  fotoTeste;

        /*
        long result = myDB.insert("users", fotoPerfil, contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
        */
    }
    //Provavelmente tá com bug
    public Boolean checarFoto(String email) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String fotoNoBd = String.valueOf(myDB.rawQuery("select fotoPerfil from users where email = ?",new String[]{email}));
        if(fotoNoBd.equals("")||fotoNoBd==null) {
            return false;
        }
        else {
            return true;
        }

    }

    public String retornarFotoPerfil(String email) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        String fotoNoBd = String.valueOf(myDB.rawQuery("select fotoPerfil from users where email = ?", new String[]{email}));
        return fotoNoBd;
    }

    public Boolean checkUser(String email) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUserPassword(String email, String senha) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        Cursor cursor = myDB.rawQuery("select * from users where email = ? and senha = ?", new String[]{email, senha});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}