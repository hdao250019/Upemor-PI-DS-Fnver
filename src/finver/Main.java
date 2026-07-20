package finver;

import Controller.GestionCatController;
import Controller.GestionUsuarioController;
import Controller.LoginController;
import Controller.MenuController;
import Controller.UserController;
import Models.Usuario;
import Views.FrmConsultarUsuarios;
import Views.FrmNuevoUsuario;
import Views.SplashFrm;
import Models.UsuarioBD;
import Views.FrmConsultarCategorias;
import Views.FrmGestionUsuarios;
import Views.FrmGestionarCate;
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
        
        //inicio.setLocationRelativeTo(null);
        //inicio.setVisible(true);
        
        FrmConsultarUsuarios verUsuarios = new FrmConsultarUsuarios();
        FrmIngresarCategoria ingresarCategoria = new FrmIngresarCategoria();
        FrmConsultarCategorias consultarCat = new FrmConsultarCategorias();
        FrmGestionUsuarios GesUs = new FrmGestionUsuarios();
        FrmGestionarCate gesCat = new FrmGestionarCate();
        Usuario usuarioLogeado = new Usuario();
        MainFrm menu = new MainFrm();
        
        LoginController control = new LoginController(loginView, usuariobd, menu);
        
        MenuController controlmenu = new MenuController(menu, usuariobd);
        GestionUsuarioController controlGestionUs = new GestionUsuarioController(GesUs, usuariobd);
        GestionCatController controlCat = new GestionCatController(gesCat, usuariobd);
        
        UserController controlador = new UserController(ventana, usuariobd, verUsuarios, ingresarCategoria, consultarCat,usuarioLogeado);
        //inicio.dispose();

    }
  
}

