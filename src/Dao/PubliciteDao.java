/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Publicite;
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
 * @author lenovo i5
 */
public class PubliciteDao{

    Statement ste;
    Connection connexion;
    Publicite pub = new Publicite();

    public PubliciteDao() {

        connexion = Connexion.getIstanceConnexion();

    }

    public void ajouter(Publicite pub) {
        try {
            PreparedStatement st = connexion.prepareStatement("INSERT INTO"
                        + " `publicite`(`titre`,`description`,path) VALUES (?,?,?)");

            st.setString(1, pub.getTitre());
            st.setString(2, pub.getDescription());
            st.setString(3, pub.getPath());
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PubliciteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierPublicite(Publicite p) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `publicite` SET `nom`=?,`description`=?,`path`=? WHERE `id`=?");

            st.setString(3, p.getPath());
            st.setInt(5, p.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerPublicite(Publicite p) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from publicite where id=?");
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Publicite> findAll() {
        List<Publicite> listePublicite = new ArrayList<>();
        String requete = "select * from publicite";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {

                Publicite p = new Publicite();
                p.setId(resultat.getInt(1));

                p.setTitre(resultat.getString(2));
                p.setDescription(resultat.getString(3));
                p.setPath(resultat.getString(4));
                

                listePublicite.add(p);
            }
            return listePublicite;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public Publicite findOne(int id) {

        Publicite p = new Publicite();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `publicite` where `id`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {

                p.setId(resultat.getInt(1));

                p.setTitre(resultat.getString(2));
                p.setDescription(resultat.getString(3));
                p.setPath(resultat.getString(4));

            }
            return p;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }
}
