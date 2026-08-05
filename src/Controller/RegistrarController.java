/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Views.*;
import Models.*;
import FinverUI.*;
import Tools.ItemCombo;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RegistrarController implements ActionListener, MouseListener {
    private FnvrRegismovPanel registrar;
    private MovimientoDB movi;
    private MainController main;
    
    public RegistrarController(FnvrRegismovPanel p, MovimientoDB movi, MainController parent){
        this.registrar = p;
        this.movi = movi;
        this.main = parent;
        
        registrar.btnGuardarMovi.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource() == this.registrar.btnGuardarMovi){
            insertarMovimiento();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    public void insertarMovimiento(){
        ItemCombo seleccionado = (ItemCombo) registrar.getCmbUsuarios().getSelectedItem();
        int idcateg = seleccionado.getId();
        
        Movimiento m = new Movimiento();
        m.setId_categoria(idcateg);
        m.setId_usuario(registrar.getIdUsr());
        m.setTipo_movimiento(registrar.cbTipo.getSelectedItem().toString());
        m.setMonto(Double.parseDouble(registrar.TXT_MONTO.getText()));
        m.setDescripcion(registrar.TXTA_DESCRIP.getText());
        
        if(movi.insertar(m)){
            JOptionPane.showMessageDialog(registrar.getParent(), "El movimiento Fue correctamente Registrado", "", JOptionPane.INFORMATION_MESSAGE);
            main.gotoMovimientos();
        }
        else JOptionPane.showMessageDialog(registrar.getParent(), "A ocurrido un error vuelva a intentar", "", JOptionPane.ERROR_MESSAGE);
        
    } 
    
}
