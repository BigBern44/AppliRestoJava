package modele.dao;

import java.util.Collection;

/**
 * Modéle de classe DAO 
 *
 * Les méthodes devraient être "static" de préférence. 
 * Mais, jusqu'à la version 7 de Java (version 8 non installée sur les environnements de développement disponibles au lycée),
 * on ne peut pas déclarer des méthodes "static" dans une interface
 * @author nbourgeois
 * @version 21 novembre 2013
 *   - octobre 2016 : 
 *       > méthodes renommées (create en insert et getOne en getOneById
 *       > commentaires javadoc complétés
 * @param <TMetier> Classe de l'objet métier alimenté par la classe Dao
 * @param <TIdMetier> Type de l'identifiant de cette classe
 */
public interface DaoInterface<TMetier, TIdMetier> {


    /**
     * getOneById
     *
     * @param idMetier identifiant métier de l'objet recherché
     * @return objet métier trouvé, ou null sinon
     * @throws java.lang.Exception
     */
    public TMetier getOneById(TIdMetier idMetier) throws Exception;

    /**
     * getAll
     *
     * @return collection de l'ensemble des objets métier disponibles; elle peut
     * étre vide
     * @throws java.lang.Exception
     */
    public Collection<TMetier> getAll() throws Exception;

//    /**
//     * retrieveMany
//     * @param objet contenant les critéres de recherche, null si aucun critére (retourner tous les objets)
//     * @return collection des objets métier trouvé; elle peut étre vide
//     */
//    public Collection<TMetier> retrieveMany(String criteres) throws Exception;
 
    /**
     * insert
     *
     * @param objetMetier objet contenant les données nécessaires à l'ajout
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws java.lang.Exception
     */
    public int insert(TMetier objetMetier) throws Exception;   
    
    /**
     * update
     *
     * @param idMetier identifiant métier de l'objet é modifier
     * @param objetMetier objet métier modifié
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws java.lang.Exception
     */
    public int update(TIdMetier idMetier, TMetier objetMetier) throws Exception;

    /**
     * delete
     *
     * @param idMetier identifiant métier de l'objet é détruire
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
     * @throws java.lang.Exception
     */
    public int delete(TIdMetier idMetier) throws Exception;
}
