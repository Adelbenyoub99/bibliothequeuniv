/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Connexion;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class AccueilBibliothéquaire extends javax.swing.JFrame {

    Connexion c = new Connexion();
    PreparedStatement s;
    ResultSet rs;
    Authentification a = new Authentification();

    /**
     * Creates new form AccueilBibliothéquaire
     */
    public AccueilBibliothéquaire() {
        initComponents();
        setTitle("Accueil bibliothéquaire");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\ressources admin\\NetBeansProjects\\Bibliothèque\\src\\Images\\Sans titre.png");
        setIconImage(icon.getImage());
        setLocationRelativeTo(this);
        lidentite.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bretour = new javax.swing.JButton();
        bouvrage = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bemprunt = new javax.swing.JButton();
        lidentite = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bretour.setBackground(new java.awt.Color(255, 255, 255));
        bretour.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bretour.setForeground(new java.awt.Color(153, 102, 0));
        bretour.setText("Deconnexion");
        bretour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bretourActionPerformed(evt);
            }
        });
        jPanel1.add(bretour, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        bouvrage.setBackground(new java.awt.Color(255, 255, 255));
        bouvrage.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        bouvrage.setForeground(new java.awt.Color(153, 102, 0));
        bouvrage.setText("Ouvrages");
        bouvrage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bouvrageActionPerformed(evt);
            }
        });
        jPanel1.add(bouvrage, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 170, 90));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Accueil bibliothécaire");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 250, -1));

        bemprunt.setBackground(new java.awt.Color(255, 255, 255));
        bemprunt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        bemprunt.setForeground(new java.awt.Color(153, 102, 0));
        bemprunt.setText("Emprunts");
        bemprunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bempruntActionPerformed(evt);
            }
        });
        jPanel1.add(bemprunt, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 160, 90));
        jPanel1.add(lidentite, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bouvrageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bouvrageActionPerformed
        GestOuvrage o = new GestOuvrage();
        o.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bouvrageActionPerformed

    private void bempruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bempruntActionPerformed
        Emprunt e = new Emprunt();
        e.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bempruntActionPerformed

    private void bretourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bretourActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment vous déconnecter ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {

                s = c.connectBdd().prepareStatement("UPDATE bibliothecaire SET connect=? WHERE matricule='" + lidentite.getText() + "'");
                s.setString(1, "Déconnecté");
                s.executeUpdate();
                a.setVisible(true);
                setVisible(false);

            } catch (SQLException ex) {
                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_bretourActionPerformed

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
            java.util.logging.Logger.getLogger(AccueilBibliothéquaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AccueilBibliothéquaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AccueilBibliothéquaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AccueilBibliothéquaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AccueilBibliothéquaire().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bemprunt;
    private javax.swing.JButton bouvrage;
    private javax.swing.JButton bretour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lidentite;
    // End of variables declaration//GEN-END:variables
}
