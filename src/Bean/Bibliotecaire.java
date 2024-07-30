
package Bean;


public class Bibliotecaire {
    
    private String matricule, nom, prenom, datenaiss, genre, pass, connect;
    
    public Bibliotecaire(){
    }

    public Bibliotecaire(String matricule, String nom, String prenom, String datenaiss, String genre, String pass, String connect) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.datenaiss = datenaiss;
        this.genre = genre;
        this.pass = pass;
        this.connect = connect;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }
    
    

  



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
