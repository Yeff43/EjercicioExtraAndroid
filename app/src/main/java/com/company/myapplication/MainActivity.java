package com.company.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.company.myapplication.Adapters.PartidoAdapter;
import com.company.myapplication.databinding.ActivityMainBinding;
import com.company.myapplication.models.Partido;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.constraintlayout.helper.widget.MotionEffect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Partido> listaPartidos;
    private ActivityResultLauncher<Intent> launcherPartidos;
    public  ActivityResultLauncher<Intent> launcherMostrarPartido;
    private PartidoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         La RFEF nos ha pedido una aplicación para poder insertar los resultados de los partidos de futbol.

         La aplicación será una Basic Activity, Se necesitará una segunda actividad para poder crear los partidos y
         su resultado, los equipos se elegirán desde un spinner donde el user seleccionará el nombre del equipo y un
         EditText para cada resultado. y 1 EditText para poder poner un resumen del partido

         Cada card mostrará los 2 equipos y sus goles NO EL RESUMEN, tendrá un botón que nos mostrará un AlertDialog
         indicando que equipo ha sido el que ha ganado.

         Si presionamos en la fila, se abrirá una tercera activity que nos mostrará toda
         la información del partido, equipos, goles y resumen
         **/

        // 1. Crear la basic activity y preparar el proyecto YA ESTA
        // 2. Creamos la actividad crearPartido con sus spinner y el campo de resultado para cada equipo y el TextView Resumen
        // 3. Creamos La clase Partido
        // 4. Creamos en el main el Arraylist de partidos y el Launcher para abrir la actividad CrearPartido y recibir el partido
        // 5. Creamos el Adapter y programamos lo basico para evitar errores
        // 6. Instanciamos el adapter en el MainActivity
        // 7.

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        inicializarLauncher();

        listaPartidos = new ArrayList<>();
        adapter = new PartidoAdapter(listaPartidos, R.layout.partido_view_holder, this);
        layoutManager = new GridLayoutManager(this, 1);

        binding.contentMain.contenedor.setLayoutManager(layoutManager);
        binding.contentMain.contenedor.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherPartidos.launch(new Intent(MainActivity.this, CrearPartido.class));
            }
        });
    }

    private void inicializarLauncher() {
        launcherPartidos = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                Bundle bundle = result.getData().getExtras();
                                Partido p = (Partido) bundle.getSerializable("PARTIDO");
                                Log.d(MotionEffect.TAG, "P: " + p.toString());
                                listaPartidos.add(0, p);
                                adapter.notifyItemInserted(0);
                                //Aqui va lo de actualizar el adapter
                                Toast.makeText(MainActivity.this, "Partido Creado Exitosamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        launcherMostrarPartido = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if (result.getData() != null){
                                Bundle bundle = result.getData().getExtras();
                                Partido p = (Partido) bundle.getSerializable("PARTIDO");
                                adapter.notifyItemChanged(listaPartidos.indexOf(p));
                            }
                        }
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("LISTA", listaPartidos);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listaPartidos.addAll((ArrayList<Partido>) savedInstanceState.getSerializable("LISTA"));
        adapter.notifyItemRangeInserted(0, listaPartidos.size());
    }
}