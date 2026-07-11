package Controller;

import Models.Categoria;
import Models.UsuarioBD;
import Views.FrmGestionarCate;
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

public class GestionCatController implements ActionListener {
    
    private FrmGestionarCate ventana;
    private UsuarioBD usuariobd;
    private JTable tabla;
    private int idSeleccionado = -1;
    
    // Constructor
    public GestionCatController(FrmGestionarCate ventana, UsuarioBD usuariobd) {
        this.ventana = ventana;
        this.usuariobd = usuariobd;
        this.ventana.BTN_Act.addActionListener(this);
        this.ventana.BTN_Eli.addActionListener(this);
        this.ventana.BTN_Lim.addActionListener(this);
        mostrarCategorias();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (this.ventana != null) {
            // Verifica si el botón Crear Cuenta ya fue inicializado en los componentes
            if (this.ventana.BTN_Act != null) {
                if(e.getSource() == ventana.BTN_Act){
                actuaizarCategoria();
            }
        }
    
        
            if (this.ventana.BTN_Eli != null) {
                if(e.getSource() == ventana.BTN_Eli){
                eliminarCat();
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
                cargarCat();
            }
        });
    }
}
    private void cargarCat(){
        int fila = tabla.getSelectedRow();
        if (fila == -1){
            return;
        }
        
         // obtener los valores desde la tabla
        idSeleccionado = Integer.parseInt(tabla.getValueAt(fila, 0).toString());
        String categoria = tabla.getValueAt(fila, 1).toString();
        String descripcion = tabla.getValueAt(fila, 2).toString();
        String total = tabla.getValueAt(fila, 3).toString();

        //Mostrar los datos del usuario
        ventana.TXT_Cat.setText(categoria);
        ventana.TXA_Desc.setText(descripcion);
        ventana.TXT_Cant.setText(total);
    }
    
     // METODO CREAR TABLA
        private void mostrarCategorias(){
        DefaultTableModel modelo = new DefaultTableModel();
            
        // Añadir columnas en tablas
        modelo.addColumn("ID");
        modelo.addColumn("Categoria");
        modelo.addColumn("Descripción");
        modelo.addColumn("Total");
        List<Categoria> listaCat = usuariobd.consultarCategorias();
        
        // Ciclo para recorrer la lista de usuarios
        for(Categoria categoria : listaCat){
            // Guardar os objetos en un arreglo para usarlo en la tabla s
            Object[] fila = {
                categoria.getId(),
                categoria.getCategoria(),
                categoria.getDescripcion(),
                categoria.getTotal()
        };
            modelo.addRow(fila);
        }
        //Crear tabla
        this.tabla = new JTable(modelo);
        // Usar JScrollpane para mostrar la tabla
        ventana.paneCat.setViewportView(this.tabla);
        seleccionTabla();
        }

         // METODO PARA ACTUALIZAR UN REGISTRO
        private void actuaizarCategoria(){
             
            // Validar si un ID fue seleccionado
            if(idSeleccionado == -1){
                JOptionPane.showMessageDialog(ventana, "SELECCIONA UN REGISTRO");
                return;
            }
            
            String categoria = ventana.TXT_Cat.getText().trim();
            double total = Double.parseDouble(ventana.TXT_Cant.getText().trim());
            String descripcion = ventana.TXA_Desc.getText().trim();
            
            if (categoria.isEmpty() || descripcion.isEmpty() || total <= 0) {
            // Mostrar un mensaje de advertencia al usuario
            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
            // OBTENER LOS NOMBRES DESDE LA VENTANA
            Categoria cat = new Categoria(idSeleccionado, categoria, descripcion, total);
            
        // Enviar el objeto de usuario antes de guardado para que el modelo lo reciba
            boolean actualizar = usuariobd.actualizarCat(cat);
        
            if(actualizar){
                JOptionPane.showMessageDialog(ventana, "Actualizacion exitosa");
            }else{
                JOptionPane.showMessageDialog(ventana, "Error al actualizar los Datos");
            }
            mostrarCategorias();
            limpiarCampos();
        }
        
        //Metodo para eliminar usuarios
        public void eliminarCat(){
            
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
            boolean eliminacion = usuariobd.eliminarCat(idSeleccionado);
            if(eliminacion){
                JOptionPane.showMessageDialog(ventana,"Eliminacion exitosa");
                mostrarCategorias();
                limpiarCampos();
            }
        
        }
        
        // Metodo para limpiar campos
        private void limpiarCampos(){
        
        idSeleccionado = -1;
        ventana.TXT_Cat.setText("");
        ventana.TXA_Desc.setText("");
        ventana.TXT_Cant.setText("");
        
        
    }
}

  
