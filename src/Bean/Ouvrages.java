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
public class Ouvrages {
    
    String rayon, titre, auteur, specialite, statut, isbn, nbrexamplaires;

    public Ouvrages() {
    }

    public Ouvrages(String rayon, String titre, String auteur, String specialite, String statut, String isbn, String nbrexamplaires) {
        this.rayon = rayon;
        this.titre = titre;
        this.auteur = auteur;
        this.specialite = specialite;
        this.statut = statut;
        this.isbn = isbn;
        this.nbrexamplaires = nbrexamplaires;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getStatut() {
        return statut;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getNbrexamplaires() {
        return nbrexamplaires;
    }

    public void setNbrexamplaires(String nbrexamplaires) {
        this.nbrexamplaires = nbrexamplaires;
    }
    
    
    
}
