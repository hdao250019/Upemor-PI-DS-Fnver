package finver;

import Controller.UserController;
import Views.FrmConsultarUsuarios;
import Views.FrmNuevoUsuario;
import Views.SplashFrm;
import Models.UsuarioBD;

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
        
        UserController controlador = new UserController(ventana, usuariobd, verUsuarios);
        
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }
  
}

