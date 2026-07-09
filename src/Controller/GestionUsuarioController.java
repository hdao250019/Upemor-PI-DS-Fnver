package Controller;

import Models.Usuario;
import Models.UsuarioBD;
import Views.FrmGestionUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
        
        if (nombre.isEmpty() || correo.isEmpty() || Pass.isEmpty() || edad <= 0) {
            // Mostrar un mensaje de advertencia al usuario
            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        Usuario user = new Usuario(edad, nombre, correo, Pass);
        // Enviar el objeto de usuario antes de guardado para que el modelo lo reciba
        boolean resultado = usuariobd.insertar(user);
        
        // Evaluar si el resultado de la insercion fue exitoso
        if (resultado) {
            JOptionPane.showMessageDialog(ventana, "Registro exitoso");
            
        } else {
            JOptionPane.showMessageDialog(ventana, "Error al registrar");
        }
        
        
        //Mostrar los datos del usuario
        ventana.txtNombreAct.setText(nombre);
        ventana.txtCorreoAct.setText(correo);
        ventana.spnEdadAct.setValue(edad);
        ventana.txtPassAct.setText(Pass);
        
    }
    
    // Metodo para limpiar campos de Usuarios
    private void LimpiarCamposUser(){
        ventana.txtNombreAct.setText("");
        ventana.txtCorreoAct.setText("");
        ventana.spnEdadAct.setValue(0);
        ventana.txtPassAct.setText("");
        
        
    }
}
