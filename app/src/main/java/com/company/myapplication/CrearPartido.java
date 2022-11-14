package com.company.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.company.myapplication.databinding.ActivityCrearPartidosBinding;
import com.company.myapplication.models.Partido;

public class CrearPartido extends AppCompatActivity {

    private ActivityCrearPartidosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrearPartidosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inicializarSpinners();

        binding.btnGuardarCrearPartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Partido p = crearPartido();
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("PARTIDO", p);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void inicializarSpinners() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.equipos_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerEquipoLocalCrearPartido.setAdapter(adapter);
        binding.spinnerEquipoVisitanteCrearPartido.setAdapter(adapter);
    }

    private Partido crearPartido() {
        if (binding.txtGolesLocalCrearPartido.getText().toString().isEmpty() ||
                binding.txtGolesVisitanteCrearPartido.getText().toString().isEmpty()
        ) return null;

        return new Partido(
                binding.spinnerEquipoLocalCrearPartido.getSelectedItem().toString(),
                Integer.parseInt(binding.txtGolesLocalCrearPartido.getText().toString()),
                binding.spinnerEquipoVisitanteCrearPartido.getSelectedItem().toString(),
                Integer.parseInt(binding.txtGolesVisitanteCrearPartido.getText().toString())
        );
    }
}