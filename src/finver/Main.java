package finver;

import Controller.MenuController;
import Controller.UserController;
import Views.FrmConsultarUsuarios;
import Views.FrmNuevoUsuario;
import Views.SplashFrm;
import Models.UsuarioBD;
import Views.FrmConsultarCategorias;
import Views.FrmIngresarCategoria;
import Views.MainFrm;
/**
 *
 * @author rousc
 */


public class Main {
     public static void main(String[] args) {
        FrmNuevoUsuario ventana = new FrmNuevoUsuario();
        UsuarioBD usuariobd = new UsuarioBD();
        SplashFrm inicio = new SplashFrm();
        MainFrm menu = new MainFrm();
        FrmConsultarUsuarios verUsuarios = new FrmConsultarUsuarios();
        FrmIngresarCategoria ingresarCategoria = new FrmIngresarCategoria();
        FrmConsultarCategorias consultarCat = new FrmConsultarCategorias();
        
        MenuController controlmenu = new MenuController(menu, usuariobd);
        UserController controlador = new UserController(ventana, usuariobd, verUsuarios, ingresarCategoria, consultarCat);
        
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }
  
}

