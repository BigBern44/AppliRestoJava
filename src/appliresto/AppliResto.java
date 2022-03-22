/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appliresto;

import vue.VueStart;
import com.sun.jdi.connect.spi.Connection;
import controleur.CtrlPrincipal;
import controleur.CtrlPrincipal;
import java.util.List;
import java.io.IOException;
import java.lang.reflect.Array;
 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import modele.metier.utilisateur;

/**
 *
 * @author eliott
 */
public class AppliResto {

    
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args)  {

        CtrlPrincipal ctrlPrincipal; 
       
        
        
        
        ctrlPrincipal = new CtrlPrincipal();
        ctrlPrincipal.action();
    }
    
}
