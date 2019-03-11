package mx.edu.ittepuc.tpdm_u2_practica1_oscar_ibanez_loreto_proyectos_civiles;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    Button btnConsultarEspecifico, cerrar, confirmarEliminar;
    TextView descripcion, ubicacion, fecha, presupuesto, idleyenda;
    EditText campoConsultar;
    Proyectos proyectos;
    Proyectos vector[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        campoConsultar = findViewById(R.id.campoConsultar);
        idleyenda = findViewById(R.id.idleyenda);
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);

        confirmarEliminar = findViewById(R.id.confirmarEliminar);

        btnConsultarEspecifico = findViewById(R.id.btnConsultarEspecifico);
        cerrar = findViewById(R.id.cerrar);


        btnConsultarEspecifico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                metodoConsultarEspecifico();
            }
        });

        confirmarEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodoEliminar();
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

    private void metodoEliminar() {
        if (campoConsultar.getText().toString().isEmpty()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atención").setMessage("Llene todos los campos solicitados!").setPositiveButton("OK", null).show();
        } else {
            String mensaje = "";
            Proyectos proyectos = new Proyectos(this);

            boolean res = proyectos.actualizar(new Proyectos(Integer.parseInt(campoConsultar.getText().toString()),

                    descripcion.getText().toString(),
                    ubicacion.getText().toString(),
                    fecha.getText().toString(),
                    Float.parseFloat(presupuesto.getText().toString())));

            if (res) {
                mensaje = "Proyecto " + campoConsultar.getText().toString() + " actualizado correctamente.";
                descripcion.setText("");
                ubicacion.setText("");
                fecha.setText("");
                presupuesto.setText("");
                campoConsultar.setText("");
            } else {
                mensaje = "Error: No se pudo actualizar el proyecto.\nSQLite: " + proyectos.errores;
            }

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atención").setMessage(mensaje).setPositiveButton("OK", null).show();

        }// else

    }// insertar

    private void metodoConsultarEspecifico() {

        if (!campoConsultar.getText().toString().trim().isEmpty()) {
            Proyectos proyectos = new Proyectos(this);

            int valor = Integer.parseInt(campoConsultar.getText().toString());
            vector = proyectos.consultarEspecifico("NO", "" + valor);


            if (vector == null) {
                mensaje("Atención!", "No se encontraón coincidencias!");
                campoConsultar.setText("");
                descripcion.setVisibility(View.INVISIBLE);
                ubicacion.setVisibility(View.INVISIBLE);
                fecha.setVisibility(View.INVISIBLE);
                presupuesto.setVisibility(View.INVISIBLE);

                idleyenda.setVisibility(View.INVISIBLE);
                confirmarEliminar.setVisibility(View.INVISIBLE);
            } else {

                descripcion.setText(vector[0].getDescripcion());
                ubicacion.setText(vector[0].getUbicacion());
                fecha.setText(vector[0].getFecha());
                presupuesto.setText("" + vector[0].getPresupuesto());

                descripcion.setVisibility(View.VISIBLE);
                ubicacion.setVisibility(View.VISIBLE);
                fecha.setVisibility(View.VISIBLE);
                presupuesto.setVisibility(View.VISIBLE);

                idleyenda.setVisibility(View.VISIBLE);
                confirmarEliminar.setVisibility(View.VISIBLE);

            }// else

        } else {
            mensaje("Atención!", "Campo vacio!");
            campoConsultar.setText("");
            descripcion.setVisibility(View.INVISIBLE);
            ubicacion.setVisibility(View.INVISIBLE);
            fecha.setVisibility(View.INVISIBLE);
            presupuesto.setVisibility(View.INVISIBLE);

            idleyenda.setVisibility(View.INVISIBLE);
            confirmarEliminar.setVisibility(View.INVISIBLE);
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
