/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.DaoUtilisateur;
import vue.VueConnexion;



/**
 *
 * @author 33652
 */
public class CtrlLogin extends ControleurGenerique implements ActionListener, WindowListener{
    
    public CtrlLogin(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueConnexion();

        vue.addWindowListener(this);
        getVue().getjButtonConnexion().addActionListener(this);
        getVue().getjButtonRetour().addActionListener(this);
        
    }

    
    
    public VueConnexion getVue() {
        return (VueConnexion) vue;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource().equals(getVue().getjButtonConnexion())){
            String pwd = new String(getVue().getjPasswordFieldMdp().getPassword());
            if (((getVue().getjTextFieldEmail().getText()).equals("")) || (pwd.equals(""))){
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Veuillez renseigner tous les champs", "Attention", JOptionPane.WARNING_MESSAGE);
                
            } else {
                   
                try {
                    if(DaoUtilisateur.loginUser(getVue().getjTextFieldEmail().getText(), pwd)){
                        this.getCtrlPrincipal().action(EnumAction.COMMENTAIRE);
                        getVue().dispose();
                        
                    }else{
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "Les identifiants sont incorrects", "Attention", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CtrlLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
                    
              
              
            
            }

        }
        if (e.getSource().equals(getVue().getjButtonRetour())){
                getVue().dispose();
         }
             
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
       
    }

    @Override
    public void windowClosing(WindowEvent e) {
       
    }

    @Override
    public void windowClosed(WindowEvent e) {
     
    }

    @Override
    public void windowIconified(WindowEvent e) {
       
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
       
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
       
    }
}
