package mx.edu.ittepuc.tpdm_u2_practica1_oscar_ibanez_loreto_proyectos_civiles;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class Proyectos {

    private BaseDatos base;
    private int id;
    private String descripcion, ubicacion, fecha;
    private float presupuesto;
    protected String errores;

    public Proyectos(Activity activity) {
        base = new BaseDatos(activity, "civil", null, 1);
    }// constructor

    public Proyectos(int id, String descripcion, String ubicacion, String fecha, float presupuesto) {
        this.id = id;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.presupuesto = presupuesto;
    }

    public boolean insertar(Proyectos proyecto) {
        try {

            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();

            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyecto.getDescripcion());
            datos.put("UBICACION", proyecto.getUbicacion());
            datos.put("FECHA", proyecto.getFecha());
            datos.put("PRESUPUESTO", proyecto.getPresupuesto());

            long resultado = transaccionInsertar.insert("PROYECTOS", "IDPROYECTO", datos);
            transaccionInsertar.close();

            if (resultado == -1) {
                return false;
            }

        } catch (SQLException e) {
            errores = e.getMessage();
            return false;
        }
        return true;
    }


    public Proyectos[] consultarEspecifico(String columna, String clave) {
        Proyectos[] resultado = null;
        try {
            SQLiteDatabase transaccionConsulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS WHERE IDPROYECTO =" + clave;

            if (columna.startsWith("DESCRIPCION")) {
                SQL = "SELECT * FROM PROYECTOS WHERE DESCRIPCION LIKE '%" + clave + "%'";
            }

            Cursor c = transaccionConsulta.rawQuery(SQL, null);
            if (c.moveToFirst()) {
                resultado = new Proyectos[c.getCount()];
                int pos = 0;

                resultado[pos] = new Proyectos(c.getInt(0), c.getString(1),
                        c.getString(2), c.getString(3), c.getFloat(4));
                pos++;

            }
            transaccionConsulta.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;

    }

    public Proyectos[] consultar() {

        Proyectos[] resultado = null;
        try {
            SQLiteDatabase transaccionConsultaGeneral = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROYECTOS";

            Cursor c = transaccionConsultaGeneral.rawQuery(SQL, null);
            if (c.moveToFirst()) {
                resultado = new Proyectos[c.getCount()];
                int pos = 0;
                do {
                    resultado[pos] = new Proyectos(c.getInt(0), c.getString(1),
                            c.getString(2), c.getString(3), c.getFloat(4));
                    pos++;
                } while (c.moveToNext());
            }
            transaccionConsultaGeneral.close();
        } catch (SQLException e) {
            return null;
        }
        return resultado;
    }

    public boolean eliminar(Proyectos a) {
        // 0 significa cero eliminaciones y mas de 0 significa que hubo mas de 0 eliminaciones
        int resultado;
        try {
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            resultado = transaccionEliminar.delete("PROYECTOS", "IDPROYECTO=?", new String[]{"" + a.getId()});
            transaccionEliminar.close();

        } catch (SQLException e) {
            errores = e.getMessage();
            return false;
        }
        return resultado > 0;
    }// eliminar

    public boolean actualizar(Proyectos proyecto) {
        try {

            SQLiteDatabase transaccionActualzar = base.getWritableDatabase();

            ContentValues datos = new ContentValues();
            datos.put("DESCRIPCION", proyecto.getDescripcion());
            datos.put("UBICACION", proyecto.getUbicacion());
            datos.put("FECHA", proyecto.getFecha());
            datos.put("PRESUPUESTO", proyecto.getPresupuesto());

            long resultado = transaccionActualzar.update("PROYECTOS", datos, "IDPROYECTO=?"
                    , new String[]{"" + proyecto.getId()});

            transaccionActualzar.close();

            if (resultado == -1) {
                return false;
            }
        } catch (SQLException e) {
            errores = e.getMessage();
            return false;
        }

        return true;
    }// actualizar


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
}
