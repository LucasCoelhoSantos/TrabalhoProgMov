package com.example.ufmsquizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class DBHelper extends SQLiteOpenHelper {
    // possível erro no context abaixo
    public DBHelper( Context context ) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table users(email Text primary key, senha Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
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

            Cursor cursor = myDB.rawQuery("select * from users where email = ? and senha=?", new String[]{email,senha });
            if (cursor.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }

}
