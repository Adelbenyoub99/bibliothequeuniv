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
public class ConnectAbonnee {
     
    Connexion c = new Connexion();
    PreparedStatement ps;
    public ResultSet rs;
    
    public ConnectAbonnee(){
      
        try {
            ps = c.connectBdd().prepareStatement("SELECT * FROM abonnee");
                 rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectAbonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
      
 }
    
    
    
    
    
    
    
    
    
    
}
