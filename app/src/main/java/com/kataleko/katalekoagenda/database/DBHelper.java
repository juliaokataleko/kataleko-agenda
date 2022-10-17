package com.kataleko.katalekoagenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kataleko.katalekoagenda.models.Contacto;

public class DBHelper extends SQLiteOpenHelper {

    private static String dbName = "agendaTelefonica.db";
    private static int versao = 1;

    String[] sql = {
            "CREATE TABLE contactos (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, phone1 TEXT, phone2 TEXT, email TEXT)"
            /*
            "INSERT INTO contactos(name, phone1, address) VALUES('Julião', '922660717', 'Bairro da Graça, Benguela')",
            "INSERT INTO contactos(name, phone1, address) VALUES('Isabel', '930891857', 'Bairro da Graça, Benguela')",
            "INSERT INTO contactos(name, phone1, address) VALUES('Floripesa', 'Sem número', 'Bairro da Graça, Benguela')",

             */
    };

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i = 0; i < sql.length; i++) {
            sqLiteDatabase.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        versao++;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS conctatos");
        onCreate(sqLiteDatabase);
    }

    // ================================ CRUD Contactos ================================
    public  long insertContacto(Contacto contacto) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", contacto.getName());
        cv.put("address", contacto.getAddress());
        cv.put("phone1", contacto.getPhone1());
        cv.put("phone2", contacto.getPhone2());
        cv.put("email", contacto.getEmail());

        return database.insert("contactos", null, cv);
    }

    public  long updateContacto(Contacto contacto) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("name", contacto.getName());
        cv.put("address", contacto.getAddress());
        cv.put("phone1", contacto.getPhone1());
        cv.put("phone2", contacto.getPhone2());
        cv.put("email", contacto.getEmail());

        return database.update("contactos", cv, "id=?", new String[]{String.valueOf(contacto.getId())});
    }

    public long deleteContacto(int id) {
        SQLiteDatabase database = getWritableDatabase();
        return database.delete("contactos", "id=?", new String[]{String.valueOf(id)});

    }

    public Cursor selectAllContactos() {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM contactos", null);
    }

    public Cursor selectByIdContactos(int id) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery("SELECT * FROM contactos WHERE id=?", new String[]{String.valueOf(id)});
    }

}
