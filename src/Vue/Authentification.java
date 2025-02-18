/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.ConnectBibliothequaire;
import Modele.ConnectGestionnaire;
import Modele.Connexion;
import java.sql.PreparedStatement;
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
public class Authentification extends javax.swing.JFrame {

    ConnectBibliothequaire cb = new ConnectBibliothequaire();
    ConnectGestionnaire cu = new ConnectGestionnaire();
    Connexion c = new Connexion();
    PreparedStatement s;

    /**
     * Creates new form authentification
     */
    public Authentification() {
        initComponents();
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Authentification");
        ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\ressources admin\\NetBeansProjects\\Bibliothèque\\src\\Images\\Sans titre.png");
        setIconImage(icon.getImage());
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
        pfpass = new javax.swing.JPasswordField();
        bannuler = new javax.swing.JButton();
        bconnect = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfusername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lfond = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pfpass.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pfpass.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(pfpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 130, -1));

        bannuler.setBackground(new java.awt.Color(255, 255, 255));
        bannuler.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bannuler.setForeground(new java.awt.Color(153, 102, 0));
        bannuler.setText("Annuler");
        bannuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bannulerActionPerformed(evt);
            }
        });
        jPanel1.add(bannuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 220, -1));

        bconnect.setBackground(new java.awt.Color(255, 255, 255));
        bconnect.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bconnect.setForeground(new java.awt.Color(153, 102, 0));
        bconnect.setText("Se connecter");
        bconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bconnectActionPerformed(evt);
            }
        });
        jPanel1.add(bconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 220, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Password : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 70, 20));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Username : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 70, 20));

        tfusername.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfusername.setForeground(new java.awt.Color(153, 102, 0));
        tfusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfusernameActionPerformed(evt);
            }
        });
        jPanel1.add(tfusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 130, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Authentification");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 200, 30));
        jPanel1.add(lfond, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 310));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfusernameActionPerformed

    }//GEN-LAST:event_tfusernameActionPerformed

    private void bconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bconnectActionPerformed
        if (tfusername.getText().equals("gestionnaire")) {
            try {

                s = c.connectBdd().prepareStatement("UPDATE gestionnaire SET connect=? WHERE username='" + "gestionnaire" + "'");

                s.setString(1, "Connecté");
                s.executeUpdate();

                boolean tr = false;
                while (cu.rs.next()) {

                    if (tfusername.getText().equals(cu.rs.getString("username")) && pfpass.getText().equals(cu.rs.getString("password"))) {
                        tr = true;
                        break;
                    }
                }
                if (tr == true) {

                    AccueilGestionnaire ag = new AccueilGestionnaire();
                    String utig = tfusername.getText();
                    ag.lidentite.setText(utig);
                    ag.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Username ou password incorrect ", "Erreur", JOptionPane.ERROR_MESSAGE);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                s = c.connectBdd().prepareStatement("UPDATE bibliothecaire SET connect=? WHERE matricule='" + tfusername.getText() + "'");

                s.setString(1, "Connecté");
                s.executeUpdate();
                String a = tfusername.getText();
                boolean tr = false;
                while (cb.rs.next()) {

                    if (tfusername.getText().equals(cb.rs.getString("matricule")) && pfpass.getText().equals(cb.rs.getString("pass"))) {
                        tr = true;
                        break;
                    }
                }
                if (tr == true) {

                    AccueilBibliothéquaire ab = new AccueilBibliothéquaire();
                    String utib = tfusername.getText();
                    ab.lidentite.setText(utib);
                    ab.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Le matricule ou le mot de passe incorrect ", "Erreur", JOptionPane.ERROR_MESSAGE);

                }
            } catch (SQLException ex) {
                Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_bconnectActionPerformed

    private void bannulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bannulerActionPerformed
        tfusername.setText("");
        pfpass.setText("");
    }//GEN-LAST:event_bannulerActionPerformed

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
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Authentification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bannuler;
    private javax.swing.JButton bconnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lfond;
    private javax.swing.JPasswordField pfpass;
    private javax.swing.JTextField tfusername;
    // End of variables declaration//GEN-END:variables
}
