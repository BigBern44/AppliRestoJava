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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.DaoUtilisateur;

import modele.metier.Utilisateur;
import vue.VueInscription;
import vue.VueStart;

/**
 *
 * @author 33652
 */
public class CtrlRegister extends ControleurGenerique implements ActionListener, WindowListener {
 
     public CtrlRegister(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueInscription();

        vue.addWindowListener(this);
        getVue().getInscriptionButton().addActionListener(this);
        getVue().getRetourButton().addActionListener(this);
        
    }
    
    

    
    public VueInscription getVue() {
        return (VueInscription) vue;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getVue().getInscriptionButton())){
            String pwd = new String(getVue().getjPasswordFieldMDP().getPassword());
            if (((getVue().getjTextFieldLogin().getText()).equals(""))|| ((getVue().getjTextFieldEmail().getText()).equals("")) || (pwd.equals(""))){
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Veuillez renseigner tous les champs", "Attention", JOptionPane.WARNING_MESSAGE);
                
            } else {           
                try {
                    if (!DaoUtilisateur.verifIfExist(getVue().getjTextFieldEmail().getText())) {
                        
                        try {
                            DaoUtilisateur.registerUser(new Utilisateur(getVue().getjTextFieldEmail().getText(),getVue().getjTextFieldLogin().getText(),pwd));
                        } catch (Exception ex) {
                            Logger.getLogger(CtrlRegister.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        getVue().dispose();
                        
                    } else {
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "Adresse email deja utilisé", "Attention", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(CtrlRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
                
              
            
            }

        }else{
             if (e.getSource().equals(getVue().getRetourButton())){
                getVue().dispose();
             }
             
        }
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
