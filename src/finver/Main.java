package finver;

import Controller.*;
import Models.*;
import Views.*;
/**
 *
 * @author rousc
 */


public class Main {
     public static void main(String[] args) {
        /*FrmInicioSesion loginView = new FrmInicioSesion();
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
        //inicio.dispose();*/
        
        FnvrMainFrm main =  new FnvrMainFrm();
        FrmInicioSesion login =  new FrmInicioSesion();
        
        MovimientoDB movdb = new MovimientoDB();
        CategoriaDB catedb = new CategoriaDB();
        PresupuestoDB predb = new PresupuestoDB();
        UsuarioBD usrdb = new UsuarioBD();
        
        MainController mainCtr =  new MainController(main, login, usrdb, movdb, predb, catedb);
        

    }
  
}

