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
public class ConnectEmprunt {

    private Connexion c = new Connexion();

    // Utilisé un preperd statment 
   public PreparedStatement ps;

   public ResultSet rs;

    public ConnectEmprunt() {

        try {
            ps = c.connectBdd().prepareStatement("SELECT* FROM emprunt");
            rs = ps.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectEmprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

}
