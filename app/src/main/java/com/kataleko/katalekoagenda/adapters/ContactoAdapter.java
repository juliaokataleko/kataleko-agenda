package com.kataleko.katalekoagenda.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kataleko.katalekoagenda.R;
import com.kataleko.katalekoagenda.activities.ShowContactoActivity;
import com.kataleko.katalekoagenda.models.Contacto;
import com.kataleko.katalekoagenda.viewholders.ContactoViewHolder;

import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoViewHolder> {

    private List<Contacto> listaDeContactos;
    View contactoView;

    public ContactoAdapter (List<Contacto> listaDeContactos) {
        this.listaDeContactos = listaDeContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        contactoView = inflater.inflate(R.layout.row_contacto_list,parent, false);
        return new ContactoViewHolder(contactoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = listaDeContactos.get(position);
        holder.bindData(contacto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contactoView.getContext(), ShowContactoActivity.class);
                intent.putExtra("id", contacto.getId());
                ((Activity)contactoView.getContext()).startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDeContactos.size();
    }
}
