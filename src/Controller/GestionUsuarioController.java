package Controller;

import Models.Usuario;
import Models.UsuarioBD;
import Views.FrmGestionUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
        mostrarUsuarios();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (this.ventana != null) {
            // Verifica si el botón Crear Cuenta ya fue inicializado en los componentes
            if (this.ventana.BTN_Act != null) {
                if(e.getSource() == ventana.BTN_Act){
                actuaizarUsuario();
            }
        }
    
        
            if (this.ventana.BTN_Eli != null) {
                if(e.getSource() == ventana.BTN_Eli){
                eliminarUsuario();
            }
        }
        if(e.getSource() == ventana.BTN_Lim){
            limpiarCampos();
        }
    }
    }

    // Método para detectar el click en la tabla
    private void seleccionTabla() {
    // Verificamos si la tabla existe antes d ejecutarla
    if (this.tabla != null) { 
        this.tabla.getSelectionModel().addListSelectionListener(evento -> {
            if (!evento.getValueIsAdjusting()) {
                cargarUsuarios();
            }
        });
    }
}
    private void cargarUsuarios(){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            return;
        }
        
         // obtener los valores desde la tabla
        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        String nombre = tabla.getValueAt(fila, 1).toString();
        int edad = Integer.parseInt(tabla.getValueAt(fila, 2).toString());
        String correo = tabla.getValueAt(fila, 3).toString();
        String Pass = tabla.getValueAt(fila, 4).toString();

        //Mostrar los datos del usuario
        ventana.txtNombreAct.setText(nombre);
        ventana.txtCorreoAct.setText(correo);
        ventana.spnEdadAct.setValue(edad);
        ventana.txtPassAct.setText(Pass);
    }
    
     // METODO CREAR TABLA
        private void mostrarUsuarios(){
        DefaultTableModel modelo = new DefaultTableModel();
            
        // Añadir columnas en tablas
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo");
        modelo.addColumn("Contraseña");
        List<Usuario> listaUsuarios = usuariobd.consultarUsuarios();
        
        // Ciclo para recorrer la lista de usuarios
        for(Usuario usuario : listaUsuarios){
            // Guardar os objetos en un arreglo para usarlo en la tabla s
            Object[] fila = {

            usuario.getId(),
            usuario.getNombre(),
            usuario.getEdad(),
            usuario.getCorreo(),
            usuario.getContrasenia()
        };
            modelo.addRow(fila);
        }
        //Crear tabla
        this.tabla = new JTable(modelo);
        // Usar JScrollpane para mostrar la tabla
        ventana.paneUsuarios.setViewportView(this.tabla);
        seleccionTabla();
        }

         // METODO PARA ACTUALIZAR UN REGISTRO
        private void actuaizarUsuario(){
             
            // Validar si un ID fue seleccionado
            if(idSeleccionado == -1){
                JOptionPane.showMessageDialog(ventana, "SELECCIONA UN REGISTRO");
                return;
            }
            
            String nombre = ventana.txtNombreAct.getText().trim();
            int edad = Integer.parseInt(ventana.spnEdadAct.getValue().toString());
            String correo = ventana.txtCorreoAct.getText().trim();
            String contrasenia = ventana.txtPassAct.getText().trim();
            
            if (nombre.isEmpty() || correo.isEmpty() || contrasenia.isEmpty() || edad <= 0) {
            // Mostrar un mensaje de advertencia al usuario
            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
            // OBTENER LOS NOMBRES DESDE LA VENTANA
            Usuario usuario = new Usuario(idSeleccionado,edad, nombre, correo, contrasenia);
            
        // Enviar el objeto de usuario antes de guardado para que el modelo lo reciba
            boolean actualizar = usuariobd.actualizar(usuario);
        
            if(actualizar){
                JOptionPane.showMessageDialog(ventana, "Actualizacion exitosa");
            }else{
                JOptionPane.showMessageDialog(ventana, "Error al actualizar los Datos");
            }
            mostrarUsuarios();
            limpiarCampos();
        }
        
        //Metodo para eliminar usuarios
        public void eliminarUsuario(){
            
            // Validar si hay un id seleccionado
            if(idSeleccionado == -1){
                JOptionPane.showMessageDialog(ventana, "Selecciona un registro");
                return;
            }
            
            // Hacer confirmacion de la eliminacion del registro
            int confirmacion = JOptionPane.showConfirmDialog(ventana, 
                    "Estas seguro de querer eliminar a este usuario? ",
                    "Confirmar Eliminacion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            
            if(confirmacion != JOptionPane.YES_NO_OPTION){
                return;
            }
            boolean eliminacion = usuariobd.eliminar(idSeleccionado);
            if(eliminacion){
                JOptionPane.showMessageDialog(ventana,"Eliminacion exitosa");
                mostrarUsuarios();
                limpiarCampos();
            }
        
        }
        
        // Metodo para limpiar campos
        private void limpiarCampos(){
        
        idSeleccionado = -1;

        ventana.txtNombreAct.setText("");
        ventana.txtCorreoAct.setText("");
        ventana.spnEdadAct.setValue(0);
        ventana.txtPassAct.setText("");
        
        
    }
}
