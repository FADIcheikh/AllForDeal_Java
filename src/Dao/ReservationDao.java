/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Reservation;
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
 * @author AZIZ
 */
public class ReservationDao {

    private Connection connexion;

    public ReservationDao() {
        connexion = Connexion.getIstanceConnexion();
    }

    public void ajouterReservation(Reservation e) {
        try {
            PreparedStatement st = connexion.prepareStatement("INSERT INTO `reservation`(`date_fin_reservation`, `id_produit`, `id_client`) VALUES (?,?,?)");

            st.setDate(1, e.getDateFinReservation());
            st.setInt(2, e.getProduit().getId());
            st.setInt(3, e.getClient().getId());

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierReservation(Reservation e) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `reservation` SET `produit`=?,`date_enchere`=? ,`date_vente`=?,`client`=? WHERE `id_enchere`=?");

            st.setDate(1, e.getDateFinReservation());
            st.setInt(2, e.getProduit().getId());
            st.setInt(3, e.getClient().getId());
            st.setInt(4, e.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerReservation(Reservation e) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from reservation where id_enchere=?");
            st.setInt(1, e.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reservation> findAll() {
        List<Reservation> listetReservation = new ArrayList<>();
        String requete = "select * from reservation";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {

                Reservation e = new Reservation();
                e.setId(resultat.getInt(1));
                e.setDateFinReservation(resultat.getDate(2));
                ProduitDao pd = new ProduitDao();
                e.setProduit(pd.findOne(resultat.getInt("produit")));
                ClientDao cd = new ClientDao();
                e.setClient(cd.findOne(resultat.getInt("client")));

                listetReservation.add(e);
            }
            return listetReservation;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public Reservation findOne(int id) {

        Reservation e = new Reservation();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `reservation` where `id_enchere`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {
                
                e.setId(resultat.getInt(1));
                e.setDateFinReservation(resultat.getDate(2));
                ProduitDao pd = new ProduitDao();
                e.setProduit(pd.findOne(resultat.getInt("produit")));
                ClientDao cd = new ClientDao();
                e.setClient(cd.findOne(resultat.getInt("client")));

            }
            return e;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }

}
