/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.ClientDao;
import Dao.PanierDao;
import Dao.ProduitDao;
import Dao.SendEmailDao;
import Entities.Client;
import Entities.MAIL;
import Entities.Panier;
import Entities.Produit;
import static GUI.inscriptionpage.NL;
import Technique.Connexion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AZIZ
 */
public class connection extends javax.swing.JFrame {

    public static final String NL = System.getProperty("line.separator");
    SendEmailDao mail = new SendEmailDao();
    MAIL mail_client = new MAIL();
///////////////////////////////////////////
    Client client = new Client();
    Panier panier = new Panier();
    Produit produit = new Produit();
/////////////////////////////////////////////
    PanierDao p = new PanierDao();
    ProduitDao x = new ProduitDao();
    ClientDao c = new ClientDao();
    ///////////////////////////////// 

    /**
     * Creates new form connection
     */
    public connection() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cnx = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 120, 30));
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 120, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/seco.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/sinscrire.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 200, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/motdepasseoub.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 310, 30));

        cnx.setText("Connexion");
        cnx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnxActionPerformed(evt);
            }
        });
        getContentPane().add(cnx, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, -1, -1));

        jButton1.setText("jButton1");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, -1, -1));

        jButton2.setText("jButton2");
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 210, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/authent.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void cnxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnxActionPerformed
        // TODO add your handling code here:
        String login = username.getText();
        String pass = password.getText();
        System.out.println("010101");
        try {
            System.out.println("0000");
            if (c.verifierPasswordutili(login, pass)) {
                System.out.println("1111");

                if (c.veriBanLocked(login, pass)) //Si client 
                {System.out.println("nigga i'm here");
                    if (!(c.testerRoleAdmin(c.roles(login)))) {
                        System.out.println("222");
                        c.modifierdateloginClient(login, pass);
                        homep h = new homep();
                        h.setVisible(true);
                        LoginInfo.setClient(c.findOne(c.idClient(login, pass)));
                        dispose();

                    } //Si admin 
                    else {
                        c.modifierdateloginClient(login, pass);
//                    home2  h2 =new home2();
//                  h2.setVisible(true);
                        dispose();

                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "compte désactivé ou bloqué contactez l'administrateur ");
                    username.setText(null);
                    password.setText(null);
                }
//                 dispose();}
//                    else{
//                          mail_client.setMail_client(c.mail_client(login, pass));
//                    mail_client.setContenu("Cher Client,"+bNL+NL+NL
//                            + "Une tentative deconnexion depuis un appareil "+System.getProperty("os.name")+" a été détectée..."+NL
//                            +"Si vous n'etes pas à l'origine de cette action veuillez contactez l'administrateur ALL FOR DEAL "+NL+NL+NL+NL
//                            +"L'équipe ALL FOR DEAL");
//                    mail_client.setSubject("Tenative de connexion depuis un appareil "+System.getProperty("os.name"));
//                 mail.send2_client(mail_client);
//                  home  h=new home();
//                  h.setVisible(true);
//                 dispose();
//                    }
//                
//                }
//                else{
//                    
//                    jOptionPane1.showMessageDialog(null, "Compte désactivé"+NL+"Contactez l'administrateur", "Erreur", JOptionPane.ERROR_MESSAGE);
//                    Contact_Gmail g = new Contact_Gmail();
//                    g.setVisible(true);
//                jTextField1.setText(null);
//               jPasswordField1.setText(null);
//                }
            } else {
//               
                JOptionPane.showMessageDialog(rootPane, "Login ou Password invalides");
                username.setText(null);
                password.setText(null);

            }
        } catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cnxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(connection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new connection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cnx;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
