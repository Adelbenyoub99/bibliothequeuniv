/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import static Controleur.Verification.addThreeWeeks;
import static Controleur.Verification.datePassée;
import static Controleur.Verification.getDateAujourdhui;
import static Controleur.Verification.getDateInTwoWeeks;
import static Controleur.Verification.impimerRapport;
import static Controleur.Verification.obtenirHeureActuelle;
import Modele.Connexion;
import impression.ImprimRecu;
import impression.Recu;
import java.awt.Color;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Emprunt extends javax.swing.JFrame {

    Connexion c = new Connexion();
    PreparedStatement s;
    ResultSet rs;
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm1 = new DefaultTableModel();
    DefaultTableModel dtm2 = new DefaultTableModel();

    /**
     * Creates new form Emprunt
     */
    public Emprunt() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setTitle("Emprunt");
        ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\ressources admin\\NetBeansProjects\\Bibliothèque\\src\\Images\\Sans titre.png");
        setIconImage(icon.getImage());
        cbrapport.setSelectedIndex(0);
        cbrechercher.setSelectedIndex(0);
        ldate.setText(getDateAujourdhui());
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfrechercher.setText("Recherche");
        tfrechercher.setFont(font);
        tfrechercher.setForeground(Color.gray);
        tabouvrage.setRowSelectionAllowed(true);
        tabouvrage.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        lprolongation.setVisible(false);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM abonnee");
            rs = s.executeQuery();
            String tabtitre[] = {"Matricule", "Statut", "Situation", "Nombre d'emprunts"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("statut"),
                    rs.getString("situation"),
                    rs.getString("nbremprunt"),});
            }
            tababo.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

        dtm1.setRowCount(0);
        dtm1.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM ouvrages");
            rs = s.executeQuery();
            String tabtitre[] = {"Rayon", "Statut"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm1.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm1.addRow(new Object[]{rs.getString("rayon"),
                    rs.getString("statut")});
            }
            tabouvrage.setModel(dtm1);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

        dtm2.setRowCount(0);
        dtm2.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM emprunt");
            rs = s.executeQuery();
            String tabtitre[] = {"Identifiant", "Matricule", "Rayon", "Date de l'emprunt", "Heure", "Date de restitution", "Prolongation"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm2.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm2.addRow(new Object[]{rs.getString("id"),
                    rs.getString("matricule"),
                    rs.getString("rayon"),
                    rs.getString("dateemprun"),
                    rs.getString("heure"),
                    rs.getString("daterestitution"),
                    rs.getString("prolongation")});
            }
            tabemprunt.setModel(dtm2);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reset() {
        lid.setText("");
        lmatabo.setText("");
        lstatut.setText("");
        lsituation.setText("");
        lrayon.setText("");
        lstatutouvrage.setText("");
        lverif.setText("");
        lnbremprunt.setText("");
        ldateemrunt.setText("");
        ldateretour.setText("");
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfrechercher.setText("Recherche");
        tfrechercher.setFont(font);
        tfrechercher.setForeground(Color.gray);
        lrayon2.setText("");

    }

    public void actualiser() {
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM abonnee");
            rs = s.executeQuery();
            String tabtitre[] = {"Matricule", "Statut", "Situation", "Nombre d'emprunts"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("statut"),
                    rs.getString("situation"),
                    rs.getString("nbremprunt")});
            }
            tababo.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

        dtm1.setRowCount(0);
        dtm1.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM ouvrages");
            rs = s.executeQuery();
            String tabtitre[] = {"Rayon", "Statut"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm1.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm1.addRow(new Object[]{rs.getString("rayon"),
                    rs.getString("statut")});
            }
            tabouvrage.setModel(dtm1);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

        dtm2.setRowCount(0);
        dtm2.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM emprunt");
            rs = s.executeQuery();
            String tabtitre[] = {"Identifiant", "Matricule", "Rayon", "Date de l'emprunt", "Heure", "Date de restitution", "Prolongation"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm2.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm2.addRow(new Object[]{rs.getString("id"),
                    rs.getString("matricule"),
                    rs.getString("rayon"),
                    rs.getString("dateemprun"),
                    rs.getString("heure"),
                    rs.getString("daterestitution"),
                    rs.getString("prolongation")});
            }
            tabemprunt.setModel(dtm2);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Methode de selection 
    public void selectabo(int ls) {

        lmatabo.setText(tababo.getValueAt(ls, 0) + "");
        lstatut.setText(tababo.getValueAt(ls, 1) + "");
        lsituation.setText(tababo.getValueAt(ls, 2) + "");
        lnbremprunt.setText(tababo.getValueAt(ls, 3) + "");

    }

    public void selectouvrage(int[] ls) {

        lrayon2.setText("");
        lstatutouvrage.setText("");
        /* lrayon2.setText(tabouvrage.getValueAt(ls, 0) + "");
        lstatutouvrage.setText(tabouvrage.getValueAt(ls, 1) + "");*/
        switch (ls.length) {
            case 1:
                lrayon2.setText(lrayon2.getText() + tabouvrage.getValueAt(ls[0], 0));
                lstatutouvrage.setText(tabouvrage.getValueAt(ls[0], 1) + "");
                break;
            case 2:
                for (int i = 0; i < ls.length - 1; i++) {
                    lrayon2.setText(lrayon2.getText() + tabouvrage.getValueAt(ls[i], 0) + ", ");
                    lstatutouvrage.setText(tabouvrage.getValueAt(ls[i], 1) + ", ");
                }
                lrayon2.setText(lrayon2.getText() + tabouvrage.getValueAt(ls[ls.length - 1], 0));
                lstatutouvrage.setText(tabouvrage.getValueAt(ls[ls.length - 1], 1) + "");
                break;
            case 3:
                for (int i = 0; i < ls.length - 1; i++) {
                    lrayon2.setText(lrayon2.getText() + tabouvrage.getValueAt(ls[i], 0) + ", ");
                    lstatutouvrage.setText(tabouvrage.getValueAt(ls[i], 1) + ", ");
                }
                lrayon2.setText(lrayon2.getText() + tabouvrage.getValueAt(ls[ls.length - 1], 0));
                lstatutouvrage.setText(tabouvrage.getValueAt(ls[ls.length - 1], 1) + "");
                break;

        }

        //lrayon2.setText(lrayon2.getText()+tabouvrage.getValueAt(ls.length-1, 0) + " ");
    }

    public void selectemprunt(int ls) {

        lid.setText(tabemprunt.getValueAt(ls, 0) + "");
        lmatabo.setText(tabemprunt.getValueAt(ls, 1) + "");
        lrayon.setText(tabemprunt.getValueAt(ls, 2) + "");
        ldateemrunt.setText(tabemprunt.getValueAt(ls, 3) + "");
        ldateretour.setText(tabemprunt.getValueAt(ls, 5) + "");
        lprolongation.setText(tabemprunt.getValueAt(ls, 6) + "");

        String verif = datePassée(ldateretour.getText());
        if (verif.equals("La date de retour est dépassée")) {
            lverif.setText(datePassée(ldateretour.getText()));
            lverif.setForeground(Color.red);
        } else {
            lverif.setText(datePassée(ldateretour.getText()));
            lverif.setForeground(Color.green);
        }

        try {
            s = c.connectBdd().prepareStatement("SELECT nbremprunt FROM abonnee WHERE matricule='" + lmatabo.getText() + "'");

            rs = s.executeQuery();

            if (rs.next()) {
                String co = rs.getString("nbremprunt");
                lnbremprunt.setText(co);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String id() {

        String id = "";
        id = lmatabo.getText() + (tababo.getRowCount() + 1);
        return id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        bimprimerrapport = new javax.swing.JButton();
        ldate = new javax.swing.JLabel();
        beffacer = new javax.swing.JButton();
        bsignaler = new javax.swing.JButton();
        bid = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tababo = new javax.swing.JTable();
        brestitution = new javax.swing.JButton();
        bdaterestitudep = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabouvrage = new javax.swing.JTable();
        cbrapport = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        beffec = new javax.swing.JButton();
        brenouv = new javax.swing.JButton();
        tfrechercher = new javax.swing.JTextField();
        cbrechercher = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lid = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lmatabo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lstatut = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lsituation = new javax.swing.JLabel();
        lrayon = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lstatutouvrage = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lnbremprunt = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lverif = new javax.swing.JLabel();
        ldateemrunt = new javax.swing.JLabel();
        ldateretour = new javax.swing.JLabel();
        lrayon2 = new javax.swing.JLabel();
        bretour = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabemprunt = new javax.swing.JTable();
        lprolongation = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bimprimerrapport.setBackground(new java.awt.Color(255, 255, 255));
        bimprimerrapport.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bimprimerrapport.setForeground(new java.awt.Color(153, 102, 0));
        bimprimerrapport.setText("Imprimer");
        bimprimerrapport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bimprimerrapportActionPerformed(evt);
            }
        });
        jPanel2.add(bimprimerrapport, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 110, -1));

        ldate.setBackground(new java.awt.Color(255, 255, 255));
        ldate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ldate.setForeground(new java.awt.Color(0, 0, 255));
        jPanel2.add(ldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 90, 20));

        beffacer.setBackground(new java.awt.Color(255, 255, 255));
        beffacer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        beffacer.setForeground(new java.awt.Color(153, 102, 0));
        beffacer.setText("Annuler");
        beffacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beffacerActionPerformed(evt);
            }
        });
        jPanel2.add(beffacer, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 420, 120, -1));

        bsignaler.setBackground(new java.awt.Color(255, 255, 255));
        bsignaler.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bsignaler.setForeground(new java.awt.Color(153, 102, 0));
        bsignaler.setText("Signaler");
        bsignaler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsignalerActionPerformed(evt);
            }
        });
        jPanel2.add(bsignaler, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 90, -1));

        bid.setBackground(new java.awt.Color(255, 255, 255));
        bid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bid.setForeground(new java.awt.Color(153, 102, 0));
        bid.setText("Générer l'id");
        bid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bidActionPerformed(evt);
            }
        });
        jPanel2.add(bid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 130, -1));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("Table des ouvrages : ");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, -1, 20));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 255));

        tababo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tababo.setForeground(new java.awt.Color(0, 0, 255));
        tababo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Matricule", "Statut", "Situation", "Nombre d'emprunt"
            }
        ));
        tababo.setGridColor(new java.awt.Color(0, 0, 255));
        tababo.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tababo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tababoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tababo);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 450, 90));

        brestitution.setBackground(new java.awt.Color(255, 255, 255));
        brestitution.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        brestitution.setForeground(new java.awt.Color(153, 102, 0));
        brestitution.setText("Effectuer la restitution");
        brestitution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brestitutionActionPerformed(evt);
            }
        });
        jPanel2.add(brestitution, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 390, 160, -1));

        bdaterestitudep.setBackground(new java.awt.Color(255, 255, 255));
        bdaterestitudep.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bdaterestitudep.setForeground(new java.awt.Color(153, 102, 0));
        bdaterestitudep.setText("Date de restitution dépassé");
        bdaterestitudep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdaterestitudepActionPerformed(evt);
            }
        });
        jPanel2.add(bdaterestitudep, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 200, -1));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setForeground(new java.awt.Color(0, 0, 255));

        tabouvrage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabouvrage.setForeground(new java.awt.Color(0, 0, 255));
        tabouvrage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Rayon", "Statut"
            }
        ));
        tabouvrage.setGridColor(new java.awt.Color(0, 0, 255));
        tabouvrage.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tabouvrage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabouvrageMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabouvrage);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 450, 90));

        cbrapport.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbrapport.setForeground(new java.awt.Color(153, 102, 0));
        cbrapport.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rapport", "Ensemble des emprunts", "Emprunts de la journeé" }));
        jPanel2.add(cbrapport, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 60, 170, -1));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Table des emprunts : ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, -1, 20));

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 255));
        jLabel16.setText("Table des abonnées : ");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, -1, 20));

        beffec.setBackground(new java.awt.Color(255, 255, 255));
        beffec.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        beffec.setForeground(new java.awt.Color(153, 102, 0));
        beffec.setText("Effectuer l'emprunt");
        beffec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beffecActionPerformed(evt);
            }
        });
        jPanel2.add(beffec, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 150, -1));

        brenouv.setBackground(new java.awt.Color(255, 255, 255));
        brenouv.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        brenouv.setForeground(new java.awt.Color(153, 102, 0));
        brenouv.setText("Prolonger");
        brenouv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brenouvActionPerformed(evt);
            }
        });
        jPanel2.add(brenouv, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 390, 120, -1));

        tfrechercher.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfrechercher.setForeground(new java.awt.Color(153, 102, 0));
        tfrechercher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfrechercherFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfrechercherFocusLost(evt);
            }
        });
        tfrechercher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfrechercherKeyReleased(evt);
            }
        });
        jPanel2.add(tfrechercher, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 170, -1));

        cbrechercher.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbrechercher.setForeground(new java.awt.Color(153, 102, 0));
        cbrechercher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rechercher", "Abonnées", "Ouvrages", "Emprunts" }));
        jPanel2.add(cbrechercher, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 120, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Informations : ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Date : ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Id emprunt : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        lid.setBackground(new java.awt.Color(255, 255, 255));
        lid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lid.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 140, 20));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Matricule de l'abonnée : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        lmatabo.setBackground(new java.awt.Color(255, 255, 255));
        lmatabo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lmatabo.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lmatabo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 120, 20));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Rayon :");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 20));

        lstatut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lstatut.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lstatut, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 120, 20));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Statut abonnée :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, 20));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Situation :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        lsituation.setBackground(new java.awt.Color(255, 255, 255));
        lsituation.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lsituation.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lsituation, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 120, 20));

        lrayon.setBackground(new java.awt.Color(255, 255, 255));
        lrayon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lrayon.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lrayon, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 170, 20));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("Statut ouvrage :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, 20));

        lstatutouvrage.setBackground(new java.awt.Color(255, 255, 255));
        lstatutouvrage.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lstatutouvrage.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lstatutouvrage, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 230, 20));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 255));
        jLabel18.setText("Nombre d'emprunts : ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        lnbremprunt.setBackground(new java.awt.Color(255, 255, 255));
        lnbremprunt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lnbremprunt.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lnbremprunt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 130, 20));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 255));
        jLabel20.setText("Date de l'emprunt :");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, 20));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 255));
        jLabel17.setText("Date de restitution :");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, 20));

        lverif.setBackground(new java.awt.Color(255, 255, 255));
        lverif.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lverif.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lverif, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 240, 20));

        ldateemrunt.setBackground(new java.awt.Color(255, 255, 255));
        ldateemrunt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ldateemrunt.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(ldateemrunt, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 110, 20));

        ldateretour.setBackground(new java.awt.Color(255, 255, 255));
        ldateretour.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ldateretour.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(ldateretour, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 120, 20));

        lrayon2.setBackground(new java.awt.Color(255, 255, 255));
        lrayon2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lrayon2.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lrayon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 210, 20));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 620, 270));

        bretour.setBackground(new java.awt.Color(255, 255, 255));
        bretour.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bretour.setForeground(new java.awt.Color(153, 102, 0));
        bretour.setText("Retour");
        bretour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bretourActionPerformed(evt);
            }
        });
        jPanel2.add(bretour, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Emprunts");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setForeground(new java.awt.Color(0, 0, 255));

        tabemprunt.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabemprunt.setForeground(new java.awt.Color(0, 0, 255));
        tabemprunt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Matricule de l'abonnée", "Rayon", "Date d'emprunt", "Heure emprunt", "Date de restitution", "Prolongation"
            }
        ));
        tabemprunt.setGridColor(new java.awt.Color(0, 0, 255));
        tabemprunt.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tabemprunt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabempruntMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabemprunt);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 1090, 120));
        jPanel2.add(lprolongation, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 420, 80, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bidActionPerformed
        if (lmatabo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Information inssufisantes");
        } else {
            lid.setText(id());
        }

    }//GEN-LAST:event_bidActionPerformed

    private void bretourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bretourActionPerformed

        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM gestionnaire");

            rs = s.executeQuery();
            while (rs.next()) {
                String co = rs.getString("connect");
                if ("Connecté".equals(co)) {
                    AccueilGestionnaire ag = new AccueilGestionnaire();
                    ag.setVisible(true);
                    setVisible(false);

                } else {
                    AccueilBibliothéquaire ab = new AccueilBibliothéquaire();
                    ab.setVisible(true);
                    setVisible(false);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bretourActionPerformed

    private void tababoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tababoMouseClicked
        selectabo(tababo.getSelectedRow());
    }//GEN-LAST:event_tababoMouseClicked

    private void tabouvrageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabouvrageMouseClicked
        selectouvrage(tabouvrage.getSelectedRows());
    }//GEN-LAST:event_tabouvrageMouseClicked

    private void tabempruntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabempruntMouseClicked
        selectemprunt(tabemprunt.getSelectedRow());
    }//GEN-LAST:event_tabempruntMouseClicked

    private void beffacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beffacerActionPerformed
        actualiser();
        reset();
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfrechercher.setText("Recherche");
        tfrechercher.setFont(font);
        tfrechercher.setForeground(Color.gray);
    }//GEN-LAST:event_beffacerActionPerformed

    private void bsignalerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsignalerActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment signaler cette personne ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                s = c.connectBdd().prepareStatement("UPDATE abonnee SET situation=? WHERE matricule='" + tabemprunt.getValueAt(tabemprunt.getSelectedRow(), 1) + "'");

                s.setString(1, "Signalé");
                s.executeUpdate();

                JOptionPane.showMessageDialog(this, "Signalement fait avec succes");

            } catch (SQLException ex) {
                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
            }
            actualiser();
            reset();
        }
    }//GEN-LAST:event_bsignalerActionPerformed

    private void beffecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beffecActionPerformed
        try {
            // verifier si le matricule existe dans la table abonnee 

            s = c.connectBdd().prepareStatement("SELECT * FROM emprunt WHERE id='" + lid.getText() + "'");
            rs = s.executeQuery();

            if (rs.next()) {

                s = c.connectBdd().prepareStatement("SELECT nbremprunt FROM abonnee WHERE matricule='" + lmatabo.getText() + "'");
                rs = s.executeQuery();

                while (rs.next()) {
                    String co = rs.getString("nbremprunt");
                    // si oui alors verifier le nombre d'ouvrage emprunté par ce dernier
                    int a = Integer.parseInt(co);

                    if ((a == 1) || (a == 2)) {
                        // si c'est inferieur a 3 alors l'abonné peut emprunté un ou plusieurs autres ouvrage

                        if (lstatutouvrage.getText().equals("Pas emprunté")) {
                            // ajouter une  n eme valeur a l'attribut rayon 

                            s = c.connectBdd().prepareStatement("UPDATE emprunt SET rayon = CONCAT(rayon, ', " + lrayon2.getText() + "') WHERE id='" + lid.getText() + "'");
                            s.executeUpdate();
                            // inrementer +1 nbr emprunt 
                            String rayon3 = lrayon2.getText();
                            int nbSegments = rayon3.length() / 6;
                            if (nbSegments == 1) {
                                String segment1 = rayon3.substring(0, 6);
                                try {
                                    s = c.connectBdd().prepareStatement("UPDATE abonnee SET nbremprunt=? WHERE matricule='" + lmatabo.getText() + "'");
                                    int i2 = Integer.parseInt(lnbremprunt.getText());
                                    i2 = i2 + 1;
                                    String l1 = i2 + "";
                                    s.setString(1, l1);
                                    s.executeUpdate();
                                } catch (SQLException ex) {
                                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                // modification de l'attribut statut de l'ouvrage a emprunté
                                try {
                                    s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");

                                    s.setString(1, "Emprunté");
                                    s.executeUpdate();

                                } catch (SQLException ex) {
                                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } else if (nbSegments == 2) {
                                String segment1 = rayon3.substring(0, 6);
                                String segment2 = rayon3.substring(8, 14);
                                try {
                                    s = c.connectBdd().prepareStatement("UPDATE abonnee SET nbremprunt=? WHERE matricule='" + lmatabo.getText() + "'");
                                    int i2 = Integer.parseInt(lnbremprunt.getText());
                                    // inrementer +2 nbr emprunt 
                                    i2 = i2 + 2;
                                    String l1 = i2 + "";
                                    s.setString(1, l1);
                                    s.executeUpdate();
                                } catch (SQLException ex) {
                                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                // modification de l'attribut statut de l'ouvrage a emprunté
                                try {
                                    s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");

                                    s.setString(1, "Emprunté");
                                    s.executeUpdate();

                                } catch (SQLException ex) {
                                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment2 + "'");

                                    s.setString(1, "Emprunté");
                                    s.executeUpdate();

                                } catch (SQLException ex) {
                                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else if (nbSegments == 3) {
                                JOptionPane.showMessageDialog(null, "Nombre d'ouvrage possible a ajouté dépassé");
                                actualiser();
                                reset();

                            }
                            if (a == 1) {

                                JOptionPane.showMessageDialog(null, "L'ouvrage a été ajouté au emprunt de l'abonné : " + lmatabo.getText() + ", vous pouvez emprunté un 3 éme ouvrage.");

                            } else if (a == 2) {

                                JOptionPane.showMessageDialog(null, "L'ouvrage a été ajouté au emprunt de l'abonné : " + lmatabo.getText() + ", vous ne pouvez plus emprunté d'ouvrage.");

                            }
                            // impression
                            String rayon = lrayon.getText() + ", " + lrayon2.getText();
                            String dateRetour = addThreeWeeks(ldate.getText());

                            Recu recu = new Recu(rayon, dateRetour);
                            ImprimRecu imprimrecu = new ImprimRecu(recu);

                            PrinterJob printerJob = PrinterJob.getPrinterJob();
                            printerJob.setPrintable(imprimrecu);

                            if (printerJob.printDialog()) {
                                try {
                                    printerJob.print();
                                } catch (PrinterException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            actualiser();
                            reset();
                        } else {
                            JOptionPane.showMessageDialog(null, "Cette ouvrage a déjà été emprunté");
                            actualiser();
                            reset();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre limite d'emprunt dépacé ");
                        actualiser();
                        reset();
                    }
                }

                // si le matricule n'existe pas 
                // alors verifier les condition suivantes 
            } else // verification des informations 
            {
                if (lid.getText().equals("") || lmatabo.getText().equals("") || lstatut.getText().equals("") || lsituation.getText().equals("") || lnbremprunt.getText().equals("") || lrayon2.getText().equals("") || lstatutouvrage.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Informations incomplètes");
                    actualiser();
                    reset();
                } else if (lsituation.getText().equals("Normale") && lstatut.getText().equals("Pas d'emprunt")) {
                    if (lstatutouvrage.getText().equals("Pas emprunté")) {

                        // modification du statut de l'abonné et incrementation de l'attribut nbremprunt
                        String rayon = lrayon2.getText();
                        int nbSegments = rayon.length() / 6;
                        if (nbSegments == 1) {
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE abonnee SET statut=?, nbremprunt=? WHERE matricule='" + lmatabo.getText() + "'");
                                int i2 = Integer.parseInt(lnbremprunt.getText());
                                i2 = i2 + 1;
                                String l1 = i2 + "";
                                s.setString(1, "Emprunté");
                                s.setString(2, l1);
                                s.executeUpdate();
                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // modification de l'attribut statut de l'ouvrage a emprunté
                            String segment1 = rayon.substring(0, 6);
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");

                                s.setString(1, "Emprunté");
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (nbSegments == 2) {
                            String segment1 = rayon.substring(0, 6);
                            String segment2 = rayon.substring(8, 14);
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE abonnee SET statut=?, nbremprunt=? WHERE matricule='" + lmatabo.getText() + "'");
                                int i2 = Integer.parseInt(lnbremprunt.getText());
                                i2 = i2 + 2;
                                String l1 = i2 + "";
                                s.setString(1, "Emprunté");
                                s.setString(2, l1);
                                s.executeUpdate();
                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // modification de l'attribut statut de l'ouvrage a emprunté
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");

                                s.setString(1, "Emprunté");
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment2 + "'");

                                s.setString(1, "Emprunté");
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else if (nbSegments == 3) {
                            String segment1 = rayon.substring(0, 6);
                            String segment2 = rayon.substring(8, 14);
                            String segment3 = rayon.substring(16);
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE abonnee SET statut=?, nbremprunt=? WHERE matricule='" + lmatabo.getText() + "'");
                                int i2 = Integer.parseInt(lnbremprunt.getText());
                                i2 = i2 + 3;
                                String l1 = i2 + "";
                                s.setString(1, "Emprunté");
                                s.setString(2, l1);
                                s.executeUpdate();
                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            // modification de l'attribut statut de l'ouvrage a emprunté
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");

                                s.setString(1, "Emprunté");
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment2 + "'");

                                s.setString(1, "Emprunté");
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment3 + "'");

                                s.setString(1, "Emprunté");
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                        // creation du nouveaux tuple empunt 
                        try {
                            s = c.connectBdd().prepareStatement("INSERT INTO emprunt(id,matricule,rayon,dateemprun,heure,daterestitution,prolongation) VALUES(?,?,?,?,?,?,?)");
                            s.setString(1, lid.getText());
                            s.setString(2, lmatabo.getText());
                            s.setString(3, lrayon2.getText());
                            s.setString(4, ldate.getText());
                            s.setString(5, obtenirHeureActuelle());
                            s.setString(6, addThreeWeeks(ldate.getText()));
                            s.setString(7, "Non");

                            s.executeUpdate();
                            JOptionPane.showMessageDialog(this, "Emprunt effectué avec succée, la date de restitution est :  " + addThreeWeeks(ldate.getText()));

                        } catch (Exception ex) {
                            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "l'Ouvrage à été emprunté, vous pouvez emprunté un deuxième ouvrage.");

                    }

                    // impression
                    String rayon1 = lrayon2.getText();

                    String dateRetour = addThreeWeeks(ldate.getText());

                    Recu recu = new Recu(rayon1, dateRetour);
                    ImprimRecu imprimrecu = new ImprimRecu(recu);

                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    printerJob.setPrintable(imprimrecu);

                    if (printerJob.printDialog()) {
                        try {
                            printerJob.print();
                        } catch (PrinterException ex) {
                            ex.printStackTrace();
                        }
                    }
                    actualiser();
                    reset();

                } else {
                    JOptionPane.showMessageDialog(null, "l'Abonnée à été signalé ou pénalisée ou a déjà emprunté: ");
                    actualiser();
                    reset();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_beffecActionPerformed

    private void brestitutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brestitutionActionPerformed

        // Parcourir la JTable pour trouver les emprunts de l'abonné
        for (int i = 0; i < tabemprunt.getRowCount(); i++) {
            if (tabemprunt.getValueAt(i, 1).equals(lmatabo.getText())) {
                // Récupérer les valeurs des rayons empruntés
                String rayon = (String) tabemprunt.getValueAt(i, 2);
                if (rayon.length() != 6) {
                    int nbSegments = rayon.length() / 6;
                    if (nbSegments == 2) {
                        String segment1 = rayon.substring(0, 6);
                        String segment2 = rayon.substring(8, 14);
                        try {
                            s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");
                            s.setString(1, "Pas emprunté");
                            s.executeUpdate();

                        } catch (SQLException ex) {
                            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment2 + "'");
                            s.setString(1, "Pas emprunté");
                            s.executeUpdate();

                        } catch (SQLException ex) {
                            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (nbSegments == 3) {
                        String segment1 = rayon.substring(0, 6);
                        String segment2 = rayon.substring(8, 14);
                        String segment3 = rayon.substring(16);
                        try {
                            s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment1 + "'");
                            s.setString(1, "Pas emprunté");
                            s.executeUpdate();

                        } catch (SQLException ex) {
                            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment2 + "'");
                            s.setString(1, "Pas emprunté");
                            s.executeUpdate();

                        } catch (SQLException ex) {
                            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + segment3 + "'");
                            s.setString(1, "Pas emprunté");
                            s.executeUpdate();

                        } catch (SQLException ex) {
                            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                } else if (rayon.length() == 6) {

                    try {
                        s = c.connectBdd().prepareStatement("UPDATE ouvrages SET statut=? WHERE rayon='" + lrayon.getText() + "'");
                        s.setString(1, "Pas emprunté");
                        s.executeUpdate();

                    } catch (SQLException ex) {
                        Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

        try {
            s = c.connectBdd().prepareStatement("DELETE FROM emprunt WHERE id = ?");
            s.setString(1, lid.getText());
            s.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            s = c.connectBdd().prepareStatement("UPDATE abonnee SET statut=?, nbremprunt=? WHERE matricule='" + lmatabo.getText() + "'");

            s.setString(1, "Pas d'emprunt");
            s.setString(2, "0");
            s.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "La restitution est réussie");
        actualiser();
        reset();


    }//GEN-LAST:event_brestitutionActionPerformed

    private void brenouvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brenouvActionPerformed

        if (lprolongation.getText().equals("Oui")) {
            JOptionPane.showMessageDialog(this, "Prolongation impossible, l'emprunt à déjà été prolongé. ");
            actualiser();
            reset();
        } else if (lprolongation.getText().equals("Non")) {
            String a = (String) tabemprunt.getValueAt(tabemprunt.getSelectedRow(), 5);
            try {
                s = c.connectBdd().prepareStatement("UPDATE emprunt SET dateemprun=?, heure=?, daterestitution=? ,prolongation=? WHERE id='" + lid.getText() + "'");
                s.setString(1, ldate.getText());
                s.setString(2, obtenirHeureActuelle());
                s.setString(3, getDateInTwoWeeks(a));
                s.setString(4, "Oui");

                s.executeUpdate();
                JOptionPane.showMessageDialog(this, "Prolongation effectué avec succée, la date de restitution est :  " + getDateInTwoWeeks(a));

                    // impression
                    String rayon1 = lrayon.getText();

                    String dateRetour = getDateInTwoWeeks(ldate.getText());

                    Recu recu = new Recu(rayon1, dateRetour);
                    ImprimRecu imprimrecu = new ImprimRecu(recu);

                    PrinterJob printerJob = PrinterJob.getPrinterJob();
                    printerJob.setPrintable(imprimrecu);

                    if (printerJob.printDialog()) {
                        try {
                            printerJob.print();
                        } catch (PrinterException ex) {
                            ex.printStackTrace();
                        }
                    }
                    actualiser();
                    reset();
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_brenouvActionPerformed

    private void bimprimerrapportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bimprimerrapportActionPerformed
        if (cbrapport.getSelectedItem().equals("Ensemble des emprunts")) {
            String titre1 = "Liste des emprunts";
            JOptionPane.showMessageDialog(null, "L'impression de l'ensemble des emprunts à été effectuer avec succés");

            impimerRapport(tabemprunt, titre1);
        } else if (cbrapport.getSelectedItem().equals("Emprunts de la journeé")) {
            String titre2 = "Liste des emprunts du jour";

            try {
                s = c.connectBdd().prepareStatement("SELECT * FROM emprunt WHERE dateemprun = ?");
                s.setString(1, getDateAujourdhui());
                rs = s.executeQuery();
                dtm2.setRowCount(0);
                try {
                    while (rs.next()) {
                        dtm2.addRow(new Object[]{rs.getString("id"),
                            rs.getString("matricule"),
                            rs.getString("rayon"),
                            rs.getString("dateemprun"),
                            rs.getString("heure"),
                            rs.getString("daterestitution"),
                            rs.getString("prolongation")});

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "L'impression des emprunts d'aujourd'hui à été effectuer avec succés");

                impimerRapport(tabemprunt, titre2);

            } catch (SQLException ex) {
                Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (cbrapport.getSelectedItem().equals("Rapport")) {

            JOptionPane.showMessageDialog(null, "Vous devez selectionner le type d'impresseion a effectuer");

        }
    }//GEN-LAST:event_bimprimerrapportActionPerformed

    private void bdaterestitudepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdaterestitudepActionPerformed
        dtm2.setRowCount(0);
        dtm2.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM emprunt");
            rs = s.executeQuery();
            String tabtitre[] = {"Id", "Matricule", "Rayon", "Dateemprun", "Heure", "Date de restitution", "Prolongation"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm2.addColumn(tabtitre[i]);
            }
            while (rs.next()) {

                if (datePassée(rs.getString("daterestitution")).equals("La date de retour est dépassée")) {
                    dtm2.addRow(new Object[]{rs.getString("id"),
                        rs.getString("matricule"),
                        rs.getString("rayon"),
                        rs.getString("dateemprun"),
                        rs.getString("heure"),
                        rs.getString("daterestitution"),
                        rs.getString("prolongation")});

                }
            }
            tabemprunt.setModel(dtm2);
        } catch (SQLException ex) {
            Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bdaterestitudepActionPerformed

    private void tfrechercherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfrechercherFocusGained
        tfrechercher.setText("");
    }//GEN-LAST:event_tfrechercherFocusGained

    private void tfrechercherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfrechercherFocusLost
        if (tfrechercher.getText().equals("")) {

            Font font = new Font("Tahoma", Font.PLAIN, 11);
            tfrechercher.setText("Recherche");
            tfrechercher.setFont(font);
            tfrechercher.setForeground(Color.gray);
        } else {
        }

    }//GEN-LAST:event_tfrechercherFocusLost

    private void tfrechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfrechercherKeyReleased

        Font font = new Font("Arial", Font.BOLD, 12);
        tfrechercher.setFont(font);
        tfrechercher.setForeground(new java.awt.Color(153, 102, 0));
        if (cbrechercher.getSelectedItem().equals("Abonnées")) {
            if (tfrechercher.getText().equals("")) {
                actualiser();
            } else {
                try {

                    String txt = tfrechercher.getText();
                    s = c.connectBdd().prepareStatement("SELECT * FROM abonnee WHERE matricule like '%" + txt + "%' or statut like '%" + txt + "%' or situation like '%" + txt + "%' or nbremprunt like '%" + txt + "%'");

                    rs = s.executeQuery();
                    dtm.setRowCount(0);

                    while (rs.next()) {
                        dtm.addRow(new Object[]{rs.getString("matricule"),
                            rs.getString("statut"),
                            rs.getString("situation"),
                            rs.getString("nbremprunt")});

                    }

                    tababo.setModel(dtm);

                } catch (SQLException ex) {
                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (cbrechercher.getSelectedItem().equals("Ouvrages")) {
            if (tfrechercher.getText().equals("")) {
                actualiser();
            } else {
                try {

                    String txt = tfrechercher.getText();
                    s = c.connectBdd().prepareStatement("SELECT * FROM ouvrages WHERE rayon like '%" + txt + "%' or statut like '%" + txt + "%'");

                    rs = s.executeQuery();
                    dtm1.setRowCount(0);

                    while (rs.next()) {
                        dtm1.addRow(new Object[]{rs.getString("rayon"),
                            rs.getString("statut")});

                    }

                    tabouvrage.setModel(dtm1);

                } catch (SQLException ex) {
                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (cbrechercher.getSelectedItem().equals("Emprunts")) {
            if (tfrechercher.getText().equals("")) {
                actualiser();
            } else {
                try {

                    String txt = tfrechercher.getText();
                    s = c.connectBdd().prepareStatement("SELECT * FROM emprunt WHERE id like '%" + txt + "%' or matricule like '%" + txt + "%' or rayon like '%" + txt + "%' or dateemprun like '%" + txt + "%' or heure like '%" + txt + "%' or daterestitution like '%" + txt + "%' or prolongation like '%" + txt + "%'");

                    rs = s.executeQuery();
                    dtm2.setRowCount(0);

                    while (rs.next()) {
                        dtm2.addRow(new Object[]{rs.getString("id"),
                            rs.getString("matricule"),
                            rs.getString("rayon"),
                            rs.getString("dateemprun"),
                            rs.getString("heure"),
                            rs.getString("daterestitution"),
                            rs.getString("prolongation")});

                    }

                    tabemprunt.setModel(dtm2);

                } catch (SQLException ex) {
                    Logger.getLogger(Emprunt.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (cbrechercher.getSelectedItem().equals("Rechercher")) {

            JOptionPane.showMessageDialog(null, "Vous devez selectionner une table a parcourire");
            actualiser();
            reset();
        }


    }//GEN-LAST:event_tfrechercherKeyReleased

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
            java.util.logging.Logger.getLogger(Emprunt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Emprunt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Emprunt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Emprunt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Emprunt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bdaterestitudep;
    private javax.swing.JButton beffacer;
    private javax.swing.JButton beffec;
    private javax.swing.JButton bid;
    private javax.swing.JButton bimprimerrapport;
    private javax.swing.JButton brenouv;
    private javax.swing.JButton brestitution;
    private javax.swing.JButton bretour;
    private javax.swing.JButton bsignaler;
    private javax.swing.JComboBox<String> cbrapport;
    private javax.swing.JComboBox<String> cbrechercher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel ldate;
    private javax.swing.JLabel ldateemrunt;
    private javax.swing.JLabel ldateretour;
    private javax.swing.JLabel lid;
    private javax.swing.JLabel lmatabo;
    private javax.swing.JLabel lnbremprunt;
    private javax.swing.JLabel lprolongation;
    private javax.swing.JLabel lrayon;
    private javax.swing.JLabel lrayon2;
    private javax.swing.JLabel lsituation;
    private javax.swing.JLabel lstatut;
    private javax.swing.JLabel lstatutouvrage;
    private javax.swing.JLabel lverif;
    private javax.swing.JTable tababo;
    private javax.swing.JTable tabemprunt;
    private javax.swing.JTable tabouvrage;
    private javax.swing.JTextField tfrechercher;
    // End of variables declaration//GEN-END:variables
}
