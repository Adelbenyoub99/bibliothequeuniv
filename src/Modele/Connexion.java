package Modele;

import java.sql.*; // package base de donnees
import javax.swing.JOptionPane;

public class Connexion {

    // on implemente l'interface connection et on declare l'objet c
    Connection c;

    public Connexion() {

        // etape 1: connexion au serveure de base de donnee
        try {
            Class.forName("com.mysql.jdbc.Driver");
          //  JOptionPane.showMessageDialog(null, "Connexion réussie au serveur ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Echec de la connexion au serveur " + e.getMessage());
        }
        // etape 2: la connection a la base de donnee: le nom et le mot de passe
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque", "root", "");
          //  JOptionPane.showMessageDialog(null, "Connexion a la bdd réussie ");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Echec de la connexion a la bdd" + e.getMessage());

        }
    }
    // declaration d'une methode de connetion 
    public Connection connectBdd(){
    
        
        
        return c;
    
    } 
    
    
    public static void main(String[] args) {

        Connexion co = new Connexion();
        co.connectBdd();
        
        
        
    }

}
