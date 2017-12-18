package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sql.Conexion;
import vista.Empleado;

public class Modelo extends Conexion {

    Empleado vistaEmpleado;

    public Modelo() {
    }

    public Modelo(Empleado vistaEmpleado) {
        this.vistaEmpleado = vistaEmpleado;
    }
//

    //Agregar datos a la BD
    public boolean agregarEmpleado(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String est_civil, String nom_depto) {
        // Se arma la consulta para verificar si el código a ingresar ya existe

        //Se envía el dato
        String query = "INSERT INTO empleados (codigo, rut, nombre, apellido, celular, email, sualdo_bruto, est_civil, nom_depto)"
                + "values ('" + codigo + "', '" + rut + "', '" + nombre + "', '" + apellido + "', '" + celular + "', '" + email + "', '" + sueldo_bruto + "', '" + est_civil + "', '" + nom_depto + "');";

        //se ejecuta la consulta
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            getConexion().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

        return false;

    }

    //Buscar datos dentro de la BD
    public Object buscarDato(int codigo) {
        DefaultTableModel tablemodel = new DefaultTableModel();
        //int registros = 0;
        String[] columNames = {"Codigo", "rut", "Nombre", "Apellido", "Celular", "Email", "Sualdo Bruto", "Estado Civil", "Nombre Departamento"};

        Object[][] data = new String[1][9];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT codigo, rut, nombre, apellido, celular, email, sualdo_bruto, est_civil, nom_depto FROM empleados where codigo ='" + codigo + "'");
            ResultSet res = pstm.executeQuery();
            System.out.println(codigo);
            String scodigo = String.valueOf(codigo);

            System.out.println(scodigo);

            int i = 0;
            while (res.next()) {

                data[0][0] = res.getString("codigo");
                data[0][1] = res.getString("rut");
                data[0][2] = res.getString("nombre");
                data[0][3] = res.getString("apellido");
                data[0][4] = res.getString("celular");
                data[0][5] = res.getString("email");
                data[0][6] = res.getString("sueldo_bruto");
                data[0][7] = res.getString("est_civil");
                data[0][8] = res.getString("nom_depto");

                i++;
            }
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "El código no existe en la BD");
            }

            res.close();
            tablemodel.setDataVector(data, columNames);
            getConexion().close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;

    }

    //Eliminar Dato de la BD 
    public boolean eliminarDato(int codigo) {
        // Se arma la consulta
        Empleado ep = new Empleado();
        Object o = new Object();

        System.out.println("buscarDato()");
        System.out.println(codigo);
        //ResultSet rs = null;

        try {
            String query = "delete from empleados where codigo ='" + codigo + "'";
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute(query);

            pstm.close();
            getConexion().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

        return false;

    }

    //Muestra los datos de la BD
    public DefaultTableModel mostrarDato() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Codigo", "rut", "Nombre", "Apellido", "Celular", "Email", "Sualdo Bruto", "Estado Civil", "Nombre Departamento"};
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total from empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][9];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT codigo, rut, nombre, apellido, celular, email, sualdo_bruto, est_civil, nom_depto FROM empleados'");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                data[0][0] = res.getString("codigo");
                data[0][1] = res.getString("rut");
                data[0][2] = res.getString("nombre");
                data[0][3] = res.getString("apellido");
                data[0][4] = res.getString("celular");
                data[0][5] = res.getString("email");
                data[0][6] = res.getString("sueldo_bruto");
                data[0][7] = res.getString("est_civil");
                data[0][8] = res.getString("nom_depto");

                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
            getConexion().close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;

    }

    //Modificar los datos de la BD
    public boolean modificarDato(int codigo, String nombre, int id_categoria, int precio, String formato) {
        String query = "update pelicula set nombre= '" + nombre + "', id_categoria= '" + id_categoria + "', precio= '" + precio + "', formato4k= '" + formato + "' where codigo ='" + codigo + "'";

        //se ejecuta update
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(query);
            pstm.execute();
            pstm.close();
            getConexion().close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }

        return false;

    }

    //Modificar los datos de la BD
}
