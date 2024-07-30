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
public class Gestionnaire {
    
    private String username , password, connect;


    public Gestionnaire() {
    
    }

    public Gestionnaire(String username, String password, String connect) {
        this.username = username;
        this.password = password;
        this.connect = connect;
    }
      public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }
    
    
    
    
    
    
}
