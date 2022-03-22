/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import vue.VueStart;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;

/**
 *
 * @author 33652
 */
public class CtrlStart extends ControleurGenerique implements ActionListener, WindowListener {
     public CtrlStart(CtrlPrincipal ctrlPrincipal) {
         
        super(ctrlPrincipal);
        
        vue = new VueStart();
        vue.addWindowListener(this);
        
        getVue().getInscriptionButton().addActionListener(this);
        getVue().getConnexionButton().addActionListener(this);
    }
     
     public void goToInscription(){
         
          this.getCtrlPrincipal().action(EnumAction.INSCRIPTION_PAGE);
     }
     public void goToConnexion(){
         
         this.getCtrlPrincipal().action(EnumAction.CONNEXION);
         
     }
     
     
    public VueStart getVue() {
        return (VueStart) vue;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getVue().getInscriptionButton())){
            goToInscription();
        }else{
             if (e.getSource().equals(getVue().getConnexionButton())){
                 goToConnexion();
             }
             
        }
    }
     
      public void Inscription() {
        this.getCtrlPrincipal().action(EnumAction.INSCRIPTION_PAGE);
    }
 @Override
    public void windowOpened(WindowEvent e) {}
  
    @Override
    public void windowClosing(WindowEvent e) {

    }
    
    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
 
    
    
}
