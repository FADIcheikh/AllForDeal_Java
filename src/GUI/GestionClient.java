/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.ClientDao;
import Entities.Client;

import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author macbookpro
 */
public class GestionClient extends javax.swing.JFrame {

    /**
     * Creates new form GestionClient
     */
    public GestionClient() {
        initComponents();
        recherche.setText("");
        recherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTableClient();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTableClient();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTableClient();
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        recherche = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        retour = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        updateTableClient();
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 700, 180));

        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });
        getContentPane().add(recherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 160, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/detailcompte1.jpg"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, 160, 60));

        retour.setText("Retour");
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });
        getContentPane().add(retour, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 610, -1, -1));

        jButton1.setText("Detail Compte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, -1, -1));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 540, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btdes.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 540, -1, -1));

        jButton3.setText("Désactiver compte");
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, -1, 30));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Gc.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        DetailClient JCon = new DetailClient(Integer.parseInt((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));

        JCon.setVisible(true);
        dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
//        MenuAdmin cg = new MenuAdmin();
//        cg.setVisible(true);
//        this.hide();
    }//GEN-LAST:event_retourActionPerformed

    /**
     * @param args the command line arguments
     */
    private void updateTableClient() {

        ClientDao ad = new ClientDao();
        List<Client> listeClient = ad.findAll();

        int i;
        int size = listeClient.size();
        int j = 0;
        for (i = 0; i < size; i++) {
            if (!(String.valueOf(listeClient.get(j).getId()).toLowerCase().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeClient.get(j).getLastName().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeClient.get(j).getFirstName().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeClient.get(j).getAdresse().toLowerCase().contains(recherche.getText().toLowerCase())
                        || String.valueOf(listeClient.get(j).getTelephone()).toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeClient.get(j).getUsername().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeClient.get(j).getPassword().toLowerCase().contains(recherche.getText().toLowerCase()))) {
                listeClient.remove(listeClient.get(j));
            } else {
                j++;
            }
        }
        if (listeClient.size() > 0) {
            String[][] a = new String[listeClient.size()][8];
            for (i = 0; i < listeClient.size(); i++) {
                a[i][0] = String.valueOf(listeClient.get(i).getId());
                a[i][1] = listeClient.get(i).getLastName();
                a[i][2] = listeClient.get(i).getFirstName();
                a[i][3] = listeClient.get(i).getAdresse();
                a[i][4] = String.valueOf(listeClient.get(i).getDateNaissance());
                a[i][5] = String.valueOf(listeClient.get(i).getTelephone());
                a[i][6] = String.valueOf(listeClient.get(i).getNbPoint());
                a[i][7] = listeClient.get(i).getUsername();
            }
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        a, new String[]{"ID", "Nom", "Prenom", "E-Mail", "Date de naissance", "Telephone", "nbre de points", "login"}));
        } else {
            String[][] a = new String[0][8];
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        a, new String[]{"ID", "Nom", "Prenom", "E-Mail", "Date de aissacne", "Telephone", "nbre de points", "login"}));
        }
        ///////////////////dynamic serch

    }

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
            java.util.logging.Logger.getLogger(GestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GestionClient().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField recherche;
    private javax.swing.JButton retour;
    // End of variables declaration//GEN-END:variables
}