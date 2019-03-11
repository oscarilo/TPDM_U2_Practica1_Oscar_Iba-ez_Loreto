package mx.edu.ittepuc.tpdm_u2_practica1_oscar_ibanez_loreto_proyectos_civiles;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    Button btnConsultarEspecifico, cerrar;
    TextView descripcion, ubicacion, fecha, presupuesto;
    EditText campoConsultar;
    Proyectos proyectos;
    Proyectos vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        campoConsultar = findViewById(R.id.campoConsultar);

        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);

        btnConsultarEspecifico = findViewById(R.id.btnConsultarEspecifico);
        cerrar = findViewById(R.id.cerrar);

        btnConsultarEspecifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodoConsultarEspecifico();
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        descripcion.setVisibility(View.INVISIBLE);
        ubicacion.setVisibility(View.INVISIBLE);
        fecha.setVisibility(View.INVISIBLE);
        presupuesto.setVisibility(View.INVISIBLE);
    }

    private void metodoConsultarEspecifico() {

        if (!campoConsultar.getText().toString().trim().isEmpty()) {
            Proyectos proyectos = new Proyectos(this);
            try {
                int valor = Integer.parseInt(campoConsultar.getText().toString());
                vector = proyectos.consultarEspecifico("NO", "" + valor);
            } catch (NumberFormatException excepcion) {
                vector = proyectos.consultarEspecifico("DESCRIPCION", campoConsultar.getText().toString());
            }


            if (vector == null) {
                mensaje("Atención!", "No se encontraón coincidencias!");
                campoConsultar.setText("");
                descripcion.setVisibility(View.INVISIBLE);
                ubicacion.setVisibility(View.INVISIBLE);
                fecha.setVisibility(View.INVISIBLE);
                presupuesto.setVisibility(View.INVISIBLE);
            } else {

                descripcion.setText("Descripción: " + vector[0].getDescripcion());
                ubicacion.setText("Ubicación: " + vector[0].getUbicacion());
                fecha.setText("Fecha: " + vector[0].getFecha());
                presupuesto.setText("Presupuesto: " + vector[0].getPresupuesto());

                descripcion.setVisibility(View.VISIBLE);
                ubicacion.setVisibility(View.VISIBLE);
                fecha.setVisibility(View.VISIBLE);
                presupuesto.setVisibility(View.VISIBLE);

            }// else

        } else {
            mensaje("Atención!", "Campo vacio!");
            campoConsultar.setText("");
            descripcion.setVisibility(View.INVISIBLE);
            ubicacion.setVisibility(View.INVISIBLE);
            fecha.setVisibility(View.INVISIBLE);
            presupuesto.setVisibility(View.INVISIBLE);

        }

    }

    private void mensaje(String titulo, String mensaje) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .show();
    }

}
