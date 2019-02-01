/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Service;
import Technique.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AZIZ
 */
public class ServiceDao {

    public Connection conn = Connexion.getIstanceConnexion();
    private Object connexion;

    public void ajouterService(Service service) {
        try {

            PreparedStatement st = conn.prepareStatement("INSERT INTO `service`(`titre`, `id_categorie`, `date_fin`, `description`, `competence`, `id_client`, `imgServ`) VALUES (?,?,?,?,?,?,?)");

            st.setString(1, service.getTitre());
            st.setInt(2, service.getCategorie().getId());
            st.setDate(3, service.getDate_fin());
            st.setString(4, service.getDescription());
            st.setInt(5, service.getCompetence().getId());
            st.setInt(6, service.getClient().getId());
            st.setString(7, service.getImgServ());

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //client et categorie
    public List<Service> findAll() {
        try {
            List<Service> listeService = new ArrayList();
            PreparedStatement ps = conn.prepareStatement("select * from service");
            ResultSet rs = ps.executeQuery();
            CategorieServiceDao csd = new CategorieServiceDao();
            ClientDao cd = new ClientDao();
            while (rs.next()) {
                Service s = new Service();

                s.setId(rs.getInt(1));
                s.setTitre(rs.getString(2));
                s.setCategorie(csd.findOne(rs.getInt(3)));
                s.setDate_fin(rs.getDate(4));
                s.setDescription(rs.getString(5));
                s.setCompetence(csd.findOne(rs.getInt(6)));
                s.setClient(cd.findOne(rs.getInt(7)));
                s.setImgServ(rs.getString(8));

                listeService.add(s);

            }
            return listeService;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public List<Service> findByClient(int id) {
        try {
            List<Service> listeService = new ArrayList();
            PreparedStatement ps = conn.prepareStatement("select * from service where id_client="+id);
            ResultSet rs = ps.executeQuery();
            CategorieServiceDao csd = new CategorieServiceDao();
            ClientDao cd = new ClientDao();
            while (rs.next()) {
                Service s = new Service();

                s.setId(rs.getInt(1));
                s.setTitre(rs.getString(2));
                s.setCategorie(csd.findOne(rs.getInt(3)));
                s.setDate_fin(rs.getDate(4));
                s.setDescription(rs.getString(5));
                s.setCompetence(csd.findOne(rs.getInt(6)));
                s.setClient(cd.findOne(rs.getInt(7)));
                s.setImgServ(rs.getString(8));

                listeService.add(s);

            }
            return listeService;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    

    //client et categorie
    public Service findOne(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from service where id_service=" + id);
            ResultSet rs = ps.executeQuery();
            CategorieServiceDao csd = new CategorieServiceDao();
            ClientDao cd = new ClientDao();
            Service s = new Service();
            while (rs.next()) {

                s.setId(rs.getInt(1));
                s.setTitre(rs.getString(2));
                s.setCategorie(csd.findOne(rs.getInt(3)));
                s.setDate_fin(rs.getDate(4));
                s.setDescription(rs.getString(5));
                s.setCompetence(csd.findOne(rs.getInt(6)));
                s.setClient(cd.findOne(rs.getInt(7)));
                s.setImgServ(rs.getString(8));

            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public void modifierService(Service service) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE `service` SET `titre`=?,`id_categorie`=?,`date_fin`=?,`description`=?,`competence`=?,`id_client`=?,`imgServ`=? WHERE `id_service`=?");

            st.setString(1, service.getTitre());
            st.setInt(2, service.getCategorie().getId());
            st.setDate(3, service.getDate_fin());
            st.setString(4, service.getDescription());
            st.setInt(5, service.getCompetence().getId());
            st.setInt(6, service.getClient().getId());
            st.setString(7, service.getImgServ());
            st.setInt(8, service.getId());

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("yesss" + ex);
        }
    }

    public void supprimerProduit(Service service) {
        try {
            PreparedStatement st = conn.prepareStatement("Delete from service where id_service=?");

            st.setInt(1, service.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Service afficherdsc() {
        int i = 14;
        String x = "";
        do {

            try {
                PreparedStatement ps = conn.prepareStatement("select * from service where id_service=" + i);
                ResultSet rs = ps.executeQuery();
                CategorieServiceDao csd = new CategorieServiceDao();
                ClientDao cd = new ClientDao();
                Service s = new Service();
                while (rs.next()) {

                    s.setId(rs.getInt(1));
                    s.setTitre(rs.getString(2));
                    s.setCategorie(csd.findOne(rs.getInt(3)));
                    s.setDate_fin(rs.getDate(4));
                    s.setDescription(rs.getString(5));
                    s.setCompetence(csd.findOne(rs.getInt(6)));
                    s.setClient(cd.findOne(rs.getInt(7)));
                    s.setImgServ(rs.getString(8));

                }
                if (s.getTitre() != "") {
                    return s;
                } else {
                    i++;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceDao.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (x == "");
        return null;
    }
    
  
   
    
}
