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
    public static List<Commentaire> selectAll() throws SQLException {
        List<Commentaire> lesCritiques = new ArrayList<Commentaire>();
        Commentaire uneCritique;
        ResultSet rs;
        PreparedStatement pstmt;
        Jdbc jdbc = Jdbc.getInstance();
        // préparer la requête
        String requete = "SELECT * FROM critiquer";
        pstmt = jdbc.getConnexion().prepareStatement(requete);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            int idR = rs.getInt("idR");
            int note = rs.getInt("note");
            String commentaire = rs.getString("commentaire");
            int idU = rs.getInt("idU");
            uneCritique = new Commentaire(idR, note, commentaire, idU);
            lesCritiques.add(uneCritique);
        }
        return lesCritiques;
    }
}
