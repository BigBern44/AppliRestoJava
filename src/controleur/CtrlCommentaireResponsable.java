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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionListener;
import modele.dao.DaoCommentaire;
import modele.dao.DaoUtilisateur;
import modele.dao.DaoException;
import modele.metier.Commentaire;
import java.util.Date;  
import javax.swing.JTable;
import vue.VueCommentaireResponsable;


/**
 *
 * @author 33652
 */

class CtrlCommentaireResponsable  extends ControleurGenerique implements ActionListener, WindowListener {
    private final Commentaire Commentaire = new Commentaire();
    private ArrayList<Commentaire> lesCommentaires;
    private ArrayList<Commentaire> lesCommentairesByDate;
    String idR = null;
    String idU = null;
    boolean filtreDate = false;
    java.util.Date dateChoisi = null;
    
    public CtrlCommentaireResponsable(CtrlPrincipal ctrlPrincipal) {
        
       
        super(ctrlPrincipal);
       
        
        vue = new VueCommentaireResponsable();
        vue.addWindowListener(this);
         
        commentairesAfficher();
        
        DateFormat dateFormat = new SimpleDateFormat("YYYY-dd-MM");  
            
        getVue().getjButtonSupprimer().addActionListener(this);
        getVue().getjButtonReinitialiser().addActionListener(this);
        getVue().getjButtonMasquer().addActionListener(this);
        getVue().getjButtonRepublier().addActionListener(this);
        getVue().getjButtonRetour().addActionListener(this);
        getVue().getJRadioButtonMasquer().addActionListener(this);
        
        getVue().getjCalendar().addPropertyChangeListener("calendar", new PropertyChangeListener() {
            
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        
        @Override
        public void propertyChange(PropertyChangeEvent e) {
           
                filtreDate=true;
                
                String formattedDate = dateFormat.format(getVue().getjCalendar().getDate());
                java.sql.Date dateSQL = java.sql.Date.valueOf(formattedDate);
                
                dateChoisi = dateSQL;
                commentairesAfficher();
            
        }
    });
        
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
    
   
    
    public VueCommentaireResponsable getVue() {
        return (VueCommentaireResponsable) vue;
    }
    
      
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(getVue().getjButtonReinitialiser())){
            filtreDate = false;

            commentairesAfficher();
        }
        
        if (e.getSource().equals(getVue().getjButtonRetour())){
            getVue().dispose();
        }
        
        if(e.getSource().equals(getVue().getjButtonRepublier())){
               if((idR != null) && (idU != null)){
               try {
                   if(DaoCommentaire.DemasquerCommentaire(idR,idU)==1){

                            commentairesAfficher();
               
                       setIdR(null);
                       setIdU(null);   
                       JOptionPane jop1 = new JOptionPane();
                       jop1.showMessageDialog(null, "Le commentaire a bien été republié", "Réussi", JOptionPane.INFORMATION_MESSAGE);
                   }else{
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "pas republié", "Attention", JOptionPane.WARNING_MESSAGE);
                   }
               } catch (DaoException ex) {
                   Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
               }
                
           }else{
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Vous n'avez pas choisi de commentaire", "Attention", JOptionPane.WARNING_MESSAGE);
           }
         }
        
        
        if(e.getSource().equals(getVue().getjButtonSupprimer())){
           if((idR != null) && (idU != null)){
               try {
                   if(DaoCommentaire.SupprimerCommentaire(idR,idU)==1){
                       
                            commentairesAfficher();
               
                           
                        setIdR(null);
                        setIdU(null);   
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "Le commentaire a bien �t� supprim�", "R�ussi", JOptionPane.INFORMATION_MESSAGE);
                   }else{
                        JOptionPane jop1 = new JOptionPane();
                        jop1.showMessageDialog(null, "pas supprim�", "Attention", JOptionPane.WARNING_MESSAGE);
                   }
               } catch (DaoException ex) {
                   Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
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
                   Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
               }
                
           }else{
                JOptionPane jop1 = new JOptionPane();
                jop1.showMessageDialog(null, "Vous n'avez pas choisi de commentaire", "Attention", JOptionPane.WARNING_MESSAGE);
           }
           
           
       }
       
       if (e.getSource().equals(getVue().getJRadioButtonMasquer())){
           
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
    
    public void commentairesAfficher()  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Commentaire> lesCommentairesAAfficher = new ArrayList<> ();
        
        if (filtreDate == true){
            
            try {
                lesCommentaires = (ArrayList<Commentaire>) DaoCommentaire.selectAllByDate((java.sql.Date) dateChoisi);
            } catch (Exception ex) {
                Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            try {
                lesCommentaires = (ArrayList<Commentaire>) DaoCommentaire.selectAll();
            } catch (Exception ex) {
                Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
        try {
            String msg = ""; // message à afficher en cas d'erreur
            getVue().getModeleTableCommentaire().setRowCount(0);
            
            String[] titresColonnes = {"IDR","IDU", "NOTE", "COMMENTAIRE","NOM UTILISATEUR","DATE DE PUBLICATION","VISIBILITE"};
            
            getVue().getModeleTableCommentaire().setColumnIdentifiers(titresColonnes);
            
            String[] ligneDonnees = new String[7];
            
            
            if (getVue().getJRadioButtonMasquer().isSelected()){
                for (Commentaire unCommentaire:lesCommentaires ){

                    if(!unCommentaire.getMasquer().equals("visible")){
                        lesCommentairesAAfficher.add(unCommentaire);
                    }                   
                }
            }else{
                lesCommentairesAAfficher = lesCommentaires;
                
            }
             for (Commentaire unCommentaire:lesCommentairesAAfficher ){
                unCommentaire.getCommentaire();
                }
            
            for (Commentaire unCommentaire : lesCommentairesAAfficher) {
                ligneDonnees[0] = Integer.toString(unCommentaire.getIdR());
                ligneDonnees[1] = Integer.toString(unCommentaire.getIdu());
                ligneDonnees[2] = Integer.toString(unCommentaire.getNote());
                ligneDonnees[3] = unCommentaire.getCommentaire();
                ligneDonnees[4] = DaoUtilisateur.getUserById(unCommentaire.getIdu()).getPseudoU();
                ligneDonnees[5] = dateFormat.format(unCommentaire.getDateCreation());
                ligneDonnees[6] = unCommentaire.getMasquer();
                
                getVue().getModeleTableCommentaire().addRow(ligneDonnees);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CtrlCommentaireResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
}
