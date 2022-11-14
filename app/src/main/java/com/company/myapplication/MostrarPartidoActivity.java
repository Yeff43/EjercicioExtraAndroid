package com.company.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.company.myapplication.models.Partido;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.company.myapplication.databinding.ActivityMostrarPartidoBinding;

public class MostrarPartidoActivity extends AppCompatActivity {

    private ActivityMostrarPartidoBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMostrarPartidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        intent = getIntent();

        Partido p = (Partido) intent.getExtras().getSerializable("PARTIDO");

        binding.txtLocalMostrarPartido.setText(p.getEquipoLocal());
        binding.txtVisitanteMostrarPartido.setText(p.getEquipoVisitante());
        binding.txtgolesLocalMostrarPartido.setText(String.valueOf(p.getGolesLocal()));
        binding.txtgolesVisitanteMostrarPartido.setText(String.valueOf(p.getGolesVisitante()));
        binding.txtResumenPartidoMostrarPartidoActivity.setText(p.getResumenPartido());
    }
}