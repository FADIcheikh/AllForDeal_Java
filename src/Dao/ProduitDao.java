/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Produit;
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
 * @author Achref
 */
public class ProduitDao {

    Statement ste;
    Connection connexion;

    public ProduitDao() {

        try {
            connexion = Connexion.getIstanceConnexion();
            ste = connexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

      public void ajouterProduit(Produit p) {
        try {
            PreparedStatement st = connexion.prepareStatement("INSERT INTO `produit`( `titre`, `categorie`, `marque`, `prix`, `date_fin`, `est_valide`, `quantite`, `estAchetee`, `pict`, `description`, `id_cliente`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            st.setString(1, p.getTitre());
            st.setInt(2, p.getCategorie().getId());
            st.setString(3, p.getMarque());
            st.setFloat(4, p.getPrix());
            st.setDate(5, p.getDateFin());
            st.setInt(6, p.getEstValide());
            st.setInt(7, p.getQuantite());
            st.setInt(8, p.getEstAchetee());
            st.setString(9, p.getPict());
            st.setString(10, p.getDescription());
            st.setInt(11,1);
            System.out.println("sql request" + st);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("yesss" + ex);
        }

    }

      public void modifierProduit(Produit p) {
        try {
            PreparedStatement st = connexion.prepareStatement("UPDATE `produit` SET `titre`=?,`marque`=?,`prix`=?,`date_fin`=?,`quantite`=?,`pict`=?,`description`=? WHERE `id_produit`=?");

            st.setString(1, p.getTitre());
          
            st.setString(2, p.getMarque());
            st.setFloat(3, p.getPrix());
            st.setDate(4, p.getDateFin());
            
            st.setInt(5, p.getQuantite());
           
            st.setString(6, p.getPict());
            st.setString(7, p.getDescription());
            st.setInt(8, p.getId());
        
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("yesss" + ex);
        }
    }

    public void supprimerProduit(Produit p) {
        try {
            PreparedStatement st = connexion.prepareStatement("Delete from `produit` where `id_produit`=?");
            st.setInt(1, p.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Produit> findAll() {
        List<Produit> listeProduit = new ArrayList<>();
        String requete = "select * from produit";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            CategorieProduitDao cpd = new CategorieProduitDao();
            ClientDao cd = new ClientDao();
            while (resultat.next()) {
                Produit p = new Produit();

                p.setId(resultat.getInt(1));
                p.setTitre(resultat.getString(2));
                p.setCategorie(cpd.findOne(resultat.getInt(3)));
                p.setMarque(resultat.getString(4));
                p.setPrix(resultat.getFloat(5));
                p.setDateFin(resultat.getDate(6));
                p.setEstValide(resultat.getInt(7));
                p.setQuantite(resultat.getInt(8));
                p.setEstAchetee(resultat.getInt(9));
                p.setPict(resultat.getString(10));
                p.setDescription(resultat.getString(11));
                p.setClient(cd.findOne(resultat.getInt(12)));
                listeProduit.add(p);
            }
            return listeProduit;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    public List<Produit> findByClient(int id) {
        List<Produit> listeProduit = new ArrayList<>();
        String requete = "select * from produit where id_cliente=" + id;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            CategorieProduitDao cpd = new CategorieProduitDao();
            ClientDao cd = new ClientDao();
            while (resultat.next()) {
                Produit p = new Produit();

                p.setId(resultat.getInt(1));
                p.setTitre(resultat.getString(2));
                p.setCategorie(cpd.findOne(resultat.getInt(3)));
                p.setMarque(resultat.getString(4));
                p.setPrix(resultat.getFloat(5));
                p.setDateFin(resultat.getDate(6));
                p.setEstValide(resultat.getInt(7));
                p.setQuantite(resultat.getInt(8));
                p.setEstAchetee(resultat.getInt(9));
                p.setPict(resultat.getString(10));
                p.setDescription(resultat.getString(11));
                p.setClient(cd.findOne(resultat.getInt(12)));
                listeProduit.add(p);
            }
            return listeProduit;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }
    
    
    
    
     public List<Produit> find(String text) {
        List<Produit> myList = new ArrayList<>();
        CategorieProduitDao cpd = new CategorieProduitDao();
       String requete = "SELECT * from produit where titre like'"+text+"%' or categorie like'"+text+"%' or marque like'"+text+"%' or prix like'"+text+"%' or est_valide like'"+text.toString()+"%' or id_produit like'"+text.toString()+"%' or quantite like'"+text+"%' or estAchetee like'"+text+"%' or description like'"+text+"%'";
        try {
            PreparedStatement ps =  connexion.prepareStatement(requete);
           // ps.setString(1, name);
            
            ResultSet rs = ps.executeQuery(requete);
            System.out.println("*******"+rs);
            while(rs.next()){
                Produit user = new Produit();
                user.setId(rs.getInt("id_produit"));
                user.setTitre(rs.getString("titre"));
                user.setCategorie(cpd.findOne(rs.getInt("categorie")));
                user.setMarque(rs.getString("marque"));
                user.setPrix(rs.getFloat("prix"));
                user.setDateFin(rs.getDate("date_fin"));
                user.setEstValide(rs.getInt("est_valide"));
                user.setQuantite(rs.getInt("quantite"));
                user.setEstAchetee(rs.getInt("estAchetee"));
                user.setPict(rs.getString("pict"));
                user.setDescription(rs.getString("description"));
                myList.add(user);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return myList;
        
    }
     
     
     
       public Produit findOne(int id) {

        Produit p = new Produit();
        String requete = "select * from produit where id_produit=" + id;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            CategorieProduitDao cpd = new CategorieProduitDao();
            ClientDao cd = new ClientDao();
            while (resultat.next()) {
                p.setId(resultat.getInt(1));
                p.setTitre(resultat.getString(2));
                p.setCategorie(cpd.findOne(resultat.getInt(3)));
                p.setMarque(resultat.getString(4));
                p.setPrix(resultat.getFloat(5));
                p.setDateFin(resultat.getDate(6));
                p.setEstValide(resultat.getInt(7));
                p.setQuantite(resultat.getInt(8));
                p.setEstAchetee(resultat.getInt(9));
                p.setPict(resultat.getString(10));
                p.setDescription(resultat.getString(11));
                p.setClient(cd.findOne(resultat.getInt(12)));
            }
            return p;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    }

    

     public List<Produit> findbyidclien(int id) {

        List<Produit> listeProduit = new ArrayList<>();
        CategorieProduitDao cpd = new CategorieProduitDao();
        ClientDao cd = new ClientDao();
        String requete = "select * from produit where id_cliente=" + id;
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Produit p = new Produit();
                p.setId(resultat.getInt(1));
                p.setTitre(resultat.getString(2));
                p.setCategorie(cpd.findOne(resultat.getInt(3)));
                p.setMarque(resultat.getString(4));
                p.setPrix(resultat.getFloat(5));
                p.setDateFin(resultat.getDate(6));
                p.setEstValide(resultat.getInt(7));
                p.setQuantite(resultat.getInt(8));
                p.setEstAchetee(resultat.getInt(9));
                p.setPict(resultat.getString(10));
                p.setDescription(resultat.getString(11));
               // p.setClient(cd.findOne(resultat.getInt(12)));
                listeProduit.add(p);
            }
            return listeProduit;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }

    ////STATS////
    public float somme_prix_produit() {
        float x = 0;

        try {
            String queryString = "select sum(prix) as prix_total from produit where estAchetee=1";
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            while (rs.next()) {

                x = rs.getInt("prix_total");
                return x;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x;
    }

    public int total_produit_vendu() {
        int x = 0;

        try {
            String queryString = "select sum(est_achete) as nombre_total from produit ";
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            while (rs.next()) {

                x = rs.getInt("nombre_total");
                return x;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x;
    }

    public int total_produit_enstock() {
        int z = 0;

        try {
            String queryString = "select sum(estAchetee) as nombre_total ,count(*) as total from produit ";
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            while (rs.next()) {

                int x = rs.getInt("nombre_total");
                int y = rs.getInt("total");
                z = y - x;
                return z;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return z;
    }

    public String meilleur_produit_vendu(String categorie) {
        String z = "";

        try {
            String queryString = "select max(nb_pts)  ,marque  from produit where categorie='" + categorie + "'";
            Statement stm = connexion.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            while (rs.next()) {

                z = rs.getString("marque");
                return z;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return z;
    }

    /////saifeddine
       
 public List<Produit> findProduitEnchere() {
        List<Produit> listeProduit = new ArrayList<>();
        String requete = "Select p.id_produit,"
                + " p.titre,"
                + " p.categorie,"
                + " p.marque,"
                + " p.prix,"
                + " p.date_fin,"
                + " p.est_valide,"
                + " p.quantite,"
                + " p.estAchetee,"
                + " p.pict,"
                + " p.description,"
                + " p.id_cliente"
                + " from enchere e left join produit p on e.id_produit where e.id_produit = p.id_produit ";
        try {
            Statement statement = connexion.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            CategorieProduitDao cpd = new CategorieProduitDao();
           ClientDao cda = new ClientDao();
            while (resultat.next()) {
                Produit p = new Produit();
                 
                p.setId(resultat.getInt(1));
                p.setTitre(resultat.getString(2));
                p.setCategorie(cpd.findOne(resultat.getInt(3)));
                p.setMarque(resultat.getString(4));
                p.setPrix(resultat.getFloat(5));
                p.setDateFin(resultat.getDate(6));
                p.setEstValide(resultat.getInt(7));
                p.setQuantite(resultat.getInt(8));
                p.setEstAchetee(resultat.getInt(9));
                p.setPict(resultat.getString(10));
                p.setDescription(resultat.getString(11));
                p.setClient(cda.findOne(resultat.getInt(12)));
                listeProduit.add(p);
            }
            return listeProduit;
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
    }
    
    
    
}
