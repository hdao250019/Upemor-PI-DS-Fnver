package Controller;

import Models.UsuarioBD;
import Views.MainFrm;
import Views.FrmNuevoUsuario;
import Views.FrmConsultarUsuarios;
import Views.FrmIngresarCategoria;
import Views.FrmConsultarCategorias;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controlador exclusivo para gestionar el menú principal (MainFrm) 
 * y abrir de forma correcta las ventanas secundarias.
 * * @author Jared
 */
public class MenuController {
    
    private MainFrm menu;
    private UsuarioBD usuariobd;

    // El constructor recibe la vista del menú principal y la conexión a la base de datos
    public MenuController(MainFrm menu, UsuarioBD usuariobd) {
        this.menu = menu;
        this.usuariobd = usuariobd;
        
        // Creamos un único adaptador de mouse que controle todos los paneles del menú
        MouseAdapter manejadorClicks = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object origen = e.getSource();
                
                // Evalúa cuál panel (o componente interno público) recibió el clic
                if (origen == menu.menuAgregar) {
                    abrirCrearCuenta();
                } else if (origen == menu.menuConsultas) {
                    abrirMostrarUsuarios();
                } else if (origen == menu.MenuIngresarCategorias) {
                    abrirIngresarCategoria();
                } else if (origen == menu.menuMostrarCat) {
                    abrirMostrarCategorias();
                }
            }
        };
        
        // Vinculamos el manejador a cada uno de tus paneles del menú
        this.menu.menuAgregar.addMouseListener(manejadorClicks);
        this.menu.menuConsultas.addMouseListener(manejadorClicks);
        this.menu.MenuIngresarCategorias.addMouseListener(manejadorClicks);
        this.menu.menuMostrarCat.addMouseListener(manejadorClicks);
    }
    
    // Métodos independientes para instanciar correctamente cada ventana con su UserController dedicado
    private void abrirCrearCuenta() {
        FrmNuevoUsuario ventanaNueva = new FrmNuevoUsuario();
        // Inicializa el UserController pasándole la ventana correspondiente
        new UserController(ventanaNueva, usuariobd, null, null, null);
        ventanaNueva.setLocationRelativeTo(null);
        ventanaNueva.setVisible(true);
    }
    
    private void abrirMostrarUsuarios() {
        FrmConsultarUsuarios verUsuariosNuevos = new FrmConsultarUsuarios();
        // Inicializa el UserController pasándole la ventana de consulta de usuarios
        new UserController(null, usuariobd, verUsuariosNuevos, null, null);
        verUsuariosNuevos.setLocationRelativeTo(null);
        verUsuariosNuevos.setVisible(true);
    }

    private void abrirIngresarCategoria() {
        FrmIngresarCategoria ingresarCat = new FrmIngresarCategoria();
        // Inicializa el UserController pasándole la ventana de ingreso de categorías
        new UserController(null, usuariobd, null, ingresarCat, null);
        ingresarCat.setLocationRelativeTo(null);
        ingresarCat.setVisible(true);
    }
    
    private void abrirMostrarCategorias() {
        FrmConsultarCategorias verCat = new FrmConsultarCategorias();
        // Inicializa el UserController pasándole la ventana de consulta de categorías
        new UserController(null, usuariobd, null, null, verCat);
        verCat.setLocationRelativeTo(null);
        verCat.setVisible(true);
    }
}