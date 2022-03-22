/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.jpa;

import com.amdelamar.jhash.Hash;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modele.metier.utilisateur;

/**
 *
 * @author 33652
 */
public class utilisateurJPA {
    private static final String PERSISTENCE_UNIT_NAME = "AppliRestoPU";
    private static EntityManagerFactory factory;
    
    
    public static void registerUser(String email, String login, String mdp) {
        
        
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        utilisateur user = new utilisateur();
            user.setEmail(email);
            user.setLogin(login);
            user.setMdp(MD5Hash(mdp));
            
            em.persist(user);
            em.getTransaction().commit();
            em.close();
    }
    
     public static boolean LoginUser(String email, String mdp) {
        
        utilisateur user = findUserByEmail(email);  
        if(user == null){
            return false;     
        }else{
            if(user.getMdp().equals(MD5Hash(mdp)) ){
                return true;
            }else{
                return false;
            }
            
        }
        
    }
    
    public static utilisateur findUserByEmail(String email){
        utilisateur user = null;
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select u from utilisateur u where u.email = :email");
       
        System.out.println(email);
        
        List<utilisateur> listeUtil = q.setParameter("email",email).getResultList();
        
        
        if (!listeUtil.isEmpty()){
              user = listeUtil.get(0);
        }
      
          
        
        if (user!=null){
           return user;
        }else{
            return null;
        }
       
   
        

        
    }
    
    public static String MD5Hash(String mdp){
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
}


