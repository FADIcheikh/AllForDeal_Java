/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Technique;

import Dao.PubliciteDao;
import Entities.Publicite;
import GUI.ListePub;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author lenovo i5
 */
public class PubPanel extends JPanel {

    static String path;

    public PubPanel(Publicite p, ListePub f) {
        File source = new File(p.getPath());
        try {
            Image image = ImageIO.read(source);
            Image img = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel();
            label.setIcon(icon);
            this.add(label);

        } catch (IOException ex) {
            Logger.getLogger(PubPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.add(new JLabel(p.getTitre()));
        this.add(new JLabel(p.getDescription()));

        JButton b = new JButton("Supprimer Publicitée");

        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PubliciteDao pd = new PubliciteDao();
                pd.supprimerPublicite(p);
                f.refresh();
            }
        });
        this.add(b);

    }

}
