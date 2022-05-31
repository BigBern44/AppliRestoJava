/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import modele.jdbc.Jdbc;
import modele.metier.Utilisateur;

/**
 *
 * @author 33652
 */
public class DaoUtilisateur  implements DaoInterface<Utilisateur,Integer> {

  
    
    public static int registerUser(Utilisateur unUtilisateur) throws Exception {
        int result = 0;
        ResultSet rs = null;
        // préparer la requête
        String requete = "INSERT INTO utilisateur(pseudoU, mdpU, mailU) VALUES (?,?,?) ";
        
        try {
           PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
           
            ps.setString(1, unUtilisateur.getPseudoU());
            ps.setString(2, MD5Hash(unUtilisateur.getMdpU()));
            ps.setString(3,unUtilisateur.getMailU());
            
            result = ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new DaoException("DaoPresence - create : pb JDBC\n" + ex.getMessage());
        }
        return result;
    }
    
     public static boolean loginUser(String email, String mdp ) throws Exception {
        boolean result = false;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM utilisateur WHERE mailU = ?";
        
        try {
           PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
           
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
        if (rs.next()) {

                Utilisateur user = (Utilisateur) chargerUnEnregistrement(rs);
                if (user.getMdpU().equals(MD5Hash(mdp))){
                    result = true;

                }
                
                
            }
        } catch (SQLException ex) {
           
        }
        return result;
    }
    public static boolean verifIfExist(String email) throws DaoException {
        boolean result = false;
        ResultSet rs = null;
        String requete = "SELECT * FROM utilisateur WHERE mailU = ?";
        
        try {

        PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);

        ps.setString(1, email);

        rs = ps.executeQuery();
        
        
        
        if (rs.next()) {
            
            result=true;
            
        } } catch (SQLException ex) {

        }
        
        return result;
    }
     
    public static Utilisateur getUserByEmail(String email) throws Exception {
        

        ResultSet rs = null;
        String requete = "SELECT * FROM utilisateur WHERE mailU = ?";
        try {
           PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);   
            ps.setString(1, email);
            rs = ps.executeQuery();

        if (rs.next()) {
            
               Utilisateur user = (Utilisateur) chargerUnEnregistrement(rs);
               return user;
            }
        } catch (SQLException ex) {
            
            
        }
        return null;
        
    }
    
    public  static Utilisateur getUserById(int Id) throws Exception {
        

        ResultSet rs = null;
        
        String requete = "SELECT * FROM utilisateur WHERE IdU = ?";
        try {
           PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);   
            ps.setString(1, Integer.toString(Id));
            rs = ps.executeQuery();

        if (rs.next()) {
            
               Utilisateur user = (Utilisateur) chargerUnEnregistrement(rs);
               return user;
            }
        } catch (SQLException ex) {
            
            
        }
        return null;
        
     }
    
    public static String getUserRoles(String email){
        ResultSet rs = null;
        String roles = "aucun";
        String requete = "SELECT * FROM utilisateur WHERE mailU = ?";
        
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);   
            ps.setString(1,email);
            rs = ps.executeQuery();

        if (rs.next()) {

               roles= rs.getString("role");
               
               if (roles == (null)){
                   
                   return "aucun";
               }
               
               return roles;
            }
        } catch (SQLException ex) {
            
        }
        return null;
        
    }
    
    
     
    
    public  static String MD5Hash(String mdp){
        String encryptedpassword = null;  
        try   
        {  
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(mdp.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encryptedpassword = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        } 
        return encryptedpassword;

}
    
     public static Utilisateur chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Utilisateur user = new Utilisateur();
            
            user.setIdU(rs.getInt("IDU"));
            
            user.setMailU(rs.getString("MAILU"));
            
            user.setPseudoU(rs.getString("PSEUDOU"));
            
            user.setMdpU(rs.getString("MDPU"));
            
        
            return user;
            
        } catch (SQLException ex) {
            throw new DaoException("DaoEquipier - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }

    @Override
    public Utilisateur getOneById(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Utilisateur> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int insert(Utilisateur objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Integer idMetier, Utilisateur objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
