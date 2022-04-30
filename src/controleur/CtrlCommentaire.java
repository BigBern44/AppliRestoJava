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
import modele.dao.ExceptionDAO;
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
    
    public CtrlCommentaire(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueCommentaire();
        commentairesAfficher();
        vue.addWindowListener(this);
        getVue().getjTableCommentaire().addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                int r = getVue().getjTableCommentaire().rowAtPoint(e.getPoint());
                if (r >= 0 && r < getVue().getjTableCommentaire().getRowCount()) {
                    getVue().getjTableCommentaire().setRowSelectionInterval(r, r);
                } else {
                    getVue().getjTableCommentaire().clearSelection();
                }

                int rowindex = getVue().getjTableCommentaire().getSelectedRow();
                if (rowindex < 0)
                    return;
                
            }
        });
        
        
    }
    
   
    
    public VueCommentaire getVue() {
        return (VueCommentaire) vue;
    }
    
     
    
     public void commentairesAfficher() {
        try {
            String msg = ""; // message à afficher en cas d'erreur
            getVue().getModeleTableCommentaire().setRowCount(0);
            
            String[] titresColonnes = {"IDR", "NOTE", "COMMENTAIRE","NOM UTILISATEUR"};
            
            getVue().getModeleTableCommentaire().setColumnIdentifiers(titresColonnes);
            
            String[] ligneDonnees = new String[4];
            lesCommentaires = (ArrayList<Commentaire>) DaoCommentaire.selectAll();
            for (Commentaire unCommentaire : lesCommentaires) {
                ligneDonnees[0] = Integer.toString(unCommentaire.getIdR());
                ligneDonnees[1] = Integer.toString(unCommentaire.getNote());
                ligneDonnees[2] = unCommentaire.getCommentaire();
                ligneDonnees[3] = DaoUtilisateur.getUserById(unCommentaire.getIdu()).getLogin();
                
                getVue().getModeleTableCommentaire().addRow(ligneDonnees);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CtrlCommentaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
