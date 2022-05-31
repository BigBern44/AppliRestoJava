/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.sql.Date;

/**
 *
 * @author rgarganigo@jolsio.net
 */
public class Commentaire {
    
    private int idR;
    private int note;
    private String commentaire;
    private int idu;
    private String masquer;
    private java.sql.Date dateCreation;

    
   
    public Commentaire(){
        
    }
   // "this" désigne l'objet courant
    public Commentaire(int idR, int note, String commentaire, int idu, String masquer, Date dateCreation) {
        
       this.idR = idR;
       this.note = note;
       this.commentaire = commentaire;
       this.idu = idu;
       this.masquer = masquer;
       this.dateCreation = dateCreation;

       
    }

    @Override
    public String toString() {
        return "Critiquer{" + "idR=" + idR + ", note=" + note + ", commentaire=" + commentaire + ", idu=" + idu + '}';
    }
       
      
    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
    
    public String getMasquer() {
        return masquer;
    }

    public void setMasquer(String masquer) {
        this.masquer = masquer;
    }
    
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }


   


       
       
}
