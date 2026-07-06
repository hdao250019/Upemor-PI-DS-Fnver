package Controller;

import Models.UsuarioBD;
import Views.FrmGestionUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;

/**
 *
 * @author Jared
 */
public class GestionUsuarioController implements ActionListener {
    
    private FrmGestionUsuarios ventana;
    private UsuarioBD usuariobd;
    private JTable tabla;
    private int idSeleccionado = -1;
    
    // Constructor
    public GestionUsuarioController(FrmGestionUsuarios ventana, UsuarioBD usuariobd) {
        this.ventana = ventana;
        this.usuariobd = usuariobd;
        this.ventana.BTN_Act.addActionListener(this);
        this.ventana.BTN_Eli.addActionListener(this);
        this.ventana.BTN_Lim.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == ventana.BTN_Act){
            
        }
        
        if(e.getSource() == ventana.BTN_Eli){
            
        }
        
        if(e.getSource() == ventana.BTN_Lim){
            
        }
        
    }

    // Método para detectar el click en la tabla
    private void seleccionTabla(){
        tabla.getSelectionModel().addListSelectionListener(evento -> {
    
        if(evento.getValueIsAdjusting()){
            //TODO: Agregar la carga de usuarios
            
            
        }
        
    });
        
    }
    private void cargarUsuarios(){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            return;
        }
        
        // obtener los valores desde la tabla
        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        String nombre = tabla.getValueAt(fila, 1).toString();
        String correo = tabla.getValueAt(fila, 2).toString();
        int edad = Integer.parseInt(tabla.getValueAt(fila, 3).toString());
        String Pass = tabla.getValueAt(fila, 4).toString();
        
        //Mostrar los datos del usuario
        ventana.txtNombre.setText(nombre);
        ventana.txtCorreo.setText(correo);
        ventana.spnEdad.setValue(edad);
        ventana.txtPass.setText(Pass);
        
    }
    
}
