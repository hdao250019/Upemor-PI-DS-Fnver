package Controller;

import Models.UsuarioBD;
import Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements ActionListener, MouseListener {
    
    private MainFrm menu;
    private UsuarioBD usuariobd;

    public MenuController(MainFrm menu, UsuarioBD usuariobd) {
        this.menu = menu;
        this.usuariobd = usuariobd;
        
        // PANELES: Se escuchan con MouseListener
        this.menu.menuAgregar.addMouseListener(this);
        this.menu.menuConsultas.addMouseListener(this);
        this.menu.menuMostrarCat.addMouseListener(this);
        
        // BOTÓN: Se escucha con ActionListener
        this.menu.MenuIngresarCategorias.addActionListener(this);
    }
    
    // 1. CAPTURA EXCLUSIVA PARA EL BOTÓN REAL
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menu.MenuIngresarCategorias) {
            irIngresarCategoria();
        }
    }
    
    // 2. CAPTURA EXCLUSIVA PARA LOS PANELES-BOTÓN
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.menu != null && this.menu.menuAgregar != null) {
            if (e.getSource() == this.menu.menuAgregar) {
                irCrearCuenta();
            }
        }
        
        if (this.menu != null && this.menu.menuConsultas != null) {
            if (e.getSource() == this.menu.menuConsultas) {
                irMostrarUsuarios();
            }
        }
        
        if (this.menu != null && this.menu.menuMostrarCat != null) {
            if (e.getSource() == this.menu.menuMostrarCat) {
                irMostrarCategorias();
            }
        }
    }
    
    // Métodos obligatorios de MouseListener (vacíos si no los usas)
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
    
     private void irCrearCuenta(){
        FrmNuevoUsuario NewUsuario = new FrmNuevoUsuario();
        UsuarioBD usuariobd = new UsuarioBD();
        
        UserController miControlador = new UserController(NewUsuario, usuariobd, null, null, null);
        
        NewUsuario.setLocationRelativeTo(null);
        NewUsuario.setVisible(true);
        
    }
    
    // Metodo para ir a mostrar usuarios
    private void irMostrarUsuarios(){
        FrmConsultarUsuarios verUsuarios = new FrmConsultarUsuarios();
        UsuarioBD usuariobd = new UsuarioBD();
        
        UserController miControlador = new UserController(null, usuariobd, verUsuarios, null, null);
        
        verUsuarios.setLocationRelativeTo(null);
        verUsuarios.setVisible(true);
        
    }

    // Método para ir a ingresar categorías
    private void irIngresarCategoria() {
       FrmIngresarCategoria vercat = new FrmIngresarCategoria();
        UsuarioBD usuariobd = new UsuarioBD();
        
        UserController miControlador = new UserController(null, usuariobd, null, vercat, null);
        
        vercat.setLocationRelativeTo(null);
        vercat.setVisible(true);
    }
    
    // AGREGADO: Método para ir a mostrar categorías
    private void irMostrarCategorias() {
        FrmConsultarCategorias ConsCat = new FrmConsultarCategorias();
        UsuarioBD usuariobd = new UsuarioBD();
        
        UserController miControlador = new UserController(null, usuariobd, null, null, ConsCat);
        
        ConsCat.setLocationRelativeTo(null);
        ConsCat.setVisible(true);
    }
}