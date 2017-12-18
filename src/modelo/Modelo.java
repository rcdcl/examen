package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String query = "INSERT INTO empleados (codigo, rut, nombre, apellido, celular, email, sueldo_bruto, est_civil, nom_depto)"
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
        String[] columNames = {"Codigo", "rut", "Nombre", "Apellido", "Celular", "Email", "Sueldo Bruto", "Estado Civil", "Nombre Departamento"};

        Object[][] data = new String[1][9];
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT codigo, rut, nombre, apellido, celular, email, sueldo_bruto, est_civil, nom_depto FROM empleados where codigo ='" + codigo + "'");
            ResultSet res = pstm.executeQuery();
            System.out.println(codigo);
            String scodigo = String.valueOf(codigo);

            System.out.println(scodigo);

            int i = 0;
            while (res.next()) {

                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("rut");
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString("apellido");
                data[i][4] = res.getString("celular");
                data[i][5] = res.getString("email");
                data[i][6] = res.getString("sueldo_bruto");
                data[i][7] = res.getString("est_civil");
                data[i][8] = res.getString("nom_depto");

                i++;
            }
            /*
            if (i == 0) {
                JOptionPane.showMessageDialog(null, "El código no existe en la BD");
            }
            */
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
        String[] columNames = {"Codigo", "rut", "Nombre", "Apellido", "Celular", "Email", "Sueldo Bruto", "Estado Civil", "Nombre Departamento"};
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
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT codigo, rut, nombre, apellido, celular, email, sueldo_bruto, est_civil, nom_depto FROM empleados");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("rut");
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString("apellido");
                data[i][4] = res.getString("celular");
                data[i][5] = res.getString("email");
                data[i][6] = res.getString("sueldo_bruto");
                data[i][7] = res.getString("est_civil");
                data[i][8] = res.getString("nom_depto");

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
    
    //Mostrar Empleados Departamento de Redes
        public DefaultTableModel mostrarDatoRedes() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Codigo", "rut", "Nombre", "Apellido", "Celular", "Email", "Sueldo Bruto", "Estado Civil", "Nombre Departamento"};
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
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT codigo, rut, nombre, apellido, celular, email, sueldo_bruto, est_civil, nom_depto FROM empleados where nom_depto = 'Redes'");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {

                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("rut");
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString("apellido");
                data[i][4] = res.getString("celular");
                data[i][5] = res.getString("email");
                data[i][6] = res.getString("sueldo_bruto");
                data[i][7] = res.getString("est_civil");
                data[i][8] = res.getString("nom_depto");

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
    public boolean modificarDato(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String est_civil, String nom_depto){
    String query = "update empleados set rut= '" + rut + "', nombre= '" + nombre + "', apellido= '" + apellido + "', celular= '" + celular + "', email= '" + email + "', sueldo_bruto= '" + sueldo_bruto + "', est_civil= '" + est_civil + "', nom_depto= '" + nom_depto + "' where codigo ='" + codigo + "'";

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
    
    public boolean existeCodigo(int codigo) {
            boolean regreso=false;

try {
            PreparedStatement pstm = this.getConexion().prepareStatement("SELECT count(*) as total from empleados where codigo ='" + codigo + "'");
            ResultSet res = pstm.executeQuery();
            res.next();
            int registros = res.getInt("total");
            if (registros != 0 ){
                regreso = true;
                
            } 
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return regreso;
        
    }
        
    public boolean borrarSueldo120() {

        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement("DELETE from empleados where sueldo_bruto = 120000;");
            int r = pstm.executeUpdate();
            pstm.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;

    }
    
       public boolean aumentaSueldo() {

        
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement("UPDATE empleados SET sueldo_bruto = sueldo_bruto*1.10;");
            int r = pstm.executeUpdate();
            pstm.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

        return true;

    }
    
    

    //Modificar los datos de la BD
}
