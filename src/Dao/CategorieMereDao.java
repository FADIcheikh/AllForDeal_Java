/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.CategorieMere;
import Technique.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saif Eddine
 */
public class CategorieMereDao {

    public Connection conn = Connexion.getIstanceConnexion();
    private Object connexion;

    
    public CategorieMereDao()
    {
       // connexion = Connexion.getIstanceConnexion();
    }
   public void ajouterCategorieMere(CategorieMere cm) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO `categoriemere`(`id`, `nom`, `path`) VALUES (?,?,?)");

            st.setInt(1, cm.getId());
            st.setString(2, cm.getNom());
            st.setString(3, cm.getPath());
            
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
   
    public void modifierCategorieMere(CategorieMere cm) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE `categoriemere` SET `nom`=?,`path`=? WHERE `id_categorie_produit`=?");
            
            
            st.setInt(1, cm.getId());
            st.setString(2, cm.getNom());
            st.setString(3, cm.getPath());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void supprimerCategorieMere(CategorieMere cm) {
        try {
            PreparedStatement st = conn.prepareStatement("Delete from categoriemere where id=?");
            st.setInt(1, cm.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
      public List<CategorieMere> findAll() {
        List<CategorieMere> listeCategorieMere = new ArrayList<>();
        String requete = "select * from categoriemere";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CategorieMere cm = new CategorieMere(resultat.getInt("id"),
                resultat.getString("nom"),
                resultat.getString("path"));
              listeCategorieMere.add(cm);  
            }
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
       return listeCategorieMere; 
        
    }
       public CategorieMere findOne(int id) {
        
        CategorieMere cm = new CategorieMere();
        
        try {
            PreparedStatement st = conn.prepareStatement("select * from `categoriemere` where `id`=?");
            st.setInt(1,id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {
                
                cm.setId(resultat.getInt(1));
                cm.setNom(resultat.getString(2));
                cm.setPath(resultat.getString(3));
                
            }
            return cm;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
        
}
}
