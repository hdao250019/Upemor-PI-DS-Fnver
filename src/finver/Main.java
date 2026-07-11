package finver;

import Controller.GestionUsuarioController;
import Controller.LoginController;
import Controller.MenuController;
import Controller.UserController;
import Views.FrmConsultarUsuarios;
import Views.FrmNuevoUsuario;
import Views.SplashFrm;
import Models.UsuarioBD;
import Views.FrmConsultarCategorias;
import Views.FrmGestionUsuarios;
import Views.FrmIngresarCategoria;
import Views.FrmInicioSesion;
import Views.MainFrm;
/**
 *
 * @author rousc
 */


public class Main {
     public static void main(String[] args) {
        FrmInicioSesion loginView = new FrmInicioSesion();
        FrmNuevoUsuario ventana = new FrmNuevoUsuario();
        UsuarioBD usuariobd = new UsuarioBD();
        SplashFrm inicio = new SplashFrm(loginView);
        MainFrm menu = new MainFrm();
        FrmConsultarUsuarios verUsuarios = new FrmConsultarUsuarios();
        FrmIngresarCategoria ingresarCategoria = new FrmIngresarCategoria();
        FrmConsultarCategorias consultarCat = new FrmConsultarCategorias();
        FrmGestionUsuarios GesUs = new FrmGestionUsuarios();
        
        
        LoginController control = new LoginController(loginView, usuariobd, menu);
        GestionUsuarioController controlGestion = new GestionUsuarioController(GesUs, usuariobd);
        MenuController controlmenu = new MenuController(menu, usuariobd);
        
        UserController controlador = new UserController(ventana, usuariobd, verUsuarios, ingresarCategoria, consultarCat, GesUs);
        
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }
  
}

