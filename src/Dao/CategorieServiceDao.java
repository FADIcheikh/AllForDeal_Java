/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.CategorieService;
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
public class CategorieServiceDao {

    private Connection connexion;

    public CategorieServiceDao() {
        connexion = Connexion.getIstanceConnexion();
    }

    public void ajouterCategorieService(CategorieService c) {
        try {
            PreparedStatement st = connexion.prepareStatement("INSERT INTO `categorie_service`(`id_categorie`, `nom_categorie`, `nb_pts`) VALUES (?,?,?)");

            st.setInt(1, c.getId());
            st.setString(2, c.getNom());
            st.setInt(3, c.getNbPts());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierCategorieService(CategorieService c) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `categorie_service` SET `nom_categorie`=?,`nb_pts`=? WHERE id_categorie=?");

            st.setString(1, c.getNom());
            st.setInt(2, c.getNbPts());
            st.setInt(3, c.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerCategorieService(CategorieService c) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from categorie_service where id_categorie=?");
            st.setInt(1, c.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<CategorieService> findAll() {
        List<CategorieService> listecat = new ArrayList<>();
        String requete = "select * from categorie_service";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                CategorieService c = new CategorieService();
                c.setId(resultat.getInt(1));
                c.setNom(resultat.getString(2));
                c.setNbPts(resultat.getInt(3));

                listecat.add(c);
            }
            return listecat;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public CategorieService findOne(int id) {

        CategorieService c = new CategorieService();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `categorie_service` where `id_categorie`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {
                c.setId(resultat.getInt(1));
                c.setNom(resultat.getString(2));
                c.setNbPts(resultat.getInt(3));
            }
            return c;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }

    public CategorieService findByName(String nom) {

        CategorieService c = new CategorieService();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `categorie_service` where `nom_categorie`=?");
            st.setString(1, nom);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {
                c.setId(resultat.getInt(1));
                c.setNom(resultat.getString(2));
                c.setNbPts(resultat.getInt(3));
            }
            return c;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }

}
