package com.example.ufmsquizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DBHelper extends SQLiteOpenHelper {
    // possível erro no context abaixo
    public DBHelper( Context context ) {
        super(context,"Login.db",null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("create table users(email Text primary key, senha Text, fotoPerfil Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1)
    {
        myDB.execSQL("drop table if exists users");
<<<<<<< Updated upstream

    }

    public Boolean insertData(String email, String senha, String fotoPerfil){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("senha",criptografia(senha));
        contentValues.put("fotoPerfil",fotoPerfil);

        long result = myDB.insert("users",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
=======

    }

    public Boolean insertData(String email, String senha, String fotoPerfil){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("senha",criptografia(senha));
        contentValues.put("fotoPerfil",fotoPerfil);

        long result = myDB.insert("users",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public long deleteData(String email) {
        long returnDB;
        SQLiteDatabase myDB = this.getWritableDatabase();
        String[] args = {String.valueOf(email)};
        returnDB = myDB.delete("users", "email =?", args);
        return returnDB;
    }

    public long updateData(String email) {
        long returnDB;
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        //values.put("fotoPerfil", fotoPerfil);
        String[] args = {String.valueOf(email)};
        returnDB = myDB.update("users", values, "email =?", args);
        myDB.close();
        return returnDB;
>>>>>>> Stashed changes
    }

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

    public String retornarFotoPerfil(String email) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        String fotoNoBd="";
        if(cursor.moveToFirst()){
            do{
                fotoNoBd = (cursor.getString(2));

            }while(cursor.moveToNext());

        }
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
            senha = criptografia(senha);
            Cursor cursor2 = myDB.rawQuery("select * from users where email = ? and senha=?", new String[]{email,senha });
            if (cursor2.getCount() > 0) {
                return true;
            } else {
                return false;
            }
        }
    public String criptografia(String senha) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Add password bytes to digest
            md.update(senha.getBytes());

            // Get the hash's bytes
            byte[] bytes = md.digest();

            // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
