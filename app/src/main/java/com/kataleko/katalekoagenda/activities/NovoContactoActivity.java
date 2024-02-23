package com.kataleko.katalekoagenda.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kataleko.katalekoagenda.R;
import com.kataleko.katalekoagenda.database.DBHelper;
import com.kataleko.katalekoagenda.models.Contacto;

public class NovoContactoActivity extends AppCompatActivity {

    private ViewHolder viewHolder = new ViewHolder();
    DBHelper dbHelper;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contacto);

        // back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbHelper = new DBHelper(this);
        i = getIntent();

        viewHolder.etName = findViewById(R.id.etName);
        viewHolder.etEmail = findViewById(R.id.etEmail);
        viewHolder.etAddress = findViewById(R.id.etAddress);
        viewHolder.etPhone1 = findViewById(R.id.etPhone1);
        viewHolder.etPhone2 = findViewById(R.id.etPhone2);

        viewHolder.btnSalvar = findViewById(R.id.btnSalvar);
        viewHolder.btnCancelar = findViewById(R.id.btnCancelar);

        viewHolder.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NovoContactoActivity.this, "Novo contacto cancelado", Toast.LENGTH_SHORT).show();
                setResult(0, i);
                finish();
            }
        });

        viewHolder.btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = viewHolder.etName.getText().toString().trim();
                String address = viewHolder.etAddress.getText().toString().trim();
                String email = viewHolder.etEmail.getText().toString().trim();
                String phone1 = viewHolder.etPhone1.getText().toString().trim();
                String phone2 = viewHolder.etPhone2.getText().toString().trim();
                Contacto contacto = new Contacto(name, address, phone1, phone2, email);

                if(!name.isEmpty() && !phone1.isEmpty()) {
                    long res = dbHelper.insertContacto(contacto);

                    if(res > 0) {
                        Toast.makeText(NovoContactoActivity.this, "Novo contacto salvo", Toast.LENGTH_SHORT).show();
                        setResult(1, i);
                        finish();
                    } else {
                        Toast.makeText(NovoContactoActivity.this, "Ocorreu uma falha...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(NovoContactoActivity.this, "Preencha pelo menos o nome e o telefone", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private class ViewHolder {
        EditText etName, etEmail, etPhone1, etPhone2, etAddress;
        Button btnSalvar, btnCancelar;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle back button press
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}