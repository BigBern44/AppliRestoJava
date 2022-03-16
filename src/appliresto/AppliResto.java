/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appliresto;

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;

/**
 *
 * @author eliott
 */
public class AppliResto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ChoixAuthentification frameName = new ChoixAuthentification();
        
        frameName.setVisible(true);
        
    }
    
}
