/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static modele.dao.DaoUtilisateur.MD5Hash;
import modele.metier.Commentaire;
import modele.jdbc.Jdbc;


/**
 *
 * @author hberneron@jolsio.net
 */

public class DaoCommentaire {
   
    /**
     *
     * @return
     * @throws SQLException
     */
    public static List<Commentaire> selectAll() throws Exception {
        List<Commentaire> lesCritiques = new ArrayList<Commentaire>();
        Commentaire uneCritique;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // prÃ©parer la requÃªte
        String requete = "SELECT * FROM critiquer";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int idR = rs.getInt("idR");
            int note = rs.getInt("note");
            String commentaire = rs.getString("commentaire");
            int idU = rs.getInt("idU");
            String masquer;
            if (rs.getInt("masquer")==0){
                masquer = "pas masqué";
            }else{
                masquer = "masqué";
            }
            uneCritique = new Commentaire(idR, note, commentaire, idU, masquer);
            lesCritiques.add(uneCritique);
        }
        return lesCritiques;
    }
    
     public static List<Commentaire> selectAllMasquer() throws Exception {
        List<Commentaire> lesCritiques = new ArrayList<Commentaire>();
        Commentaire uneCritique;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // prÃ©parer la requÃªte
        String requete = "SELECT * FROM critiquer WHERE masquer = 1";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int idR = rs.getInt("idR");
            int note = rs.getInt("note");
            String commentaire = rs.getString("commentaire");
            int idU = rs.getInt("idU");
            String masquer;
            if (rs.getInt("masquer")==0){
                masquer = "pas masqué";
            }else{
                masquer = "masqué";
            }
            uneCritique = new Commentaire(idR, note, commentaire, idU, masquer);
            lesCritiques.add(uneCritique);
        }
        return lesCritiques;
    }
    
    public static int SupprimerCommentaire(String idR,String idU) throws DaoException  {
        
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        int result = 0;
        
        String requete = "DELETE FROM critiquer WHERE idR=? AND idU=? ";
        
        try {
        PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
        ps.setString(1, idR);
        ps.setString(2, idU);
   
        result = ps.executeUpdate();
        
        } catch (SQLException ex) {
            throw new DaoException("DaoPresence - update : pb JDBC\n" + ex.getMessage());
        }
        
        return result;
     
    }
    
    public static int MasquerCommentaire(String idR,String idU) throws DaoException  {
        
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        int result = 0;
        
        String requete = "UPDATE critiquer SET masquer = 1 WHERE idR=? AND idU=? ";
        
        try {
        PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
        ps.setString(1, idR);
        ps.setString(2, idU);
   
        result = ps.executeUpdate();
        
        } catch (SQLException ex) {
            throw new DaoException("DaoPresence - update : pb JDBC\n" + ex.getMessage());
        }
        
        return result;
     
    }
    
    public static int DemasquerCommentaire(String idR,String idU) throws DaoException  {
        
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        int result = 0;
        
        String requete = "UPDATE critiquer SET masquer = 0 WHERE idR=? AND idU=? ";
        
        try {
        PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
        ps.setString(1, idR);
        ps.setString(2, idU);
   
        result = ps.executeUpdate();
        
        } catch (SQLException ex) {
            throw new DaoException("DaoPresence - update : pb JDBC\n" + ex.getMessage());
        }
        
        return result;
     
    }
    
    
    
    
    
}
