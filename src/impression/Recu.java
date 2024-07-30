/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impression;

/**
 *
 * @author HP
 */
public class Recu {

    private String rayon, dater;


    public Recu(String rayon, String dateRetour) {
        this.rayon = rayon;
        this.dater = dateRetour;
    }

    public String getRayon() {
        return rayon;
    }

    public String getDateRetour() {
        
        return dater;
    }
}