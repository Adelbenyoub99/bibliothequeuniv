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
public class ConnectBibliothequaire {
     
    Connexion c = new Connexion();
    PreparedStatement ps;
    public ResultSet rs;
    
    public ConnectBibliothequaire(){
      
        try {
            ps = c.connectBdd().prepareStatement("SELECT * FROM bibliothecaire");
                 rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
        }
      
 }
    
    
    
    
    
    
    
    
    
    
}
