/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.dao;

/**
 *
 * @author 33652
 */
public class ExceptionDAO extends Exception {
    /**
     * Creates a new instance of <code>DaoException</code> without detail message.
     */
    public ExceptionDAO() {
    }

    /**
     * Constructs an instance of <code>DaoException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExceptionDAO(String msg) {
        super(msg);
    }
}
