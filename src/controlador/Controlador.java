/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import modelo.Modelo;
import vista.Empleado;
import vista.Mostrar;


/**
 *
 * @author MBpro_Rafa
 */
public class Controlador implements ActionListener {

    //Declarar vista
    Empleado vistaEmpleado;
    Mostrar vistaMostrar = new Mostrar();
    

    //Declaro el modelo
    Modelo modeloDato = new Modelo();

    //Metodo enumerar donde se agregan los botones o elementos que van a desencadenar acciones (que voy a escuchar)
    public enum Accion {
        btnagregar,//boton agregar de vista Empleado
        btnmodificar,// botón modificar de vista Empleado
        btneliminar,// botón eliminar de vista Empleado
        btnsalir,// botón salir vista Empleado
        btnbuscar,// botón buscar vista Empleado
        
        btnmostrar,// botón mostrar de vista Mostrar  
        btnvolver, // botón volver de vista Mostar
        btnlimpiar,//botón limpiar de vista 
                
        // opciones barra menúmenú
        msissalir, // opción salir barra menú
        mempmostrar, // opción vista empleado barra menú
        
        // combos
        cboestadocivil, // combo estado civil vista Empleado    
        cbodepartamento, // combo selección departamento vista Empleado
    }

    //Agregamos el constructor de la clase
    public Controlador(Empleado vistaEmpleado) {
        this.vistaEmpleado = vistaEmpleado;
        //this.vistaElDato.setVisible(true);

    }

    //Creamos metodo para iniciar
    public void iniciar() {

        try {
            this.vistaEmpleado.setVisible(true);//Hago que la vista sea visible
            this.vistaEmpleado.setLocationRelativeTo(null);
            this.vistaEmpleado.setTitle("Empleados");

            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //SwingUtilities.updateComponentTreeUI(vistaEmpleado);
        } catch (Exception ex) {
        }

        //catch (UnsupportedLookAndFeelException ex){}
        //catch (ClassNotFoundException ex) {}
        //catch (InstantiationException ex) {}
        //catch (IllegalAccessException ex) {}
        // Escuchamos el boton que ingresa el dato
        this.vistaEmpleado.btnagregar.setActionCommand("btnagregar");
        this.vistaEmpleado.btnagregar.addActionListener(this);
        // Escuchamos el boton que modifica el dato
        this.vistaEmpleado.btnmodificar.setActionCommand("btnmodificar");
        this.vistaEmpleado.btnmodificar.addActionListener(this);
        // Escuchamos el boton que elimina el dato
        this.vistaEmpleado.btneliminar.setActionCommand("btneliminar");
        this.vistaEmpleado.btneliminar.addActionListener(this);
        // Escuchamos el boton que muestra el dato
        this.vistaMostrar.btnmostrar.setActionCommand("btnmostrar");
        this.vistaMostrar.btnmostrar.addActionListener(this);
        // Escuchamos el boton volver de vista Mostrar
        this.vistaMostrar.btnvolver.setActionCommand("btnvolver");
        this.vistaMostrar.btnvolver.addActionListener(this);
       
        // Escuchamos el boton que limpia datos de pantalla
        //this.vistaEmpleado.btnlimpiar.setActionCommand("btnlimpiar");
        //this.vistaEmpleado.btnlimpiar.addActionListener(this);
        
        // Escuchamos el boton que salir
        this.vistaEmpleado.btnsalir.setActionCommand("btnsalir");
        this.vistaEmpleado.btnsalir.addActionListener(this);
        // Escuchamos el boton que buscar
        this.vistaEmpleado.btnbuscar.setActionCommand("btnbuscar");
        this.vistaEmpleado.btnbuscar.addActionListener(this);
        // Escuchamos el bcombo descripción
        this.vistaEmpleado.cboestadocivil.setActionCommand("cbodescripcion");
        this.vistaEmpleado.cboestadocivil.addActionListener(this);
        
        // Escuchamos el opción salir barra menú
        this.vistaEmpleado.msissalir.setActionCommand("msissalir");
        this.vistaEmpleado.msissalir.addActionListener(this);
        // Escuchamos opción mostar barra menú
        this.vistaEmpleado.mempmostrar.setActionCommand("mempmostrar");
        this.vistaEmpleado.mempmostrar.addActionListener(this);
        

    }
    
    //limpia la tabla
    public void eliminar() {
        DefaultTableModel tb = (DefaultTableModel) this.vistaMostrar.tbEmpleado.getModel();
        int a = this.vistaMostrar.tbEmpleado.getRowCount() - 1;
        System.out.println(a);
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }

    }
    
    //limpia los datos de pantalla
    public void limpiartodo() {
        this.vistaEmpleado.txtnombre.setText("");
        this.vistaEmpleado.cboestadocivil.setSelectedIndex(0);
        this.vistaEmpleado.txtcodigo.setText("0");
        this.vistaEmpleado.txtcelular.setText("0");
        this.vistaEmpleado.cbodepartamento.setSelectedIndex(0);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Accion.valueOf(e.getActionCommand())) {
            case btnagregar:
                //llamamos al metodo que esta en el modelo para agragar el dato y le enviamos lo que captura del textField
                
                try{
                
                Inicio ep = new Inicio();
                String active, active2;


                active = String.valueOf(this.vistaEmpleado.cbodepartamento.getSelectedItem());
                active2 = String.valueOf(this.vistaEmpleado.cboestadocivil.getSelectedItem());
                

                System.out.println(active);
                boolean correcto1 = false,
                 correcto2 = false;
                

                int verificacioncodigo = Integer.parseInt(this.vistaEmpleado.txtcodigo.getText());

                int verificacionprecio = Integer.parseInt(this.vistaEmpleado.txtcelular.getText());



                if (verificacioncodigo < 10000 || verificacioncodigo > 99999) {

                    JOptionPane.showMessageDialog(null, "El código debe estar entre 10000 y 99999. Intente nuevamente");

                } else {

                    if (active == "Seleccione" || active2 == "Seleccione") {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar Formato de pelicula y descripción");

                        System.out.println(active);

                    } else {

                        if (verificacionprecio <= 0) {
                            JOptionPane.showMessageDialog(null, "El precio debe ser mayor que 0");
                        } else {

                            
                                                                                    // int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String est_civil, String nom_depto
                            if (this.modeloDato.agregarEmpleado(Integer.parseInt(this.vistaEmpleado.txtcodigo.getText()), this.vistaEmpleado.txtrut.getText() ,this.vistaEmpleado.txtnombre.getText(), this.vistaEmpleado.txtapellido.getText(), Integer.parseInt(this.vistaEmpleado.txtcelular.getText()), this.vistaEmpleado.txtemail.getText(), Integer.parseInt(this.vistaEmpleado.txtsueldo.getText()), String.valueOf(this.vistaEmpleado.cboestadocivil.getSelectedItem()), String.valueOf(this.vistaEmpleado.cbodepartamento.getSelectedItem()))) {

                                JOptionPane.showMessageDialog(null, "El Producto se agregó correctamente");

                                limpiartodo();
                                eliminar();

                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo agregar");
                            }
                        }
                    }
                }
                } catch (Exception ex){}

                break;



            case btneliminar:
                //llamamos método para eliminar dato
                int codigos = Integer.parseInt(this.vistaEmpleado.txtcodigo.getText());
                boolean confirmacion = false;
                System.out.println("btneliminar");
                confirmacion = this.modeloDato.eliminarDato(codigos);
                if (confirmacion == true) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                }
                this.vistaMostrar.tbEmpleado.setModel(this.modeloDato.mostrarDato());
                break;

            case btnmodificar:
                //lamamos método para modificar valores del producto menos el código
                if (this.modeloDato.modificarDato(Integer.parseInt(this.vistaEmpleado.txtcodigo.getText()), this.vistaEmpleado.txtnombre.getText(), this.vistaEmpleado.cboestadocivil.getSelectedIndex(), Integer.parseInt(this.vistaEmpleado.txtcelular.getText()), String.valueOf(this.vistaEmpleado.cbodepartamento.getSelectedItem()))) {

                    JOptionPane.showMessageDialog(null, "El Producto se modificó correctamente");

                    //Limpiamos
                    limpiartodo();
                    eliminar();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar");
                }

                System.out.println("btnmodificar");
                this.vistaMostrar.tbEmpleado.setModel(this.modeloDato.mostrarDato());
                break;

            case btnmostrar:

                this.vistaMostrar.tbEmpleado.setModel(this.modeloDato.mostrarDato());
                break;

            case btnsalir:
                System.exit(0);
                break;
                
            case btnbuscar:
                int codigoss = Integer.parseInt(this.vistaEmpleado.txtcodigo.getText());
                boolean bconfirmacion = false;
                System.out.println("buscar");
                this.vistaMostrar.tbEmpleado.setModel((TableModel) this.modeloDato.buscarDato(codigoss));
                if (bconfirmacion == true) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                }
                
                break;
                
            case btnlimpiar:
                eliminar();
                limpiartodo();
                break;
                
            case btnvolver:
                this.vistaMostrar.setVisible(false);//Hago que la vista sea visible

                break;


            case msissalir:
                System.exit(0);
                break;
                
            case mempmostrar:
                this.vistaMostrar.setVisible(true);//Hago que la vista sea visible
                this.vistaMostrar.setLocationRelativeTo(null);
                this.vistaMostrar.setTitle("Mostras Datos Empleados");
                
                break;
            

        }

    }

}
