package finver;

import Controller.UserController;
import Views.FrmConsultarUsuarios;
import Views.FrmNuevoUsuario;
import Views.SplashFrm;
import Models.UsuarioBD;
import Views.FrmIngresarDinero;

/**
 *
 * @author rousc
 */
public class Main {
     public static void main(String[] args) {
        FrmNuevoUsuario ventana = new FrmNuevoUsuario();
        UsuarioBD usuariobd = new UsuarioBD();
        SplashFrm inicio = new SplashFrm();
        FrmConsultarUsuarios verUsuarios = new FrmConsultarUsuarios();
        FrmIngresarDinero ingresarDinero = new FrmIngresarDinero();
        
        UserController controlador = new UserController(ventana, usuariobd, verUsuarios, ingresarDinero);
        
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }
  
}

