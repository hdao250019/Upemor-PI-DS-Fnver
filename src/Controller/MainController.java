/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


import Views.*;
import Models.*;
import FinverUI.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author Angel H
 */
public class MainController  implements ActionListener, MouseListener {
    
    private FnvrMainFrm mainForm;
    private FvnrHomePanel home;
    private FvnrMovimientosPanel movimientos;
    private MovimientoDB movimientodb;
    
    public MainController(FnvrMainFrm frm, MovimientoDB movimientodb){
        this.mainForm = frm;
        this.home =  new FvnrHomePanel();
        this.movimientodb = movimientodb;
        
        this.movimientos =  new FvnrMovimientosPanel();
        mainForm.btnInicio.addMouseListener(this);
        mainForm.btnMovimiento.addMouseListener(this);
        
         gotoInicio();
        mainForm.setVisible(true);
        gotoInicio();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { 
        if (SwingUtilities.isLeftMouseButton(e)) {
            if(e.getSource() == this.mainForm.btnInicio){
                gotoInicio();
            }
            if(e.getSource() == this.mainForm.btnMovimiento){
                gotoMovimientos();
            }
        }
    }
    
    private void gotoInicio(){
        this.mainForm.contPanel.removeAll();
        
        home.setSize(this.mainForm.contPanel.getSize());
        home.setPreferredSize(this.mainForm.contPanel.getSize());
        home.revalidate();
        home.setLocation(0, 0);
        
        home.ProgresoBar.setAPorcent(50); //Porcentaje de la barra 0 - 100 (gasto*100/presupuesto)
        home.gastoTotalLbl.setText("$1200"); //Variable que obtenga el valor de la suma de lo que se ha gastado
        home.presupTotalLbl.setText("$2100"); //Varialbe que obtenga el presupuesto inicial
        
        home.listMov.removeAll();
        
        int limite = 3;
        int cont = 0;
        
        List<Movimiento> listaMov = movimientodb.consultarMovimientos();
        for(Movimiento mov : listaMov){
            
            if(cont >= limite){
                break;
            }
            
            MovimientoCardSlim c = new MovimientoCardSlim(mov.getId_categoria(), mov.getMonto(), mov.getTipo_movimiento());
            
            //Agregar el card de movimiento
            home.listMov.add(c);
            cont++;
        }
        
        this.mainForm.contPanel.setLayout(new BorderLayout());
        this.mainForm.contPanel.removeAll();
        this.mainForm.contPanel.add(home, BorderLayout.CENTER);
        this.mainForm.contPanel.revalidate(); 
        this.mainForm.contPanel.repaint(); 
    }
    
    private void gotoMovimientos(){
        
        movimientos.setSize(this.mainForm.contPanel.getSize());
        movimientos.setPreferredSize(this.mainForm.contPanel.getSize());
        movimientos.revalidate();
        movimientos.setLocation(0, 0);
       
        movimientos.listMovimientos.removeAll();
        
        List<Movimiento> listaMov = movimientodb.consultarMovimientos();
        for(Movimiento mov : listaMov){
            MovimientoCard c = new MovimientoCard(mov.getId_categoria(), mov.getMonto(), mov.getFecha(), mov.getTipo_movimiento());
            
            //Separador de 10 pixeles
            JPanel s =new JPanel();
            s.setSize(5, 10);
            s.setMaximumSize(s.getSize());
            
            //Agregar el card de movimiento
            movimientos.listMovimientos.add(c);
            movimientos.listMovimientos.add(s);
        }
        
        
        this.mainForm.contPanel.setLayout(new BorderLayout());
        this.mainForm.contPanel.removeAll();
        this.mainForm.contPanel.add(movimientos, BorderLayout.CENTER);
        this.mainForm.contPanel.revalidate(); 
        this.mainForm.contPanel.repaint(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) { }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
