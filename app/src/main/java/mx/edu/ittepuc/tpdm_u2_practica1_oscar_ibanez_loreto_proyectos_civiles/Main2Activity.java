package mx.edu.ittepuc.tpdm_u2_practica1_oscar_ibanez_loreto_proyectos_civiles;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText descripcionProyecto, ubicacionProyecto, fechaProyecto, presupuestoProyecto;
    Button btnInsertar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        descripcionProyecto = findViewById(R.id.descripcionProyecto);
        ubicacionProyecto = findViewById(R.id.ubicacionProyecto);
        fechaProyecto = findViewById(R.id.fechaProyecto);
        presupuestoProyecto = findViewById(R.id.presupuestoProyecto);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

    }// onCreate

    private void insertar() {
        if (validarVacios()) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atención").setMessage("Llene todos los campos solicitados!").setPositiveButton("OK", null).show();
        } else {
            String mensaje = "";
            Proyectos proyectos = new Proyectos(this);

            boolean res = proyectos.insertar(new Proyectos(0, descripcionProyecto.getText().toString(),
                    ubicacionProyecto.getText().toString(),
                    fechaProyecto.getText().toString(),
                    Float.parseFloat(presupuestoProyecto.getText().toString())));

            if (res) {
                mensaje = "Proyecto " + descripcionProyecto.getText().toString() + " insertado correctamente.";
                descripcionProyecto.setText("");
                ubicacionProyecto.setText("");
                fechaProyecto.setText("");
                presupuestoProyecto.setText("");
            } else {
                mensaje = "Error: No se pudo guardar el proyecto.\nSQLite: " + proyectos.errores;
            }

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atención").setMessage(mensaje).setPositiveButton("OK", null).show();

        }// else

    }// insertar

    private boolean validarVacios() {
        if (descripcionProyecto.getText().toString().isEmpty() ||
                ubicacionProyecto.getText().toString().isEmpty() ||
                fechaProyecto.getText().toString().isEmpty() ||
                presupuestoProyecto.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }


}// class
