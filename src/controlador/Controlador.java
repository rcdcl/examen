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
        btnagregar,//boton agregar de vista
        btnmodificar,// botón modificar de vista
        btneliminar,// botón eliminar de vista
        btnmostrar,// botón mostrar de vista
        btnlimpiar,//botón limpiar de vista
        btnsalir,// botón salir vista
        btnbuscar,// botón buscar vista
        
        
        
        cbodescripcion// combo descripcion vista
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
            this.vistaEmpleado.setTitle("VideoBuster");

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
        //this.vistaEmpleado.btnmostrar.setActionCommand("btnmostrar");
        //this.vistaEmpleado.btnmostrar.addActionListener(this);

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

    }
    
    //limpia la tabla
    public void eliminar() {
        DefaultTableModel tb = (DefaultTableModel) this.vistaMostrar.tbProducto.getModel();
        int a = this.vistaMostrar.tbProducto.getRowCount() - 1;
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
        this.vistaEmpleado.txtprecio.setText("0");
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

                int verificacionprecio = Integer.parseInt(this.vistaEmpleado.txtprecio.getText());



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

                            
                            
                            if (this.modeloDato.agregarDatoProducto(Integer.parseInt(this.vistaEmpleado.txtcodigo.getText()), this.vistaEmpleado.txtnombre.getText(), this.vistaEmpleado.cboestadocivil.getSelectedIndex(), Integer.parseInt(this.vistaEmpleado.txtprecio.getText()), String.valueOf(this.vistaEmpleado.cbodepartamento.getSelectedItem()))) {

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
                this.vistaMostrar.tbProducto.setModel(this.modeloDato.mostrarDato());
                break;

            case btnmodificar:
                //lamamos método para modificar valores del producto menos el código
                if (this.modeloDato.modificarDato(Integer.parseInt(this.vistaEmpleado.txtcodigo.getText()), this.vistaEmpleado.txtnombre.getText(), this.vistaEmpleado.cboestadocivil.getSelectedIndex(), Integer.parseInt(this.vistaEmpleado.txtprecio.getText()), String.valueOf(this.vistaEmpleado.cbodepartamento.getSelectedItem()))) {

                    JOptionPane.showMessageDialog(null, "El Producto se modificó correctamente");

                    //Limpiamos
                    limpiartodo();
                    eliminar();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar");
                }

                System.out.println("btnmodificar");
                this.vistaMostrar.tbProducto.setModel(this.modeloDato.mostrarDato());
                break;

            case btnmostrar:

                this.vistaMostrar.tbProducto.setModel(this.modeloDato.mostrarDato());
                break;

            case btnsalir:
            
                System.exit(0);
                break;
                
            case btnbuscar:
                int codigoss = Integer.parseInt(this.vistaEmpleado.txtcodigo.getText());
                boolean bconfirmacion = false;
                System.out.println("buscar");
                this.vistaMostrar.tbProducto.setModel((TableModel) this.modeloDato.buscarDato(codigoss));
                if (bconfirmacion == true) {
                    JOptionPane.showMessageDialog(null, "El registro fue eliminado con éxito");
                }
                
                break;
                
            case btnlimpiar:
                eliminar();
                limpiartodo();
                break;

        }

    }

}
