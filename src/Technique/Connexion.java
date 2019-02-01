/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AZIZ
 */
public class Connexion {
    
    String url="jdbc:mysql://localhost:3306/allfordealfinale";
    String login = "root";
    String pwd="";
   static Connection instanceConnexion;

    private Connexion() {
             try {
            instanceConnexion = DriverManager.getConnection(url, login, pwd);
            System.out.println("ok");
        } catch (SQLException ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getIstanceConnexion(){
        if(instanceConnexion == null){
             new Connexion();
              return instanceConnexion; 
        }
           
        else
            return instanceConnexion; 
   
    }
    

    
    
}
