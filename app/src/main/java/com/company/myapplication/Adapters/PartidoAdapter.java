package com.company.myapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.myapplication.MainActivity;
import com.company.myapplication.MostrarPartidoActivity;
import com.company.myapplication.R;
import com.company.myapplication.models.Partido;

import java.util.List;

public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidoVH> {

    private List<Partido> objects;
    private int fila;
    private Context context;

    public PartidoAdapter(List<Partido> objects, int fila, Context context) {
        this.objects = objects;
        this.fila = fila;
        this.context = context;
    }

    @NonNull
    @Override
    public PartidoAdapter.PartidoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View partidoView = LayoutInflater.from(context).inflate(fila,null);
        partidoView.setLayoutParams(
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        return new PartidoVH(partidoView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoAdapter.PartidoVH holder, int position) {
        Partido partido = objects.get(position);
        holder.equipoLocal.setText(partido.getEquipoLocal());
        holder.golesLocal.setText(String.valueOf(partido.getGolesLocal()));
        holder.equipoVisitante.setText(partido.getEquipoVisitante());
        holder.golesVisitante.setText(String.valueOf(partido.getGolesVisitante()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MostrarPartidoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDO",partido);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class PartidoVH extends RecyclerView.ViewHolder {
        TextView equipoLocal;
        TextView equipoVisitante;
        TextView golesLocal;
        TextView golesVisitante;
        ImageButton btnBorrarPartido;

        public PartidoVH(@NonNull View itemView) {
            super(itemView);
            equipoLocal = itemView.findViewById(R.id.lblEquipoLocalViewHolder);
            equipoVisitante = itemView.findViewById(R.id.lblEquipoVisitanteViewHolder);
            golesLocal = itemView.findViewById(R.id.lblGolesLocalViewHolder);
            golesVisitante = itemView.findViewById(R.id.lblGolesVisitanteViewHolder);
            btnBorrarPartido = itemView.findViewById(R.id.btnDeletePartidoViewHolder);
        }
    }
}
