/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dao.ClientDao;
import Dao.ProduitDao;
import Entities.Enchere;
import Technique.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saif Eddine
 */
public class EnchereDao {

    private Connection connexion;

    public EnchereDao() {
        connexion = Connexion.getIstanceConnexion();
    }

    public void ajouterEnchere(Enchere e) {
        try {
            PreparedStatement st = connexion.prepareStatement("INSERT INTO `enchere`(`id_enchere`, `id_produit`, `date_enchere` ,`date_vente`,`id_client`,`prix`) VALUES (?,?,?,?,?,?)");

            st.setInt(1, e.getId_enchere());
            st.setInt(2, e.getProduit().getId());
            st.setDate(3, e.getDate_enchere());
            st.setDate(4, e.getDate_vente());
            st.setInt(5, e.getClient().getId());
            st.setInt(6, e.getPrix());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierEnchere(Enchere e) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `enchere` SET `id_produit`=?,`date_enchere`=? ,`date_vente`=?,`id_client`=?,`prix`=? WHERE `id_enchere`=?");

            st.setInt(1, e.getId_enchere());
            st.setInt(2, e.getProduit().getId());
            st.setDate(3, e.getDate_enchere());
            st.setDate(4, e.getDate_vente());
            st.setInt(5, e.getClient().getId());
            st.setInt(6, e.getPrix());
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerEnchere(Enchere e) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from enchere where id_enchere=?");
            st.setInt(1, e.getId_enchere());
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Enchere> findAll() {
        List<Enchere> listetEncheres = new ArrayList<>();
        String requete = "select * from enchere";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {

                Enchere e = new Enchere();
                e.setId_enchere(resultat.getInt(1));
                ProduitDao pd = new ProduitDao();
                e.setProduit(pd.findOne(resultat.getInt("id_produit")));
                e.setDate_enchere(resultat.getDate(3));
                e.setDate_vente(resultat.getDate(4));
                ClientDao cd = new ClientDao();
                e.setClient(cd.findOne(resultat.getInt("id_client")));
                e.setPrix(resultat.getInt("prix"));

                listetEncheres.add(e);
            }
            return listetEncheres;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public Enchere findOne(int id) {

        Enchere e = new Enchere();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `enchere` where `id_enchere`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {

                e.setId_enchere(resultat.getInt(1));
                ProduitDao pd = new ProduitDao();
                e.setProduit(pd.findOne(resultat.getInt("id_produit")));
                e.setDate_enchere(resultat.getDate(3));
                e.setDate_vente(resultat.getDate(4));
                ClientDao cd = new ClientDao();
                e.setClient(cd.findOne(resultat.getInt("id_client")));
                e.setPrix(resultat.getInt("prix"));

            }
            return e;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }
    
     public Enchere ChercherEnchereParIdProduit(int id) {

        Enchere e = new Enchere();

        try {
            PreparedStatement st = connexion.prepareStatement("select * from `enchere` where `id_produit`=?");
            st.setInt(1, id);
            ResultSet resultat = st.executeQuery();

            while (resultat.next()) {

                e.setId_enchere(resultat.getInt(1));
                ProduitDao pd = new ProduitDao();
                e.setProduit(pd.findOne(resultat.getInt("id_produit")));
                e.setDate_enchere(resultat.getDate(3));
                e.setDate_vente(resultat.getDate(4));
                ClientDao cd = new ClientDao();
                e.setClient(cd.findOne(resultat.getInt("id_client")));
                e.setPrix(resultat.getInt("prix"));

            }
            return e;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }

    public java.sql.Date AjouterJour(java.sql.Date date) {

        Calendar today = Calendar.getInstance();
        today.setTime(date);
        today.add(Calendar.DATE, +1);
        java.sql.Date finale = new java.sql.Date(today.getTimeInMillis());
        System.out.println(finale);
        return finale;
    }

    public Date ConcatenerStringToDate(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        Date date = null;
        try {
            date = (Date) formatter.parse(dateInString);
        } catch (ParseException ex) {
            Logger.getLogger(EnchereDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(date);
        System.out.println(formatter.format(date));

        return date;
    }
 

    public boolean ComparerDateAvecDateActuelle(String date) {
        String dd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        ConcatenerStringToDate(dd);
        ConcatenerStringToDate(date);
        boolean check = date.equals(dd);
        return check;

    }
}
