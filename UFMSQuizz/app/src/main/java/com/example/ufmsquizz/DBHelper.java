package com.example.ufmsquizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class DBHelper extends SQLiteOpenHelper {
    // possível erro no context abaixo
    public DBHelper( Context context ) {
        super(context,"Login.db",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table users(email Text primary key, senha Text, fotoPerfil Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1)
    {
        myDB.execSQL("drop table if exists users");

    }

    public Boolean insertData(String email, String senha){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("senha",senha);

        long result = myDB.insert("users",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    // Provavelmente tá com bug

    public String salvarFoto( String fotoPerfil, String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fotoPerfil",fotoPerfil);
        String sql = "INSERT OR REPLACE INTO users (fotoPerfil) VALUES("+contentValues+") WHERE email ="+email;
        myDB.execSQL(sql);
        String fotoTeste = "SELECT fotoPerfil FROM users WHERE email ="+email;
        return  fotoTeste;
        //long result = myDB.insert("users",fotoPerfil,contentValues);
         //if(result==-1){


    }
    //Provavelmente tá com bug
    public Boolean checarFoto(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String fotoNoBd = String.valueOf(myDB.rawQuery("select fotoPerfil from users where email = ?",new String[]{email}));
        if(fotoNoBd.equals("")||fotoNoBd==null){
            return false;
        }
        else {
            return true;
        }

    }

    public String retornarFotoPerfil(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        String fotoNoBd = String.valueOf(myDB.rawQuery("select fotoPerfil from users where email = ?",new String[]{email}));

        return fotoNoBd;
    }


    public Boolean checkUser(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from users where email = ?",new String[]{email});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkUserPassword(String email, String senha){
        SQLiteDatabase myDB = this.getWritableDatabase();

            Cursor cursor2 = myDB.rawQuery("select * from users where email = ? and senha=?", new String[]{email,senha });
            if (cursor2.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }

}
