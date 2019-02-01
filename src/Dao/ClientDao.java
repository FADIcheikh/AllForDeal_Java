/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Client;
import Technique.Connexion;
import Technique.SHA;
import com.sun.org.apache.regexp.internal.REUtil;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.relation.Role;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author AZIZ
 */
public class ClientDao {

    //Salt + pass 
    SHA sha = new SHA();

    public Connection conn;

    public ClientDao() {
        conn = Connexion.getIstanceConnexion();
    }

    public void ajouterClient(Client client, String motdepass) {
        String strSalt = sha.getStringSalt();

        String hashpass = sha.get_SHA_512_SecurePassword(motdepass, strSalt);
        try {
            PreparedStatement st = conn.prepareStatement("insert into client"
                    + "(last_name,"
                    + "first_name,"
                    + "date_naissance,"
                    + "telephone,"
                    + "adresse,"
                    + "email,"
                    + "username,"
                    + "password,"
                    + "sexe,"
                    + "salt,"
                    + "enabled,"
                    + "locked,"
                    + "expired,"
                    + "credentials_expired,"
                    + "roles,"
                    + "date_inscription)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_date())");

            st.setString(1, client.getLastName());
            st.setString(2, client.getFirstName());
            st.setDate(3, client.getDateNaissance());
            st.setInt(4, client.getTelephone());
            st.setString(5, client.getAdresse());
            st.setString(6, client.getEmail());
            st.setString(7, client.getUsername());
            st.setString(8, hashpass);
//            st.setInt(9,client.getNote());
//            st.setString(10,client.getCompetence());
//            st.setString(9,"client" );
            st.setInt(9, client.getSexe());
            st.setString(10, strSalt);
            st.setBoolean(11, client.isEnabled());
            st.setBoolean(12, client.isLocked());
            st.setBoolean(13, client.isExpired());
            st.setBoolean(14, client.isCredentialsExpired());
            st.setString(15, client.getRoles());

            System.out.println(st);

            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //date last login
    public void modifierdateloginClient(String username, String password) {
        try {
            
             String strSalt = sha.getStringSalt();

        String hashpass = sha.get_SHA_512_SecurePassword(password, strSalt);

            String queryString = "update client set last_login=current_date() where username='"+ username +"' and password='"+ hashpass +"' ";
            Statement stm = conn.createStatement();
            stm.executeUpdate(queryString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
//info cnx

    public void ModifierClienCnx(Client client) {
        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE `client` SET `username`=?,`password`=?,`email`=?  WHERE `id`='" + client.getId() + "'");
            ps.setString(1, client.getUsername());
            ps.setString(2, client.getPassword());
            ps.setString(3, client.getEmail());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error:" + ex);
        }
    }

    //infos perso
    public void ModifierClienPerso(Client client) {
        try {

            PreparedStatement ps = conn.prepareStatement("UPDATE `client` SET `last_name`=?,`first_name`=?,`date_naissance`=?,`telephone`=?,`adresse`=?  WHERE `id`='" + client.getId() + "'");
            ps.setString(1, client.getLastName());
            ps.setString(2, client.getFirstName());
            ps.setDate(3, client.getDateNaissance());
            ps.setInt(4, client.getTelephone());
            ps.setString(5, client.getAdresse());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("SQL Error:" + ex);
        }
    }

    public void supprimerClient(int id_personne) {
        try {

            String queryString = "delete  FROM client where ='" + id_personne + "'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    // recuperer salt depuis la BD 
    public String salt(String login) {
        String salt = "";
        try {
            String requeteLogin = "select salt from client where username = '" + login + "'";
            PreparedStatement pst = conn.prepareStatement(requeteLogin);
            ResultSet rs = pst.executeQuery(requeteLogin);
            if (rs.next()) {

                salt = rs.getString("salt");

                return salt;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salt;

    }

    public String roles(String login) {

        String role = "";
        try {
            String requeteLogin = "select roles from client where username = '" + login + "'";
            PreparedStatement pst = conn.prepareStatement(requeteLogin);
            ResultSet rs = pst.executeQuery(requeteLogin);
            if (rs.next()) {
                role = rs.getString("roles");

                return role;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return role;

    }

    public boolean testerRoleAdmin(String str) {
        boolean b = false;
        String str2 = "ROLE_SUPER_ADMIN";

        try {
            String requeteLogin = "select INSTR('" + str2 + "','" + str + "') as test from client";
            PreparedStatement pst = conn.prepareStatement(requeteLogin);
            ResultSet rs = pst.executeQuery(requeteLogin);
            while (rs.next()) {

                int x = rs.getInt("test");
                if (x == 0) {
                    b = false;
                } else {
                    b = true;
                }
            }

        } catch (Exception e) {
        }

        return b;
    }

    public boolean verifierPasswordutili(String login, String pass) {
        boolean b = false;

        String strSalt = salt(login);

        String hashpass = sha.get_SHA_512_SecurePassword(pass, strSalt);

        try {
            String requeteLogin = "select * from client where username = '" + login + "' and password ='" + hashpass + "'";
            PreparedStatement pst = conn.prepareStatement(requeteLogin);
            ResultSet rs = pst.executeQuery(requeteLogin);
            if (rs.next()) {
                b = true;

                /*MD5 md5 = new MD5();
                 if (md5.getHash(password).equals(pass)){
                 b=true;
                 }*/
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return b;

    }

    //client et categorie
    public List<Client> findAll() {
        try {
            List<Client> listeService = new ArrayList();
            PreparedStatement ps = conn.prepareStatement("select * from client");
            ResultSet rs = ps.executeQuery();

            ClientDao cd = new ClientDao();
            while (rs.next()) {
                Client c = new Client();

                c.setId(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setUsernameCanonical(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setEmailCanonical(rs.getString(5));
                c.setEnabled(rs.getBoolean(6));
                c.setSalt(rs.getString(7));
                c.setPassword(rs.getString(8));
                c.setLastLogin(rs.getDate(9));
                c.setLocked(rs.getBoolean(10));
                c.setExpired(rs.getBoolean(11));
                c.setExpiresAt(rs.getDate(12));
                c.setConfirmationToken(rs.getString(13));
                c.setPasswordRequestedAt(rs.getDate(14));
                c.setRoles(rs.getString(15));
                c.setCredentialsExpired(rs.getBoolean(16));
                c.setCredentialsExpireAt(rs.getDate(17));
                c.setAdresse(rs.getString(18));
                c.setFirstName(rs.getString(19));
                c.setLastName(rs.getString(20));
                c.setTelephone(rs.getInt(21));
                c.setSexe(rs.getInt(22));
                c.setDateNaissance(rs.getDate(23));
                c.setDateInscription(rs.getDate(24));
                c.setNbPoint(rs.getInt(25));

                listeService.add(c);

            }
            return listeService;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    //client et categorie
    public Client findOne(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from client where id=" + id);
            ResultSet rs = ps.executeQuery();
//            CategorieServiceDao csd = new CategorieServiceDao();
//            ClientDao cd = new ClientDao();
            Client c = new Client();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setUsername(rs.getString(2));
                c.setUsernameCanonical(rs.getString(3));
                c.setEmail(rs.getString(4));
                c.setEmailCanonical(rs.getString(5));
                c.setEnabled(rs.getBoolean(6));
                c.setSalt(rs.getString(7));
                c.setPassword(rs.getString(8));
                c.setLastLogin(rs.getDate(9));
                c.setLocked(rs.getBoolean(10));
                c.setExpired(rs.getBoolean(11));
                c.setExpiresAt(rs.getDate(12));
                c.setConfirmationToken(rs.getString(13));
                c.setPasswordRequestedAt(rs.getDate(14));
                c.setRoles(rs.getString(15));
                c.setCredentialsExpired(rs.getBoolean(16));
                c.setCredentialsExpireAt(rs.getDate(17));
                c.setAdresse(rs.getString(18));
                c.setFirstName(rs.getString(19));
                c.setLastName(rs.getString(20));
                c.setTelephone(rs.getInt(21));
                c.setSexe(rs.getInt(22));
                c.setDateNaissance(rs.getDate(23));
                c.setDateInscription(rs.getDate(24));
                c.setNbPoint(rs.getInt(25));

            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    //true si le cmpte n'est pas bolqué

    public boolean veriBanLocked(String login, String pass) {
        boolean b = false;
        String strSalt = salt(login);

        String hashpass = sha.get_SHA_512_SecurePassword(pass, strSalt);
        try {

            PreparedStatement ps = conn.prepareStatement("select locked,enabled from client where username ='" + login + "' and password='" + hashpass + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int lock = rs.getInt("locked");
                int ban = rs.getInt("enabled");
                System.out.println(lock + " test " + ban);
                if ((ban == 1) && (lock == 0)) {
                    b = true;
                } else {
                    b = false;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    //return id client 
    public int idClient(String username, String password) {
        int id = 0;
        try {
            String requeteLogin = "select id from client where username = '" + username + "' and password ='" + password + "'";
            PreparedStatement pst = conn.prepareStatement(requeteLogin);
            ResultSet rs = pst.executeQuery(requeteLogin);
            if (rs.next()) {
                id = rs.getInt("id");

                System.out.println("test id" + id);
                return id;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    /////MAIL////
    public String mail_client(String username, String password) {
        String mail = "";
        try {
            String requeteLogin = "select email from client where username = '" + username + "' and password ='" + password + "'";
            PreparedStatement pst = conn.prepareStatement(requeteLogin);
            ResultSet rs = pst.executeQuery(requeteLogin);
            if (rs.next()) {
                mail = rs.getString("email");
                return mail;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mail;
    }

//    /STATS////
    public float moyenne_age() {
        int z = 0;
        try {
            String queryString = "Select sum((Year(current_date()) - YEAR(date_naissance))) as age , count(*) as total from client ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);

            while (rs.next()) {
                int x = rs.getInt("age");
                int y = rs.getInt("total");
                z = x / y;
                return z;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return z;
    }

    //////
    public int afficher_evolution_annee(int annee_inscription) {
        int x = 0;
        try {

            String queryString = "SELECT count(*) as tout FROM client where  YEAR(date_inscription)='" + annee_inscription + "'  ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);

            while (rs.next()) {

                x = rs.getInt("tout");

                return x;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return x;

    }

    //////
    public int afficher_evolution_mois(int mois_inscription, int annee) {
        int x = 0;
        try {

            String queryString = "SELECT count(*) as tout FROM client where  MONTH(date_inscription)='" + mois_inscription + "' AND YEAR(date_inscription)='" + annee + "'  ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);

            while (rs.next()) {

                x = rs.getInt("tout");

                return x;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return x;

    }

    /////
    public int afficher_evolution_date(String date_inscription) {
        int x = 0;
        try {

            String queryString = "SELECT count(*) as tout FROM client where date_inscription like '" + date_inscription + "'  ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);

//        int x =rs.getInt("ban");
            while (rs.next()) {

                x = rs.getInt("tout");

                return x;

//          DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//                 dataset.setValue(a,"male","male");
//                 dataset.setValue(b,"female","female");
//             XYSeries serie = new XYSeries("");
//             serie.add(x, 1);
//             serie.add(x, 2);
//             serie.add(x, 3);
//             serie.add(x, 4);
//             serie.add(x, 5);
//             serie.add(x, 6);
//             serie.add(x, 7);
//             serie.add(x, 8);
//             serie.add(x, 9);
//             serie.add(x, 10);
//             serie.add(x, 11);
//             serie.add(x, 12);
//             
//             XYSeriesCollection dataset1 = new XYSeriesCollection();
//             dataset1.addSeries(serie);
//                 
//                 JFreeChart chart1=ChartFactory.createXYLineChart("evolution inscription", "evolution", "month", dataset1, PlotOrientation.HORIZONTAL, true, true, true);
//                 ChartFrame frame = new ChartFrame("evolution", chart1);
//                 frame.setSize(450,350);
//                 frame.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return x;

    }

    //////
    public void afficher_stat_sexe() {
        try {

            String queryString = "SELECT count(*) as tout ,sum(sexe) as male FROM client  ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);

//        int x =rs.getInt("ban");
            while (rs.next()) {
                int y = rs.getInt("male");
                int x = rs.getInt("tout");
                int z = x - y;
                float a;
                float b;

                a = (float) y / x;
                b = (float) z / x;

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.setValue(a, "male", "male");
                dataset.setValue(b, "female", "female");

//                 //3D
//                 JFreeChart chart1=ChartFactory.createBarChart3D("sexe", "sexe", "pourcentage", dataset, PlotOrientation.VERTICAL, false, true, false);     
//                 ChartFrame frame1= new ChartFrame("proportion gender", chart1);
//                 chart1.setBorderVisible(true);
//                        //ajout menu 
//                 JMenuBar menu = new JMenuBar();
//                 menu.add(new JButton("hhh"));
//                 frame1.setJMenuBar(menu);
//                 
//                 
//
//           
//
//              
//                 frame1.setVisible(true);  
//                 frame1.setSize(450,350);
//                
//                 //2D
//                 JFreeChart chart=ChartFactory.createBarChart("sexe", "sexe", "pourcentage", dataset, PlotOrientation.VERTICAL, false, true, false);
//                 CategoryPlot p = chart.getCategoryPlot();
//                 ChartFrame frame= new ChartFrame("proportion gender", chart);
//                 frame.setVisible(true);
//                 frame.setSize(450,350); 
//                 //triangle
//                  JFreeChart chart2=ChartFactory.createAreaChart("sexe", "sexe", "pourcentage", dataset, PlotOrientation.VERTICAL, true, true, true);
//                 CategoryPlot p2 = chart.getCategoryPlot();
//                 ChartFrame frame2= new ChartFrame("proportion gender", chart2);
//                 frame2.setVisible(true);
//                 frame2.setSize(450,350);
//                  //triangle
//                  JFreeChart chart3=ChartFactory.createStackedBarChart3D("sexe", "sexe", "pourcentage", dataset, PlotOrientation.VERTICAL, true, true, true);
//                 CategoryPlot p3 = chart.getCategoryPlot();
//                
//                 ChartFrame frame3= new ChartFrame("proportion gender", chart3);
//                 frame3.setVisible(true);
//                 frame3.setSize(450,350);
                //cercle
                DefaultPieDataset dataset1 = new DefaultPieDataset();
                dataset1.setValue("male", a);
                dataset1.setValue("female", b);
                JFreeChart chart4 = ChartFactory.createPieChart3D("sexe", dataset1, true, true, Locale.FRENCH);

                ChartFrame frame4 = new ChartFrame("proportion gender", chart4);
                frame4.setVisible(true);
                frame4.setSize(450, 350);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    //////

    public int nb_clients() {
        int i = 0;
        try {

            String queryString = "SELECT count(*) as tout  FROM client  ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);
            while (rs.next()) {
                i = rs.getInt("tout");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return i;
    }

    /////
    public void afficher_stat_ban() {
        try {

            String queryString = "SELECT count(*) as tout ,sum(locked) as banned FROM client  ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(queryString);

//        int x =rs.getInt("ban");
            while (rs.next()) {
                int y = rs.getInt("banned");
                int x = rs.getInt("tout");
                int z = x - y;

                float a;
                float b;

                a = (float) y / x;
                b = (float) z / x;

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                dataset.setValue(a, "not banned", "not banned");
                dataset.setValue(b, "banned", "banned");
                JFreeChart chart = ChartFactory.createBarChart("ban", "ban", "pourcentage", dataset, PlotOrientation.VERTICAL, false, true, false);
                CategoryPlot p = chart.getCategoryPlot();
                chart.getBorderPaint();
                ChartFrame frame = new ChartFrame("proportion ban", chart);
                frame.setVisible(true);
                frame.setSize(450, 350);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
