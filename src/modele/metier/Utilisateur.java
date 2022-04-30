/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;



/**
 *
 * @author rgarganigo@jolsio.net
 */
public class Utilisateur {
       int  idU;
       private String mailU;
       private String mdpU;
       private String pseudoU;
       
    public Utilisateur() {
     
    }
    public Utilisateur(String mailU, String mdpU, String pseudoU) {

        this.mailU = mailU;
        this.mdpU = mdpU;
        this.pseudoU = pseudoU;
       
    }

    @Override
    public String toString() {
        return "utilisateur{" + "idU=" + idU + ", mailU=" + mailU + ", mdpU=" + mdpU + ", pseudoU=" + pseudoU + ", adminU="  + '}';
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getMailU() {
        return mailU;
    }

    public void setMailU(String mailU) {
        this.mailU = mailU;
    }

    public String getMdpU() {
        return mdpU;
    }

    public void setMdpU(String mdpU) {
        this.mdpU = mdpU;
    }

    public String getPseudoU() {
        return pseudoU;
    }

    public void setPseudoU(String pseudoU) {
        this.pseudoU = pseudoU;
    }

 
       
    
}
