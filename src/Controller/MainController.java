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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author Angel H
 */
public class MainController  implements ActionListener, MouseListener {
    
    private RegistrarController regcon;
    private CategoriasController catcon;
    
    private SplashFrm splash;
    private FnvrMainFrm mainForm;
    private FrmInicioSesion log;
    
    private FvnrHomePanel home;
    private FvnrMovimientosPanel movimientos;
    private FnvrRegismovPanel registrar;
    private FnvrCategoriasPanel categorias;
    private FnvrCatFrm cat2;
    private FnvrEditCatFrm cat3;
    private FnvrReporPanel reporte;
    
    private UsuarioBD usuariodb;
    private CategoriaDB categoriadb;
    private MovimientoDB movimientodb;
    private PresupuestoDB presupuestodb;
    private CategoriaPresupuestoDB catpredb;
    
    
    public int id_userLogeado;
    private String nombreLogeado;
    
    public MainController(FnvrMainFrm frm, FrmInicioSesion log, UsuarioBD usuariodb, MovimientoDB movimientodb, PresupuestoDB presupuestodb,
                            CategoriaDB categoriadb){
        //DB
        this.catpredb = new CategoriaPresupuestoDB();
        
        //Ventanas
        this.mainForm = frm;
        this.log = log;
        
        //ModelosDB
        this.usuariodb = usuariodb;
        this.categoriadb = categoriadb;
        this.movimientodb = movimientodb;
        this.presupuestodb =  presupuestodb;
        
        //Paneles
        this.home =  new FvnrHomePanel();
        this.movimientos =  new FvnrMovimientosPanel();
        this.registrar = registrar = new FnvrRegismovPanel();
        this.categorias =  new FnvrCategoriasPanel();
        this.cat2 =  new FnvrCatFrm(mainForm, true);
        this.cat3 =  new FnvrEditCatFrm(mainForm, true);
        this.reporte = new FnvrReporPanel();
        
        //Controladores
        regcon  = new RegistrarController(registrar, movimientodb, this);
        catcon = new CategoriasController(cat2, cat3, categoriadb, this);
        
        //ActionListener
        mainForm.btnInicio.addMouseListener(this);
        mainForm.btnMovimiento.addMouseListener(this);
        mainForm.btnCateg.addMouseListener(this);
        mainForm.cerrarBtn.addMouseListener(this);
        mainForm.btnReport.addMouseListener(this);
        
        home.btnIngreso.addMouseListener(this);
        categorias.btnnNuevo.addMouseListener(this);
        
        
        log.btnIni.addActionListener(this);
        
        //Ejecuiones
        iniciarApp();
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { 
        if (SwingUtilities.isLeftMouseButton(e)) {
            
            //Botones del Main
            if(e.getSource() == this.mainForm.btnInicio){
                gotoInicio();
            }
            if(e.getSource() == this.mainForm.btnMovimiento){
                gotoMovimientos();
            }
            if(e.getSource() == this.mainForm.btnCateg){
                gotoCategorias();
            }
            if(e.getSource() == this.mainForm.cerrarBtn){
                this.mainForm.dispose();
                iniciarApp();
            }
            if(e.getSource() == this.mainForm.btnReport){
                gotoReporte();
            }
            
            //Botones de paneles
            if(e.getSource() == home.btnIngreso){
                gotoRegistrar();
            }
            if(e.getSource() ==  categorias.btnnNuevo){
                cat2.setVisible(true);
            }
            if(e.getSource() == movimientos.btnNuevo){
                gotoRegistrar();
            }
            
        }
    }
    
    private void Splash(int time, String msg, JFrame frm){
        splash = new SplashFrm(msg);
        splash.setVisible(true);
        new Thread(() -> {
        try {
            Thread.sleep(time);

            java.awt.EventQueue.invokeLater(() -> {
                frm.setVisible(true);
                splash.dispose();
            });

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }).start();
    }
    
    private void iniciarMainFrm(){
        gotoInicio();
        mainForm.setVisible(true);
        gotoInicio();
    }
    
    private boolean iniciarApp(){
        boolean ret = false;
        
        log.setVisible(true);
        
        return ret;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Navegacion del MainFrm"> 
    
    private void gotoInicio(){
        this.mainForm.contPanel.removeAll();
        
        home.setSize(this.mainForm.contPanel.getSize());
        home.setPreferredSize(this.mainForm.contPanel.getSize());
        home.revalidate();
        home.setLocation(0, 0);
        
        Presupuesto prep = presupuestodb.obtenerPresupuestoActivo(id_userLogeado);
        double totalGasto = presupuestodb.obtenerTotalGastado(id_userLogeado);
        double totalPresup = prep.getMonto_limite_periodo(); 
        int porcent = (int)((totalGasto*100)/totalPresup);
        System.out.println(porcent);
        
        home.ProgresoBar.setAPorcent(porcent); //Porcentaje de la barra 0 - 100 (gasto*100/presupuesto)
        home.gastoTotalLbl.setText(Double.toString(totalGasto)); //Variable que obtenga el valor de la suma de lo que se ha gastado
        home.presupTotalLbl.setText(Double.toString(totalPresup)); //Varialbe que obtenga el presupuesto inicial
        
        home.listMov.removeAll();
        
        int limite = 5;
        int cont = 0;
        
        List<Movimiento> listaMov = movimientodb.consultarMovimientos(id_userLogeado);
        for(Movimiento mov : listaMov){
            
            if(cont >= limite){
                break;
            }
            
            MovimientoCardSlim c = new MovimientoCardSlim(mov.getCategoria(), mov.getMonto(), mov.getTipo_movimiento());
            
            
            //Agregar el card de movimiento
            home.listMov.add(c);
            cont++;
        }
        
        this.home.labelSaludo.setText("Buenos dias " + this.nombreLogeado.trim().split("\\s+")[0]);
        
        this.mainForm.contPanel.setLayout(new BorderLayout());
        this.mainForm.contPanel.removeAll();
        this.mainForm.contPanel.add(home, BorderLayout.CENTER);
        this.mainForm.contPanel.revalidate(); 
        this.mainForm.contPanel.repaint(); 
    }
    
    public void gotoMovimientos(){
        
        movimientos.setSize(this.mainForm.contPanel.getSize());
        movimientos.setPreferredSize(this.mainForm.contPanel.getSize());
        movimientos.revalidate();
        movimientos.setLocation(0, 0);
       
        movimientos.listMovimientos.removeAll();
        
        List<Movimiento> listaMov = movimientodb.consultarMovimientos(id_userLogeado);
        for(Movimiento mov : listaMov){
            MovimientoCard c = new MovimientoCard(mov.getCategoria(), mov.getMonto(), mov.getFecha(), mov.getTipo_movimiento());
            
            //Separador de 18 pixeles
            JPanel s =new JPanel();
            s.setSize(5, 18);
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
    
    private void gotoRegistrar(){
        
        registrar.setSize(this.mainForm.contPanel.getSize());
        registrar.setPreferredSize(this.mainForm.contPanel.getSize());
        registrar.revalidate();
        registrar.setLocation(0, 0);
        
        registrar.setIdUsr(id_userLogeado);
        
        //Crear lista de las categorias para el combo box
        DefaultComboBoxModel<ItemCombo> model = new DefaultComboBoxModel<>();
        
        registrar.cbCateg.removeAllItems();
        List<Categoria> listaCat = categoriadb.obtenerCategorias(id_userLogeado);
        for(Categoria cat : listaCat){
            model.addElement(new ItemCombo(cat.getId(), cat.getNombre()));
        }
        
        registrar.getCmbUsuarios().setModel(model);
        
        //registrar.cbCateg.get
       
        
        this.mainForm.contPanel.setLayout(new BorderLayout());
        this.mainForm.contPanel.removeAll();
        this.mainForm.contPanel.add(registrar, BorderLayout.CENTER);
        this.mainForm.contPanel.revalidate(); 
        this.mainForm.contPanel.repaint(); 
    }
    
    public void gotoCategorias(){
        
        categorias.setSize(this.mainForm.contPanel.getSize());
        categorias.setPreferredSize(this.mainForm.contPanel.getSize());
        categorias.revalidate();
        categorias.setLocation(0, 0);
       
        categorias.listCateg.removeAll();
        
        List<Categoria> listaCat = categoriadb.obtenerCategorias(id_userLogeado);
        for(Categoria cate : listaCat){
            CategCard c = new CategCard(cate.getId(), cate.getNombre(), cate.getDescripcion(), cat3);
            
            //Separador de 18 pixeles
            JPanel s =new JPanel();
            s.setSize(5, 18);
            s.setMaximumSize(s.getSize());
            
            //Agregar el card de movimiento
            categorias.listCateg.add(c);
            categorias.listCateg.add(s);
        }

        this.mainForm.contPanel.setLayout(new BorderLayout());
        this.mainForm.contPanel.removeAll();
        this.mainForm.contPanel.add(categorias, BorderLayout.CENTER);
        this.mainForm.contPanel.revalidate(); 
        this.mainForm.contPanel.repaint(); 
    }
    
    public void gotoReporte(){
        
        reporte.setSize(this.mainForm.contPanel.getSize());
        reporte.setPreferredSize(this.mainForm.contPanel.getSize());
        reporte.revalidate();
        reporte.setLocation(0, 0);
        
        
        Presupuesto prep = presupuestodb.obtenerPresupuestoActivo(id_userLogeado);
        double totalGasto = presupuestodb.obtenerTotalGastado(id_userLogeado);
        double totalPresup = prep.getMonto_limite_periodo(); 
        int porcent = (int)((totalGasto*100)/totalPresup);
        System.out.println(porcent);
        
        reporte.barGeneral.setPorcentaje(porcent);
        reporte.gastoTotalLbl.setText(Double.toString(totalGasto)); //Variable que obtenga el valor de la suma de lo que se ha gastado
        reporte.presupTotalLbl.setText(Double.toString(totalPresup)); //Varialbe que obtenga el presupuesto inicial
        
        reporte.CategCont.removeAll();
        
        List<CategoriaPresupuesto> listaCat = catpredb.obtenerCategorias(id_userLogeado);
        for(CategoriaPresupuesto cate : listaCat){
            CardRepCat c = new CardRepCat();
            c.setCategoria(cate.getNombrecat());
            totalGasto = cate.getMonto_gasto();
            totalPresup = cate.getMonto_lim();
            porcent = (int)((totalGasto*100)/totalPresup);
            c.lblNomCat1.setText("Gastaste $" + Double.toString(totalGasto) + " de $" + Double.toString(totalPresup));
            c.setPorcentaje(porcent);
            
            //Agregar el card de movimiento
                reporte.CategCont.add(c);

                reporte.CategCont.revalidate();
                reporte.CategCont.repaint();
        }

        
        
      


       
       
        this.mainForm.contPanel.setLayout(new BorderLayout());
        this.mainForm.contPanel.removeAll();
        this.mainForm.contPanel.add(reporte, BorderLayout.CENTER);
        this.mainForm.contPanel.revalidate(); 
        this.mainForm.contPanel.repaint(); 
    }
   
    
    
    // </editor-fold>    

    

    @Override
    public void actionPerformed(ActionEvent e) { 
        if(e.getSource() == log.btnIni){
            validarAccesoLogin();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Logica de Login"> 
    
    private void validarAccesoLogin() {
        String usuario = log.txtUsuario.getText().trim();
        String pass = new String (log.txtPass.getPassword()).trim();

        // Validar campos vacíos
        if (usuario.isEmpty() || pass.isEmpty()) {
            if(usuario.isEmpty())
            JOptionPane.showMessageDialog(log, "Introduzca Usuario.", 
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            if(pass.isEmpty())
            JOptionPane.showMessageDialog(log, "Introduzca Contraseña.", 
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Models.Usuario usuarioValidado = this.usuariodb.login(usuario, pass);

        if (usuarioValidado != null) {
            
            SesionAct.setUsuarioActual(usuarioValidado);
            JOptionPane.showMessageDialog(log, "¡Bienvenido de nuevo, " + usuarioValidado.getUsuario()+ "!");
            
            this.id_userLogeado = usuarioValidado.getId();
            this.nombreLogeado =  usuarioValidado.getUsuario();
            
            log.dispose();
            iniciarMainFrm();
        }else{
            JOptionPane.showMessageDialog(log, "Datos de inicio de sesión inválidos.", "", JOptionPane.ERROR_MESSAGE);
        }
    }
    // </editor-fold>  
    
    
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
