/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Reclamation;
import Interfaces.IReclamation;
import Technique.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author macbookpro
 */
public class ReclamationDao implements IReclamation {

    Statement ste;
    Connection connexion;

    public ReclamationDao() {

        try {
            connexion = Connexion.getIstanceConnexion();
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void AjouterReclamation(Reclamation r) {
        try {
            String req1 = "insert into reclamation (titre,etat,description,id_client,date)"
                        + "values('" + r.getTitre() + "', '" + r.getEtat() + "', '" + r.getDescription() + "', '" + r.getId_client() + "','" + r.getDate() + "')";

            ste.executeUpdate(req1);

        } catch (SQLException ex) {
            System.out.println("yesss" + ex);
        }
    }

    @Override
    public void ModifierReclamation(Reclamation r) {
        try {
            String req1 = "UPDATE reclamation set `titre`='" + r.getTitre() + "', `etat`='" + r.getEtat() + "', `description`='" + r.getDescription() + "', `id_client`='" + r.getId_client() + "',`date`='" + r.getDate() + "' where `id_reclamation`='" + r.getId_reclamation() + "'";
            System.out.println(req1);
            ste.executeUpdate(req1);

        } catch (SQLException ex) {
            System.out.println("yesss" + ex);
        }
    }

    public void SupprimerReclamation(Reclamation r) {
        try {
            String req1 = "delete from reclamation"
                        + " where id_reclamation=" + r.getId_reclamation();
            ste.executeUpdate(req1);

        } catch (SQLException ex) {
            System.out.println("yesss" + ex);
        }
    }

    @Override
    public List<Reclamation> findAll() {

        List<Reclamation> listeReclamation = new ArrayList<>();
        String requete = "select * from reclamation";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Reclamation a = new Reclamation();
                a.setId_reclamation(resultat.getInt(1));
                a.setTitre(resultat.getString(2));
                a.setEtat(resultat.getString(3));
                a.setDescription(resultat.getString(4));
                a.setId_client(resultat.getInt(5));
                a.setDate(resultat.getDate(6));

                listeReclamation.add(a);
            }
            return listeReclamation;

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    @Override
    public Reclamation findOne(int id) {
        List<Reclamation> listeReclamation = new ArrayList<>();
        String requete = "select * from reclamation where id_reclamation=" + id;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            Reclamation a = new Reclamation();
            while (resultat.next()) {

                a.setId_reclamation(resultat.getInt(1));
                a.setTitre(resultat.getString(2));
                a.setEtat(resultat.getString(3));
                a.setDescription(resultat.getString(4));
                a.setId_client(resultat.getInt(5));
                a.setDate(resultat.getDate(6));

            }
            return a;

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public List<Reclamation> findByClient(int id) {

        List<Reclamation> listeReclamation = new ArrayList<>();
        String requete = "select * from reclamation where id_client=" + id;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Reclamation a = new Reclamation();
                a.setId_reclamation(resultat.getInt(1));
                a.setTitre(resultat.getString(2));
                a.setEtat(resultat.getString(3));
                a.setDescription(resultat.getString(4));
                a.setId_client(resultat.getInt(5));
                a.setDate(resultat.getDate(6));

                listeReclamation.add(a);
            }
            return listeReclamation;

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }
}
