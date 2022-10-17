package com.kataleko.katalekoagenda.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kataleko.katalekoagenda.R;
import com.kataleko.katalekoagenda.models.Contacto;

import org.jetbrains.annotations.NotNull;

public class ContactoViewHolder extends RecyclerView.ViewHolder {

    private TextView tvNome, tvPhone;
    public ContactoViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNome = itemView.findViewById(R.id.rvTvNomeContacto);
        tvPhone = itemView.findViewById(R.id.rvTvPhoneContacto);
    }

    public void bindData(Contacto contacto) {
        tvNome.setText(contacto.getName());
        tvPhone.setText(contacto.getPhone1());
    }
}
