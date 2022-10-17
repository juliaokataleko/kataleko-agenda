package com.kataleko.katalekoagenda.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kataleko.katalekoagenda.ContactoMock;
import com.kataleko.katalekoagenda.R;
import com.kataleko.katalekoagenda.adapters.ContactoAdapter;
import com.kataleko.katalekoagenda.database.DBHelper;
import com.kataleko.katalekoagenda.models.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ListaDeContactosActivity extends AppCompatActivity {

    Button btnAddContacto;
    RecyclerView rvListaContactos;
    private DBHelper dbHelper;
    List<Contacto> listaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contactos);

        btnAddContacto = findViewById(R.id.btnAddContacto);
        rvListaContactos = findViewById(R.id.rvListaContactos);

        dbHelper = new DBHelper(this);

        btnAddContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaDeContactosActivity.this, NovoContactoActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // ContactoMock mock = new ContactoMock();
        listaContactos = new ArrayList<>();
        // listaContactos.addAll(mock.getContactoList());

        carregarContactos();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 1 && data != null) {
            // @TODO
            // novo registo criado, atualizar a lista
            carregarContactos();
        } else if(requestCode == 2 && resultCode == 1 && data != null) {
            // @TODO
            // registo eliminado
            carregarContactos();
        } else if(requestCode == 1 && resultCode == 0 && data != null) {
            // @TODO
            // novo registo cancelado
        }
    }

    public  void carregarContactos() {
        Cursor cursor = dbHelper.selectAllContactos();
        cursor.moveToFirst();

        listaContactos.clear();

        if(cursor.getCount() > 0) {

            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                @SuppressLint("Range") String phone1 = cursor.getString(cursor.getColumnIndex("phone1"));
                @SuppressLint("Range") String phone2 = cursor.getString(cursor.getColumnIndex("phone2"));
                @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));

                Contacto contacto = new Contacto(id, name, address, phone1, phone2, email);

                listaContactos.add(contacto);

            } while (cursor.moveToNext());

            ContactoAdapter contactoAdapter = new ContactoAdapter(listaContactos);
            rvListaContactos.setAdapter(contactoAdapter);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rvListaContactos.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarContactos();
    }
}