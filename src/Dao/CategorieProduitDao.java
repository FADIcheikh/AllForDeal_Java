/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.CategorieMere;
import Entities.CategorieProduit;
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
public class CategorieProduitDao {

    private Connection connexion;

    public CategorieProduitDao() {
        connexion = Connexion.getIstanceConnexion();
    }

    public void ajouterCategorieProduit(CategorieProduit p) {
        try {
            CategorieMere cm =new CategorieMere();
        CategorieMereDao cmd= new CategorieMereDao();
            PreparedStatement st = connexion.prepareStatement("INSERT INTO `categorie_produit`(`id_categorie_produit`, `nom_categorie_produit`, `nb_pts_categorie_produit`,`categorieMere`) VALUES (?,?,?,?)");

            st.setInt(1, p.getId());
            st.setString(2, p.getNom());
            st.setInt(3, p.getNbPts());
            st.setInt(4, p.getCategorieMere().getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierCategorieProduit(CategorieProduit p) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `categorie_produit` SET `nom_categorie_produit`=?,`nb_pts_categorie_produit`=?,`categorieMere`=? WHERE `id_categorie_produit`=?");

            st.setInt(1, p.getId());
            st.setString(2, p.getNom());
            st.setInt(3, p.getNbPts());
            st.setInt(4, p.getCategorieMere().getId());
            st.setInt(5, p.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerCategorieProduit(CategorieProduit p) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from categorie_produit where id_categorie_produit=?");
            st.setInt(1, p.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CategorieProduit> findAll() {
        CategorieMereDao cd = new CategorieMereDao();
        List<CategorieProduit> listeproduit = new ArrayList<>();
        String requete = "select * from categorie_produit";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CategorieProduit p = new CategorieProduit();
                p.setId(resultat.getInt(1));
                p.setNom(resultat.getString(2));
                p.setNbPts(resultat.getInt(3));
                p.setCategorieMere(cd.findOne(resultat.getInt(4)));
                listeproduit.add(p);
            }
            return listeproduit;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public CategorieProduit findOne(int id) {

        CategorieProduit p = new CategorieProduit();
        CategorieMereDao cd = new CategorieMereDao();
        try {
            PreparedStatement st = connexion.prepareStatement("select * from `categorie_produit` where `id_categorie_produit`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {

                p.setId(resultat.getInt(1));
                p.setNom(resultat.getString(2));
                p.setNbPts(resultat.getInt(3));
                p.setCategorieMere(cd.findOne(resultat.getInt(4)));

            }
            return p;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }
    public List<CategorieProduit> RechercherCategorieProduitParCategorieMere(int x)
             
    { List<CategorieProduit> listeCategorieProduitM = new ArrayList<>();
        String requete = "SELECT * FROM categorie_produit WHERE categorieMere ="+ x ;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

       
        while (resultat.next()) {
                CategorieProduit cp = new CategorieProduit(
                        resultat.getInt("id_categorie_produit"),
                resultat.getString("nom_categorie_produit"),
                resultat.getInt("nb_pts_categorie_produit")
                
                );
              listeCategorieProduitM.add(cp);  
            }
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    return listeCategorieProduitM;
    }

    
    
    public void supprimerCategorieProduitParId(int id_categorie_produit) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from categorie_produit where id_categorie_produit=?");
            st.setInt(1, id_categorie_produit);
              System.out.println(st);
           st.executeUpdate();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
