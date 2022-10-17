package com.kataleko.katalekoagenda.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kataleko.katalekoagenda.R;
import com.kataleko.katalekoagenda.database.DBHelper;
import com.kataleko.katalekoagenda.models.Contacto;

public class ShowContactoActivity extends AppCompatActivity {

    Intent intent;
    private ViewHolder viewHolder = new ViewHolder();
    DBHelper dbHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacto);

        intent = getIntent();
        id = intent.getExtras().getInt("id");
        dbHelper = new DBHelper(this);

        viewHolder.etName = findViewById(R.id.etEditName);
        viewHolder.etEmail = findViewById(R.id.etEditEmail);
        viewHolder.etAddress = findViewById(R.id.etEditAddress);
        viewHolder.etPhone1 = findViewById(R.id.etEditPhone1);
        viewHolder.etPhone2 = findViewById(R.id.etEditPhone2);

        viewHolder.btnSave = findViewById(R.id.btnShowContactoSalvar);
        viewHolder.btnDelete = findViewById(R.id.btnShowContactoDelete);
        viewHolder.btnEnableEdition = findViewById(R.id.btnShowContactoEditarEliminar);
        viewHolder.btnGoBack = findViewById(R.id.btnShowContactoVoltar);
        viewHolder.linearLayoutButtons = findViewById(R.id.llBotoes);
        viewHolder.btnEmail = findViewById(R.id.btnEmail);
        viewHolder.btnLigar = findViewById(R.id.btnLigar);

        carregarDadosDoContacto(id);

        viewHolder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // salvar dados no banco

                String name = viewHolder.etName.getText().toString().trim();
                String address = viewHolder.etAddress.getText().toString().trim();
                String email = viewHolder.etEmail.getText().toString().trim();
                String phone1 = viewHolder.etPhone1.getText().toString().trim();
                String phone2 = viewHolder.etPhone2.getText().toString().trim();
                Contacto contacto = new Contacto(id, name, address, phone1, phone2, email);

                long res = dbHelper.updateContacto(contacto);

                if(res > 0) {
                    Toast.makeText(ShowContactoActivity.this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
                    setResult(1, intent);
                    finish();
                } else {
                    Toast.makeText(ShowContactoActivity.this, "Ocorreu uma falha.", Toast.LENGTH_SHORT).show();
                }

                disableEdicao();
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // eliminar elemento do banco

                long res = dbHelper.deleteContacto(id);

                if(res > 0) {
                    Toast.makeText(ShowContactoActivity.this, "Contacto eliminado com sucesso.", Toast.LENGTH_SHORT).show();
                    setResult(1, intent);
                    finish();
                } else {
                    Toast.makeText(ShowContactoActivity.this, "Ocorreu uma falha.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        viewHolder.btnEnableEdition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableEdicao();
            }
        });

        viewHolder.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarDadosDoContacto(id);
            }
        });

        viewHolder.btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = viewHolder.etPhone1.getText().toString().trim();

                if(!phone.isEmpty()) {
                    Intent myIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                    startActivity(myIntent);
                } else {
                    Toast.makeText(ShowContactoActivity.this, "O número está vazio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewHolder.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = viewHolder.etEmail.getText().toString().trim();
                if(!email.isEmpty()) {
                    Intent myIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+email));
                    myIntent.putExtra(Intent.EXTRA_EMAIL, email);
                    myIntent.putExtra(Intent.EXTRA_SUBJECT, "Enviar email");
                    myIntent.putExtra(Intent.EXTRA_TEXT, "Enviando apartir da agenda");
                    startActivity(Intent.createChooser(myIntent, "Chooser"));
                }
            }
        });
    }

    private void carregarDadosDoContacto(int id) {
        // buscar o contacto no banco
        Cursor cursor = dbHelper.selectByIdContactos(id);
        cursor.moveToFirst();
        if(cursor.getCount() == 1) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String address = cursor.getString(cursor.getColumnIndex("address"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String phone1 = cursor.getString(cursor.getColumnIndex("phone1"));
            @SuppressLint("Range") String phone2 = cursor.getString(cursor.getColumnIndex("phone2"));

            viewHolder.etName.setText(name);
            viewHolder.etEmail.setText(address);
            viewHolder.etEmail.setText(email);
            viewHolder.etPhone1.setText(phone1);
            viewHolder.etPhone2.setText(phone2);

            disableEdicao();
        } else {
            Toast.makeText(this, "Não foi possível carregar os dados do contato", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class ViewHolder {
        EditText etName, etPhone1, etEmail, etAddress, etPhone2;
        Button btnSave, btnDelete, btnEnableEdition, btnGoBack;
        Button btnLigar, btnEmail;
        LinearLayout linearLayoutButtons;
    }

    private void enableEdicao() {
        viewHolder.etName.setEnabled(true);
        viewHolder.etEmail.setEnabled(true);
        viewHolder.etPhone1.setEnabled(true);
        viewHolder.etPhone2.setEnabled(true);
        viewHolder.etAddress.setEnabled(true);

        viewHolder.linearLayoutButtons.setVisibility(View.VISIBLE);
        viewHolder.btnEnableEdition.setVisibility(View.GONE);
    }

    private void disableEdicao() {
        viewHolder.etName.setEnabled(false);
        viewHolder.etEmail.setEnabled(false);
        viewHolder.etPhone1.setEnabled(false);
        viewHolder.etPhone2.setEnabled(false);
        viewHolder.etAddress.setEnabled(false);

        viewHolder.linearLayoutButtons.setVisibility(View.GONE);
        viewHolder.btnEnableEdition.setVisibility(View.VISIBLE);
    }
}