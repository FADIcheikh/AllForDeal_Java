/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.ServiceDao;
import Entities.Service;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author macbookpro
 */
public class GestionService extends javax.swing.JFrame {

    List<Service> listeService;

    /**
     * Creates new form GestionClient
     */
    public GestionService() {
        initComponents();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    null, new String[]{"titre", "Categorie", "marque", "prix", "duree produit", "quantité", "Etat", "Vendu", "Proprietaire"}));
        updateTableService();
        recherche.setText("");
        recherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTableService();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTableService();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTableService();
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
        retour = new javax.swing.JButton();
        recherche = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ProduitId = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 708));
        setResizable(false);
        getContentPane().setLayout(null);

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
        updateTableService();
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(130, 270, 700, 180);

        retour.setText("Retour");
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });
        getContentPane().add(retour);
        retour.setBounds(160, 520, 90, 30);

        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });
        getContentPane().add(recherche);
        recherche.setBounds(550, 190, 160, 30);

        jButton2.setText("Effacer Service");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(640, 520, 135, 30);
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 0, 0, 0);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/gestion de services.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1038, 716));
        jLabel1.setMinimumSize(new java.awt.Dimension(1038, 716));
        jLabel1.setPreferredSize(new java.awt.Dimension(1038, 716));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 1038, 680);

        ProduitId.setText("jLabel1");
        getContentPane().add(ProduitId);
        ProduitId.setBounds(120, 150, 34, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed

    }//GEN-LAST:event_rechercheActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        ServiceDao pd = new ServiceDao();
        Service s = listeService.get(jTable1.getSelectedRow());
        pd.supprimerProduit(s);
        listeService.remove(s);
        updateTableService();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
        AdminInterface cg = new AdminInterface();
        cg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_retourActionPerformed

    /**
     * @param args the command line arguments
     */
    private void updateTableService() {

        ServiceDao sd = new ServiceDao();
        listeService = sd.findAll();

        int i;
        int size = listeService.size();
        int j = 0;

        for (i = 0; i < size; i++) {
            if (!(listeService.get(j).getTitre().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeService.get(j).getCategorie().getNom().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeService.get(j).getCompetence().getNom().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeService.get(j).getClient().getFirstName().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeService.get(j).getClient().getLastName().toLowerCase().contains(recherche.getText().toLowerCase()))) {
                listeService.remove(listeService.get(j));

            } else {
                j++;
            }
        }
        if (listeService.size() > 0) {
            String[][] a = new String[listeService.size()][5];
            for (i = 0; i < listeService.size(); i++) {
                a[i][0] = listeService.get(i).getTitre();
                a[i][1] = listeService.get(i).getCategorie().getNom();
                a[i][2] = String.valueOf(listeService.get(i).getDate_fin());
                a[i][3] = listeService.get(i).getCompetence().getNom();
                a[i][4] = listeService.get(i).getClient().getLastName() + " " + listeService.get(i).getClient().getFirstName();

            }
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        a, new String[]{"Titre", "Categorie", "Date D'expiration", "Contre Service", "Client"}));
        } else {
            String[][] a = new String[0][2];
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        a, new String[]{"Titre", "Categorie", "Date D'expiration", "Contre Service", "Client"}));
        }

        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {

        });

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
            java.util.logging.Logger.getLogger(GestionService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionService.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GestionService().setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ProduitId;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField recherche;
    private javax.swing.JButton retour;
    // End of variables declaration//GEN-END:variables
}
