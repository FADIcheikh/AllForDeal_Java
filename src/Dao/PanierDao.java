/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Panier;
import Interfaces.IPanier;
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
public class PanierDao implements IPanier {

    Statement ste;
    Connection connexion;

    public PanierDao() {

        try {
            connexion = Connexion.getIstanceConnexion();
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void AjouterPanier(Panier p) {
        try {
            String req1 = "insert into panier (client,produit)"
                        + "values('" + p.getClient().getId() + "', '" + p.getProduit().getId() + "')";

            ste.executeUpdate(req1);

        } catch (SQLException ex) {
            System.out.println("yesss" + ex);
        }
    }

    @Override
    public void SupprimerPanier(Panier p) {
        try {
            String req1 = "delete from panier"
                        + " where client=" + p.getClient().getId() + " and produit=" + p.getProduit().getId();
            ste.executeUpdate(req1);

        } catch (SQLException ex) {
            System.out.println("yesss" + ex);
        }
    }

    @Override
    public List<Panier> findAll() {

        List<Panier> listePanier = new ArrayList<>();
        String requete = "select * from panier";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            ClientDao cd = new ClientDao();
            ProduitDao pd = new ProduitDao();

            while (resultat.next()) {
                Panier a = new Panier();
                a.setProduit(pd.findOne(resultat.getInt(1)));
                a.setClient(cd.findOne(resultat.getInt(2)));

                listePanier.add(a);
            }
            return listePanier;

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    @Override
    public List<Panier> findByClient(int id) {
        List<Panier> listePanier = new ArrayList<>();
        String requete = "select * from Panier where client=" + id;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            ClientDao cd = new ClientDao();
            ProduitDao pd = new ProduitDao();

            while (resultat.next()) {
                Panier a = new Panier();
                a.setProduit(pd.findOne(resultat.getInt(1)));
                a.setClient(cd.findOne(resultat.getInt(2)));

                listePanier.add(a);
            }
            return listePanier;

        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }
}
