/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ConnectGestionnaire {

    private Connexion c = new Connexion();

    // Utilisé un preperd statment 
   public PreparedStatement ps;

   public ResultSet rs;

    public ConnectGestionnaire() {

        try {
            ps = c.connectBdd().prepareStatement("SELECT * FROM gestionnaire");
            rs = ps.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectGestionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
