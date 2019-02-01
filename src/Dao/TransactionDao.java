/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Transaction;
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
public class TransactionDao {

    private Connection connexion;

    public TransactionDao() {
        connexion = Connexion.getIstanceConnexion();
    }

    public void ajouterTransaction(Transaction tr) {
        try {
            PreparedStatement st = connexion.prepareStatement("INSERT INTO `transaction`(`client`, `montant` ,`type`) VALUES (?,?,?)");

            st.setInt(1, tr.getClient().getId());
            st.setInt(2, tr.getMontant());
            st.setString(3, tr.getType());

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierTransaction(Transaction tr) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `transaction` SET `client`=?,`montant`=? ,`type`=? WHERE `id`=?");

            st.setInt(1, tr.getClient().getId());
            st.setInt(2, tr.getMontant());
            st.setString(3, tr.getType());
            st.setInt(4, tr.getId());

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerTransaction(Transaction tr) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from transaction where id=?");
            st.setInt(1, tr.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Transaction> findAll() {
        List<Transaction> listetTransactions = new ArrayList<>();
        String requete = "select * from transaction";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            ClientDao cd = new ClientDao();
            while (resultat.next()) {
                Transaction tr = new Transaction();
                tr.setId(resultat.getInt(1));
                tr.setMontant(resultat.getInt(2));
                tr.setType(resultat.getString(3));
                tr.setClient(cd.findOne(resultat.getInt(4)));
                listetTransactions.add(tr);
            }
            return listetTransactions;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public Transaction findOne(int id) {

        Transaction tr = new Transaction();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `transaction` where `id`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();
            ClientDao cd = new ClientDao();
            while (resultat.next()) {

                tr.setId(resultat.getInt(1));
                tr.setMontant(resultat.getInt(2));
                tr.setType(resultat.getString(3));
                tr.setClient(cd.findOne(resultat.getInt(4)));

            }
            return tr;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }

}
