/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Dao.ClientDao;
import Dao.ReclamationDao;

import Entities.Client;
import Entities.Reclamation;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author macbookpro
 */
public class GestionReclamation extends javax.swing.JFrame {

    int[] ids;
    List<Reclamation> listeReclamation;

    /**
     * Creates new form AjoutAdmin
     */
    public GestionReclamation() {
        initComponents();

        recherche.setText("");
        recherche.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTableReclamation();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTableReclamation();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTableReclamation();
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

        titre = new javax.swing.JTextField();
        etat = new javax.swing.JTextField();
        retour = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        recherche = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textReclamation = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        reclamationId = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titreActionPerformed(evt);
            }
        });
        getContentPane().add(titre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 150, 40));

        etat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                etatActionPerformed(evt);
            }
        });
        getContentPane().add(etat, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 340, 180, 40));

        retour.setText("Retour");
        retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retourActionPerformed(evt);
            }
        });
        getContentPane().add(retour, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 620, -1, -1));

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
        updateTableReclamation();
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, 180));

        recherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercheActionPerformed(evt);
            }
        });
        getContentPane().add(recherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 290, 180, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/traiter.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 620, -1, -1));

        jButton1.setText("Traiter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 630, -1, -1));

        textReclamation.setColumns(20);
        textReclamation.setRows(5);
        jScrollPane2.setViewportView(textReclamation);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 460, 200));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        reclamationId.setText("0");
        getContentPane().add(reclamationId, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, 10, -1));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rec.jpg"))); // NOI18N
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/rec.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void titreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titreActionPerformed

    private void rechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rechercheActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() != -1) {
            Reclamation r;
            r = new Reclamation(
                    listeReclamation.get(jTable1.getSelectedRow()).getId_reclamation(),
                    listeReclamation.get(jTable1.getSelectedRow()).getId_client(),
                    titre.getText(),
                    etat.getText(),
                    textReclamation.getText(),
                    new java.sql.Date(1990)
            );
            ClientDao cd = new ClientDao();

            Client c = cd.findOne(r.getId_client());
            titre.setText("");
            etat.setText("");
            textReclamation.setText("");
            ReclamationDao rd = new ReclamationDao();
            r.setEtat("Traitée !");
            rd.ModifierReclamation(r);

            updateTableReclamation();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void etatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_etatActionPerformed
        // TODO add your handl^ing code here:
    }//GEN-LAST:event_etatActionPerformed

    private void retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retourActionPerformed
        AdminInterface cg = new AdminInterface();
        cg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_retourActionPerformed

    /**
     * @param args the command line arguments
     */
    private void updateTableReclamation() {

        ReclamationDao rd = new ReclamationDao();
        listeReclamation = rd.findAll();
        ClientDao cd = new ClientDao();

        int i;
        int size = listeReclamation.size();
        int j = 0;
        for (i = 0; i < size; i++) {
            if (!(String.valueOf(listeReclamation.get(j).getId_reclamation()).toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeReclamation.get(j).getTitre().toLowerCase().contains(recherche.getText().toLowerCase())
                        || listeReclamation.get(j).getEtat().toLowerCase().contains(recherche.getText().toLowerCase()))) {
                listeReclamation.remove(listeReclamation.get(j));
            } else {
                j++;
            }
        }
        ids = new int[listeReclamation.size()];
        if (listeReclamation.size() > 0) {

            String[][] a = new String[listeReclamation.size()][5];
            for (i = 0; i < listeReclamation.size(); i++) {
                Client c = cd.findOne(listeReclamation.get(i).getId_client());
                a[i][0] = listeReclamation.get(i).getTitre();
                a[i][1] = String.valueOf(listeReclamation.get(i).getDate());
                a[i][2] = listeReclamation.get(i).getEtat();
                a[i][3] = c.getLastName() + " " + c.getFirstName();

            }
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        a, new String[]{"Titre", "Date", "Etat", "Client"}));
        } else {
            String[][] a = new String[0][2];
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        a, new String[]{"Titre", "Date", "Etat", "Client"}));
        }

        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {

            if (jTable1.getSelectedRow() >= 0) {
                titre.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
               // date.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                etat.setText((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2));
                textReclamation.setText(listeReclamation.get(jTable1.getSelectedRow()).getDescription());
            }

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
            java.util.logging.Logger.getLogger(GestionReclamation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionReclamation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionReclamation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionReclamation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionReclamation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JTextField etat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField recherche;
    private javax.swing.JLabel reclamationId;
    private javax.swing.JButton retour;
    private javax.swing.JTextArea textReclamation;
    private javax.swing.JTextField titre;
    // End of variables declaration//GEN-END:variables
}
