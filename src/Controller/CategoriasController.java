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

public class CategoriasController implements ActionListener, MouseListener {
    private FnvrCatFrm frm;
    private FnvrEditCatFrm edit;
    private CategoriaDB catdb;
    private MainController home;

    public CategoriasController(FnvrCatFrm frm, FnvrEditCatFrm edit, CategoriaDB catdb, MainController home) {
        this.frm = frm;
        this.catdb = catdb;
        this.home = home;
        this.edit = edit;
        
        frm.btnGuardarCat.addMouseListener(this);
        edit.btnActualizarCat.addMouseListener(this);
        edit.btnEliminarCat.addMouseListener(this);
        System.out.println("ControladorCateg cargado");
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if(e.getSource() ==  this.edit.btnActualizarCat){
           System.out.println("Hubo click actualizar");
           actualizarCategoria();
       }
       if(e.getSource() == this.frm.btnGuardarCat){
           System.out.println("Hubo clik guardad");
            insertarCategoria();
        }
       if(e.getSource() ==  this.edit.btnEliminarCat){
           eliminarCategoria();
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
    
    public void insertarCategoria(){
        Categoria cat = new Categoria(frm.TXT_NOMBRE.getText(), frm.TXTA_DESCRIP.getText(), home.id_userLogeado);
        if(catdb.insertar(cat)){
            JOptionPane.showMessageDialog(frm, "Categoría registrada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
            frm.TXTA_DESCRIP.setText("");
            frm.TXT_NOMBRE.setText("");
            frm.setVisible(false);
            home.gotoCategorias();
            
        }
        else JOptionPane.showMessageDialog(frm, "A ocurrido un error vuelva a intentar", "", JOptionPane.ERROR_MESSAGE);
    }
    
    public void actualizarCategoria(){
        System.out.println("ID categoria: " + edit.getId_cat());
        System.out.println(edit.TXT_NOMBRE.getText());
        System.out.println(home.id_userLogeado);
        
        Categoria cat = new Categoria(edit.getId_cat(), edit.TXT_NOMBRE.getText(), edit.TXTA_DESCRIP.getText(), home.id_userLogeado);
        if(catdb.actualizarCat(cat)){
            JOptionPane.showMessageDialog(edit, "Categoría actualizada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
            edit.setVisible(false);
            home.gotoCategorias();
            
        }
        else JOptionPane.showMessageDialog(frm, "A ocurrido un error vuelva a intentar", "", JOptionPane.ERROR_MESSAGE);
    }
    
    public void eliminarCategoria(){
        
        if(JOptionPane.showConfirmDialog(edit, "¿Esta seguro que desea realizar esta acción?", "", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
             if(catdb.eliminarCat(edit.getId_cat())){
            JOptionPane.showMessageDialog(edit, "Categoría eliminada con éxito.", "", JOptionPane.INFORMATION_MESSAGE);
            edit.setVisible(false);
            home.gotoCategorias();
            
            }
            else JOptionPane.showMessageDialog(frm, "A ocurrido un error vuelva a intentar", "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
}
