/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectBDD;

import java.sql.Connection;
import java.sql.DriverManager;
 
 
public class Base {
     
    public Connection conn;
     
    public void connexionBD(){
         
         
        /*  Chargement de la Base de données  */
            try{
                 
                Class.forName("com.mysql.jdbc.Driver.");
                 
            } catch (Exception e) {
                 
                System.out.println(" Erreur de chargement de la Base de données");
                e.getMessage();
                System.exit(0);
                 
            }
         
         
            /*    Connexion de la Base de données  */
            try {
                 
                String url = "jdbc:mysql://localhost/AppliJavaResto";
                String user = "root";
                String passwd ="root";
                conn = DriverManager.getConnection(url, user, passwd);
                 
            } catch (Exception e) {
                System.out.println(" Erreur de Connexion à la Base de données ");
            }  
             
        }
     
            /*   */
            public Connection getConnect(){
                 
                return conn;
            }
     
             
            /*   Deconnexion  */
            public void Deconnexion(){
             
                try {
                    conn.close();
                     
                } catch (Exception e) {
                    System.out.println(" Déconnexion Impossible ");
                }
            }
         
             
             
             
}