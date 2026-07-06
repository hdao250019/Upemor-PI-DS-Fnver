package Controller;

import Models.Categoria;
import Views.FrmConsultarUsuarios;
import Views.FrmNuevoUsuario;
import Models.UsuarioBD;
import Models.Usuario;
import Views.FrmConsultarCategorias;
import Views.FrmGestionUsuarios;
import Views.FrmIngresarCategoria;
import Views.MainFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jared
 */
public class UserController implements ActionListener{
    
    //crear objetos para el modelo y la vista
    private MainFrm menu;
    private FrmNuevoUsuario ventana;
    private UsuarioBD usuariobd;
    private FrmConsultarUsuarios verUsuarios;
    private FrmIngresarCategoria ingresarCategoria;
    private FrmConsultarCategorias consultarCat;
    
    
    
    // Constructor para iniciazar los ojetos}
    public UserController(FrmNuevoUsuario ventana, UsuarioBD usuariobd,FrmConsultarUsuarios verUsuarios, FrmIngresarCategoria ingresarCategoria, FrmConsultarCategorias consultarCat) {
        this.ventana = ventana;
        this.usuariobd = usuariobd;
        this.verUsuarios = verUsuarios;
        this.ingresarCategoria = ingresarCategoria;
        this.consultarCat = consultarCat;
        
        
        
        // Verifica si el objeto de la ventana de registro existe
        if (this.ventana != null) {
            // Verifica si el botón Crear Cuenta ya fue inicializado en los componentes
            if (this.ventana.BTNCrear != null) {
                this.ventana.BTNCrear.addActionListener(this);
            }
        }
        // Verifica si el objeto de la ventana de consulta de usuarios existe
        if (this.verUsuarios != null) {
            // Verifica si el botón Mostrar Usuarios existe en el frame
            if (this.verUsuarios.btnUsuarios != null) {
                this.verUsuarios.btnUsuarios.addActionListener(this);
            }
            mostrarUsuarios();
        }
        
        // Verifica si el objeto de la ventana de registro existe
        if (this.ingresarCategoria != null) {
            // Verifica si el botón Crear Cuenta ya fue inicializado en los componentes
            if (this.ingresarCategoria.BTN_CAT != null) {
                this.ingresarCategoria.BTN_CAT.addActionListener(this);
            }
        }
        
        // Verifica si el objeto de la ventana de registro existe
        if (this.consultarCat != null) {
            // Verifica si el botón mostrar Categorias ya fue inicializado en los componentes
            if (this.consultarCat.btnCategorias != null) {
                this.consultarCat.btnCategorias.addActionListener(this);
            }
            mostrarCtegorias();
        }
        
    }
    
    
    //Metodo obligatorio para la clase abstracta
    //Override es sobreescribir el código de un metodo existente (actionPerformed)
    @Override
    public void actionPerformed(ActionEvent e){
        
        
    // Validamos que ventana exista antes de preguntar por su botón
    // Sin esta validacion java no encuentra la ventana por lo que marca error
        if (this.ventana != null && this.ventana.BTNCrear != null) {
            if (e.getSource() == this.ventana.BTNCrear) {
                
                guardarUsuario();
            }
        }
        
        // Validamos que verUsuarios exista antes de preguntar por su botón
        if (this.verUsuarios != null && this.verUsuarios.btnUsuarios != null) {
            if (e.getSource() == this.verUsuarios.btnUsuarios) {
                mostrarUsuarios();
            }
        }
        
        // Validamos que verUsuarios exista antes de preguntar por su botón
        if (this.ingresarCategoria != null && this.ingresarCategoria.BTN_CAT != null) {
            if (e.getSource() == this.ingresarCategoria.BTN_CAT) {
                guardarCategoria();
            }
        }
        
        // Validamos que verUsuarios exista antes de preguntar por su botón
        if (this.consultarCat != null && this.consultarCat.btnCategorias != null) {
            if (e.getSource() == this.consultarCat.btnCategorias) {
                mostrarCtegorias();
            }
        }
        
        
        
        
}
    
    // Metodo para guardar Usuarios
    private void guardarUsuario(){
        
        // Obtener los datos desde la ventana
        String nombre = ventana.txtNombre.getText().trim();
        String correo = ventana.txtCorreo.getText().trim();
        int edad = Integer.parseInt(ventana.spnEdad.getValue().toString());
        String contraseña = ventana.txtPass.getText().trim();
        
        if (nombre.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || edad <= 0) {
            // Mostrar un mensaje de advertencia al usuario
            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        
        Usuario user = new Usuario(edad, nombre, correo, contraseña);
        // Enviar el objeto de usuario antes de guardado para que el modelo lo reciba
        boolean resultado = usuariobd.insertar(user);
        
        // Evaluar si el resultado de la insercion fue exitoso
        if (resultado) {
            JOptionPane.showMessageDialog(ventana, "Registro exitoso");
            LimpiarCamposUser();
            
            // Solo actualiza la tabla si la ventana existe
            if (this.verUsuarios != null) {
                mostrarUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(ventana, "Error al registrar");
        }
    }
    
    
    // Metodo para crear la tabla mostrarUsuarios
    private void mostrarUsuarios(){
    
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Añadir columnas de la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Correo");
        modelo.addColumn("Contraseña");
        
        List<Usuario> listaUsuarios = usuariobd.consultarUsuarios();
        
        // Ciclo para recorrer la lista de usuarios
        for(Usuario usuario : listaUsuarios){
            
            // Guardar los objetos en un arreglo para usarlos en la tabla
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
        JTable tablaUser = new JTable(modelo);
        // Usar JScrollpane para mostrar la tabla
        verUsuarios.paneUsuarios.setViewportView(tablaUser);
        
    }
        
    
    // Metodo para guardar la categoria
    private void guardarCategoria(){
    // Obtener los datos desde la ventana
        String categoria = ingresarCategoria.TXT_Cat.getText().trim();
        String descripcion = ingresarCategoria.TXA_Desc.getText().trim();
        String totalStr = ingresarCategoria.TXT_Cant.getText().trim();
        
        if (totalStr.isEmpty() || categoria.isEmpty() || descripcion.isEmpty()) {
            // Mostrar un mensaje de advertencia al usuario
            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", 
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
            Double total = Double.parseDouble(totalStr);
        Categoria cat = new Categoria(categoria, descripcion, total);
        // Enviar el objeto de usuario antes de guardado para que el modelo lo reciba
        boolean resultado = usuariobd.categorias(cat);
        
        // Evaluar si el resultado de la insercion fue exitoso
        if (resultado) {
            JOptionPane.showMessageDialog(ventana, "Registro exitoso");
            LimpiarCamposCat();
            
            // Solo actualiza la tabla si la ventana existe
            if (this.verUsuarios != null) {
                mostrarUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(ventana, "Error al registrar");
        }
    }
    
    
    // Metodo para crear la tabla mostrarCategorias
    private void mostrarCtegorias(){
    
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Añadir columnas de la tabla
        modelo.addColumn("ID");
        modelo.addColumn("Categoria");
        modelo.addColumn("Descripción");
        modelo.addColumn("Dinero Ingresado");
        
        List<Categoria> listaCat = usuariobd.consultarCategorias();
        
        // Ciclo para recorrer la lista de usuarios
        for(Categoria categoria : listaCat){
            
            // Guardar los objetos en un arreglo para usarlos en la tabla
            Object[] fila = {
                categoria.getId(),
                categoria.getCategoria(),
                categoria.getDescripcion(),
                categoria.getTotal()
                    
            };
            
            modelo.addRow(fila);
        }
        //Crear tabla
        JTable tablaUser = new JTable(modelo);
        // Usar JScrollpane para mostrar la tabla
        consultarCat.paneCategorias.setViewportView(tablaUser);
        
    }
    
    
    // Metodo para ir a la gestion de Usuarios
    private void irGestionUsuarios(){
        
        FrmGestionUsuarios ventanaGestion = new FrmGestionUsuarios();
        UsuarioBD usuariobd = new UsuarioBD();
        
        GestionUsuarioController controladorGestion =
                new GestionUsuarioController(ventanaGestion, usuariobd);
        
        ventanaGestion.setLocationRelativeTo(null);
        ventanaGestion.setVisible(true);
        
    }
    
    
    // Metodo para limpiar campos de Usuarios
    private void LimpiarCamposUser(){
        ventana.txtNombre.setText("");
        ventana.txtCorreo.setText("");
        ventana.spnEdad.setValue(0);
        ventana.txtPass.setText("");
        
        
    }
    
    
    // Metodo para limpiar campos de Categoria
    private void LimpiarCamposCat(){
        ingresarCategoria.TXT_Cat.setText("");
        ingresarCategoria.TXA_Desc.setText("");
        ingresarCategoria.TXT_Cant.setText("");
        
        
    }
}