/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionListener;
import modele.dao.DaoCommentaire;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoException;
import modele.metier.Commentaire;
import vue.VueCommentaire;
import javax.swing.JTable;


/**
 *
 * @author 33652
 */
public class CtrlCommentaire  extends ControleurGenerique implements ActionListener, WindowListener {
    private final Commentaire Commentaire = new Commentaire();
    private ArrayList<Commentaire> lesCommentaires;
    String idR = null;
    String idU = null;
    
    
    
    public CtrlCommentaire(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueCommentaire();
        commentairesAfficher();
        vue.addWindowListener(this);
        getVue().getjButtonSupprimer().addActionListener(this);
        getVue().getjButtonMasquer().addActionListener(this);
        getVue().getjButtonRetour().addActionListener(this);
        getVue().getJRadioButtonMasquer().addActionListener(this);
        
        
        getVue().getjTableCommentaire().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int r = getVue().getjTableCommentaire().rowAtPoint(e.getPoint());
                if (r >= 0 && r < getVue().getjTableCommentaire().getRowCount()) {
                    getVue().getjTableCommentaire().setRowSelectionInterval(r, r);
                } else {
                    getVue().getjTableCommentaire().clearSelection();
                }

                int rowindex = getVue().getjTableCommentaire().getSelectedRow();
                
                setIdR(getVue().getjTableCommentaire().getModel().getValueAt(rowindex, 0).toString());
                
                setIdU(getVue().getjTableCommentaire().getModel().getValueAt(rowindex, 1).toString());
                
                
            }
        });
        
        
    }
    
   
    
    public VueCommentaire getVue() {
        return (VueCommentaire) vue;
    }
    
      
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(getVue().getjButtonRetour())){
            getVue().dispose();
       }
       
        if(e.getSource().equals(getVue().getjButtonSupprimer())){
           if((idR != null) && (idU != null)){
               try {
                   if(DaoCommentaire.SupprimerCommentaire(idR,idU)==1){
                       commentairesAfficher();
                       setIdR(null);
                       setIdU(null);   
                       JOptionPane jop1 = new JOptionPane();
                       jop1.showMessageDialog(null, "Le commentaire a bien été supprimé", "Réussi", JOptionPane.INFORMATION_MESSAGE);
                   }else{
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "pas supprimé", "Attention", JOptionPane.WARNING_MESSAGE);
                   }
               } catch (DaoException ex) {
                   Logger.getLogger(CtrlCommentaire.class.getName()).log(Level.SEVERE, null, ex);
               }
                
           }else{
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Vous n'avez pas choisi de commentaire", "Attention", JOptionPane.WARNING_MESSAGE);
           }
           
       }
        
       if (e.getSource().equals(getVue().getjButtonMasquer())){
           if((idR != null) && (idU != null)){
               try {
                   if(DaoCommentaire.MasquerCommentaire(idR,idU)==1){
                       commentairesAfficher();
                       setIdR(null);
                       setIdU(null);   
                       JOptionPane jop1 = new JOptionPane();
                       jop1.showMessageDialog(null, "Le commentaire a bien été masqué", "Réussi", JOptionPane.INFORMATION_MESSAGE);
                   }else{
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "pas masqué", "Attention", JOptionPane.WARNING_MESSAGE);
                   }
               } catch (DaoException ex) {
                   Logger.getLogger(CtrlCommentaire.class.getName()).log(Level.SEVERE, null, ex);
               }
                
           }else{
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Vous n'avez pas choisi de commentaire", "Attention", JOptionPane.WARNING_MESSAGE);
           }
           
           
       }
       
       if (e.getSource().equals(getVue().getJRadioButtonMasquer())){
           System.out.println("yo");
           commentairesAfficher();
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
     public void setIdR(String idR) {
        this.idR = idR;
    }

    public void setIdU(String idU) {
        this.idU = idU;
    }
    
    public void commentairesAfficher() {
        try {
            String msg = ""; // message Ã  afficher en cas d'erreur
            getVue().getModeleTableCommentaire().setRowCount(0);
            
            String[] titresColonnes = {"IDR","IDU", "NOTE", "COMMENTAIRE","NOM UTILISATEUR","VISIBILITE"};
            
            getVue().getModeleTableCommentaire().setColumnIdentifiers(titresColonnes);
            
            String[] ligneDonnees = new String[6];
            if (getVue().getJRadioButtonMasquer().isSelected()){
                lesCommentaires = (ArrayList<Commentaire>) DaoCommentaire.selectAllMasquer();
            }else{
                lesCommentaires = (ArrayList<Commentaire>) DaoCommentaire.selectAll();
            }
            
            
            
            for (Commentaire unCommentaire : lesCommentaires) {
                ligneDonnees[0] = Integer.toString(unCommentaire.getIdR());
                ligneDonnees[1] = Integer.toString(unCommentaire.getIdu());
                ligneDonnees[2] = Integer.toString(unCommentaire.getNote());
                ligneDonnees[3] = unCommentaire.getCommentaire();
                ligneDonnees[4] = DaoUtilisateur.getUserById(unCommentaire.getIdu()).getPseudoU();
                ligneDonnees[5] = unCommentaire.getMasquer();
                
                getVue().getModeleTableCommentaire().addRow(ligneDonnees);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CtrlCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
