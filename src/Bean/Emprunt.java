/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author HP
 */
public class Emprunt {
    
    String id, matricule, rayon, dateemprunt, heure, dateretour,prolongation;

    public Emprunt() {
    }

    public Emprunt(String id, String matricule, String rayon, String dateemprunt, String heure, String dateretour, String prolongation) {
        this.id = id;
        this.matricule = matricule;
        this.rayon = rayon;
        this.dateemprunt = dateemprunt;
        this.heure = heure;
        this.dateretour = dateretour;
        this.prolongation = prolongation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(String dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String dateretour) {
        this.dateretour = dateretour;
    }

    public String getProlongation() {
        return prolongation;
    }

    public void setProlongation(String prolongation) {
        this.prolongation = prolongation;
    }
    
    
}
