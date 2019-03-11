package mx.edu.ittepuc.tpdm_u2_practica1_oscar_ibanez_loreto_proyectos_civiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listaProyectos;
    Proyectos proyectos;
    Proyectos vector[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaProyectos = findViewById(R.id.listaProyectos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        Proyectos proyectos = new Proyectos(this);
        vector = proyectos.consultar();

        String[] descripcionProyecto = null;

        if (vector == null) {
            descripcionProyecto = new String[1];
            descripcionProyecto[0] = "* No hay proyectos capturados aún. *";
        } else {
            descripcionProyecto = new String[vector.length];

            for (int i = 0; i < vector.length; i++) {
                Proyectos temp = vector[i];
                descripcionProyecto[i] = temp.getDescripcion();
            }// for
        }// else

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descripcionProyecto);
        listaProyectos.setAdapter(adaptador);


        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuinsertar:
                Intent activityInsertar = new Intent(this, Main2Activity.class);
                startActivity(activityInsertar);
                break;
            case R.id.menuconsultar:
                Intent activityConsultar = new Intent(this, Main3Activity.class);
                startActivity(activityConsultar);
                break;
            case R.id.menueliminar:
                Intent activityEliminar = new Intent(this, Main4Activity.class);
                startActivity(activityEliminar);
                break;
            case R.id.menumodificar:
                Intent activityModificar = new Intent(this, Main5Activity.class);
                startActivity(activityModificar);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
