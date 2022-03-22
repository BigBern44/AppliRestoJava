/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



/**
 *
 * @author 33652
 */@Entity
public class utilisateur implements Serializable {

    public utilisateur() {
    }

    public utilisateur(String email, String login, String mdp) {
        this.email = email;
        this.login = login;
        this.mdp = mdp;
    }

    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    private String login;
    private String mdp;

   

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
