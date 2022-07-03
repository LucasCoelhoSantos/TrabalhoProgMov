package com.example.ufmsquizz.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperUsuario extends SQLiteOpenHelper {
    // DATABASE INFORMATION
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "quizz.db";

    // TABLES USUARIO
    private static final String TABLE_NAME = "usuario";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_NOME = "nome";
    private static final String COLUMN_SENHA = "senha";
    private static final String COLUMN_FOTOPERFIL = "fotoPerfil";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_NOME + " TEXT NOT NULL, "
            + COLUMN_EMAIL + " TEXT NOT NULL PRIMARY KEY, "
            + COLUMN_SENHA + " TEXT NOT NULL, "
            + COLUMN_FOTOPERFIL + " TEXT);";

    public DBHelperUsuario(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void inserirUsuario(Usuario u){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, u.getNome());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_SENHA, u.getSenha());
        values.put(COLUMN_FOTOPERFIL, u.getFotoPerfil());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateUsuario(Usuario u) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, u.getNome());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_FOTOPERFIL, u.getFotoPerfil());
        String[] args = {String.valueOf(u.getEmail())};
        db.update(TABLE_NAME, values,COLUMN_EMAIL + "=?", args);
        db.close();
    }

    public long excluirUsuario(Usuario u) {
        long retornoBD;
        db = this.getWritableDatabase();
        String[] args = {String.valueOf(u.getEmail())};
        retornoBD = db.delete(TABLE_NAME, COLUMN_EMAIL + "=?", args);
        return retornoBD;
    }

    public Usuario selectOneUsuario(String email) {
        db = this.getReadableDatabase();
        String[] columns = {COLUMN_NOME, COLUMN_EMAIL, COLUMN_SENHA};

        String[] args = {String.valueOf(email)};
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, COLUMN_EMAIL + "=?", args, null, null, "upper(nome)", null);

        Usuario u = new Usuario();
        u.setNome(cursor.getString(0));
        u.setEmail(cursor.getString(1));
        u.setSenha(cursor.getString(2));
        return u;
    }

    public ArrayList<Usuario> selectAllUsuarios() {
        db = this.getReadableDatabase();
        String[] columns = {COLUMN_NOME, COLUMN_EMAIL, COLUMN_SENHA};

        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, null, null, null, null, "upper(nome)", null);
        ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        while(cursor.moveToNext()){
            Usuario u = new Usuario();
            u.setNome(cursor.getString(0));
            u.setEmail(cursor.getString(1));
            u.setSenha(cursor.getString(2));
            listaUsuario.add(u);
        }
        return listaUsuario;
    }
}