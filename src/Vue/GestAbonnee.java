/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import static Controleur.Verification.addOneMonth;
import static Controleur.Verification.addOneYear;
import static Controleur.Verification.getDateAujourdhui;
import static Controleur.Verification.getDateInTwoWeeks;
import Modele.Connexion;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class GestAbonnee extends javax.swing.JFrame {

    Connexion c = new Connexion();
    PreparedStatement s;
    DefaultTableModel dtm = new DefaultTableModel();
    ResultSet rs;

    /**
     * Creates new form gestabonnee
     */
    public GestAbonnee() {

        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setTitle("Abonnées");
        ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\ressources admin\\NetBeansProjects\\Bibliothèque\\src\\Images\\Sans titrepng");
        setIconImage(icon.getImage());
        cbabonnement.setSelectedIndex(0);
        cbtype.setSelectedIndex(0);
        cbsituation.setSelectedIndex(0);
        cbaction.setSelectedIndex(0);
        ldate.setText(getDateAujourdhui());
        tfdaten.setText("jj/mm/aaaa");
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfdaten.setFont(font);
        tfdaten.setForeground(Color.gray);
        tfrecherche.setText("Recherche");
        tfrecherche.setFont(font);
        tfrecherche.setForeground(Color.gray);

        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM abonnee");
            rs = s.executeQuery();
            while (rs.next()) {

                // Vérification si la date de fin de pénalité est égale à la date d'aujourd'hui
                if (rs.getString("datefinpenalite").equals(getDateAujourdhui())) {
                    try {
                        s = c.connectBdd().prepareStatement("UPDATE abonnee SET situation=?, datefinpenalite=? WHERE matricule='" + rs.getString("matricule") + "'");

                        s.setString(1, "Normale");
                        s.setString(2, "");
                        s.executeUpdate();

                    } catch (Exception ex) {
                        Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            while (rs.next()) {

                // Vérification si la date de fin de pénalité est égale à la date d'aujourd'hui
                if (rs.getString("datefin").equals(getDateAujourdhui())) {
                    try {
                        s = c.connectBdd().prepareStatement("UPDATE abonnee SET situation=?, WHERE matricule='" + rs.getString("matricule") + "'");

                        s.setString(1, "Suspendu");
                        s.executeUpdate();

                    } catch (Exception ex) {
                        Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        } catch (SQLException e) {
        }
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM abonnee");
            rs = s.executeQuery();
            String tabtitre[] = {"Matricule", "Nom", "Prénom", "Date de naissance", "Genre", "Type", "Tél", "Date d'adèration", "Statut", "Situation", "Abonnement", "Date de fin d'abonnement"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("datenaiss"),
                    rs.getString("genre"),
                    rs.getString("type"),
                    rs.getString("tel"),
                    rs.getString("dateadere"),
                    rs.getString("statut"),
                    rs.getString("situation"),
                    rs.getString("abonnement"),
                    rs.getString("datefin")});
            }
            tababonnee.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Methode reset 
    public void reset() {
        tfmat.setText("");
        tfnom.setText("");
        tfprenom.setText("");
        rbh.setSelected(false);
        rbf.setSelected(false);
        tftel.setText("");
        ldateadere.setText("");
        lprix.setText("");
        cbabonnement.setSelectedIndex(0);
        cbtype.setSelectedIndex(0);
        cbsituation.setSelectedIndex(0);
        cbstatut.setSelectedIndex(0);
        cbaction.setModel(new DefaultComboBoxModel<>(new String[]{"Action", "Ajouter", "Modifier", "Supprimer"}));
        cbaction.setSelectedIndex(0);
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfdaten.setText("jj/mm/aaaa");
        tfdaten.setFont(font);
        tfdaten.setForeground(Color.gray);
        tfrecherche.setText("Recherche");
        tfrecherche.setFont(font);
        tfrecherche.setForeground(Color.gray);

    }

    public void actualiser() {
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM abonnee");
            rs = s.executeQuery();
            String tabtitre[] = {"Matricule", "Nom", "Prénom", "Date de naissance", "Genre", "Type", "Tél", "Date d'adèration", "Statut", "Situation", "Abonnement", "Date de fin d'abonnement"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("datenaiss"),
                    rs.getString("genre"),
                    rs.getString("type"),
                    rs.getString("tel"),
                    rs.getString("dateadere"),
                    rs.getString("statut"),
                    rs.getString("situation"),
                    rs.getString("abonnement"),
                    rs.getString("datefin")});
            }
            tababonnee.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Methode de selection 
    public void select(int ls) {

        tfmat.setText(tababonnee.getValueAt(ls, 0) + "");
        tfnom.setText(tababonnee.getValueAt(ls, 1) + "");
        tfprenom.setText(tababonnee.getValueAt(ls, 2) + "");
        Font font = new Font("Arial", Font.BOLD, 12);
        tfdaten.setForeground(new java.awt.Color(153, 102, 0));
        tfdaten.setFont(font);
        tfdaten.setText(tababonnee.getValueAt(ls, 3) + "");
        if (tababonnee.getValueAt(ls, 4).equals("Homme")) {
            rbf.setSelected(false);
            rbh.setSelected(true);

        }
        if (tababonnee.getValueAt(ls, 4).equals("Femme")) {
            rbh.setSelected(false);
            rbf.setSelected(true);

        }
        cbtype.setSelectedItem(tababonnee.getValueAt(ls, 5));
        tftel.setText(tababonnee.getValueAt(ls, 6) + "");
        ldateadere.setText(tababonnee.getValueAt(ls, 7) + "");
        cbstatut.setSelectedItem(tababonnee.getValueAt(ls, 8));
        cbsituation.setSelectedItem(tababonnee.getValueAt(ls, 9));
        cbabonnement.setSelectedItem(tababonnee.getValueAt(ls, 10));
        cbaction.setModel(new DefaultComboBoxModel<>(new String[]{"Action", "Modifier", "Supprimer"}));
        cbaction.setSelectedIndex(0);

        if (cbtype.getSelectedItem().equals("Etudiant")) {
            lprix.setText("200 DA par ans");
            cbabonnement.setVisible(true);
            labonnement.setVisible(true);
        } else if (cbtype.getSelectedItem().equals("Etudiant ex")) {
            lprix.setText("200 DA par mois");
            cbabonnement.setVisible(true);
            labonnement.setVisible(true);
        } else if (cbtype.getSelectedItem().equals("Enseignant")) {

            cbabonnement.setVisible(false);
            labonnement.setVisible(false);
            lprix.setText("Gratuit");
        }

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
        ldate = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        bannuler = new javax.swing.JButton();
        tfrecherche = new javax.swing.JTextField();
        bprecedent = new javax.swing.JButton();
        bsignalement = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tababonnee = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        tfmat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfnom = new javax.swing.JTextField();
        tfprenom = new javax.swing.JTextField();
        tfdaten = new javax.swing.JTextField();
        rbh = new javax.swing.JRadioButton();
        rbf = new javax.swing.JRadioButton();
        tftel = new javax.swing.JTextField();
        lsitu = new javax.swing.JLabel();
        cbsituation = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbtype = new javax.swing.JComboBox<>();
        lprix = new javax.swing.JLabel();
        labonnement = new javax.swing.JLabel();
        cbabonnement = new javax.swing.JComboBox<>();
        ldateadere = new javax.swing.JLabel();
        cbstatut = new javax.swing.JComboBox<>();
        cbaction = new javax.swing.JComboBox<>();
        baction = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ldate.setBackground(new java.awt.Color(255, 255, 255));
        ldate.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ldate.setForeground(new java.awt.Color(0, 0, 255));
        jPanel2.add(ldate, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, 100, 20));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 255));
        jLabel14.setText("Date : ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, -1, 20));

        bannuler.setBackground(new java.awt.Color(255, 255, 255));
        bannuler.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bannuler.setForeground(new java.awt.Color(153, 102, 0));
        bannuler.setText("Annuler");
        bannuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bannulerActionPerformed(evt);
            }
        });
        jPanel2.add(bannuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 110, -1));

        tfrecherche.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfrecherche.setForeground(new java.awt.Color(153, 102, 0));
        tfrecherche.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfrechercheFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfrechercheFocusLost(evt);
            }
        });
        tfrecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfrechercheKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfrechercheKeyTyped(evt);
            }
        });
        jPanel2.add(tfrecherche, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 180, -1));

        bprecedent.setBackground(new java.awt.Color(255, 255, 255));
        bprecedent.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bprecedent.setForeground(new java.awt.Color(153, 102, 0));
        bprecedent.setText("Retour");
        bprecedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprecedentActionPerformed(evt);
            }
        });
        jPanel2.add(bprecedent, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, -1, -1));

        bsignalement.setBackground(new java.awt.Color(255, 255, 255));
        bsignalement.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bsignalement.setForeground(new java.awt.Color(153, 102, 0));
        bsignalement.setText("Signalement");
        bsignalement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsignalementActionPerformed(evt);
            }
        });
        jPanel2.add(bsignalement, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 110, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Informations : ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Abonnés");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 255));
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 102, 0)));

        tababonnee.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tababonnee.setForeground(new java.awt.Color(0, 0, 255));
        tababonnee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricule", "Nom", "Prénom", "Date de naissanse", "Genre", "Type", "Tél", "Date adhésion", "Statut", "Situation", "Abonnement", "Date de fin"
            }
        ));
        tababonnee.setGridColor(new java.awt.Color(0, 0, 255));
        tababonnee.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tababonnee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tababonneeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tababonnee);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 1020, 130));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tfmat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfmat.setForeground(new java.awt.Color(153, 102, 0));
        tfmat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfmatFocusLost(evt);
            }
        });
        jPanel1.add(tfmat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 140, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Matricule : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Nom : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Prénom :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, 20));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Date de naissance : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Date d'adhésion : ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, 20));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Grenre : ");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 20));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Téléphone : ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, 20));

        tfnom.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfnom.setForeground(new java.awt.Color(153, 102, 0));
        tfnom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfnomFocusLost(evt);
            }
        });
        jPanel1.add(tfnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 140, -1));

        tfprenom.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfprenom.setForeground(new java.awt.Color(153, 102, 0));
        tfprenom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfprenomFocusLost(evt);
            }
        });
        jPanel1.add(tfprenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 140, -1));

        tfdaten.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfdaten.setForeground(new java.awt.Color(153, 102, 0));
        tfdaten.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfdatenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfdatenFocusLost(evt);
            }
        });
        tfdaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfdatenKeyReleased(evt);
            }
        });
        jPanel1.add(tfdaten, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 140, -1));

        rbh.setBackground(new java.awt.Color(255, 255, 255));
        rbh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbh.setForeground(new java.awt.Color(153, 102, 0));
        rbh.setText("Homme");
        rbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbhActionPerformed(evt);
            }
        });
        jPanel1.add(rbh, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        rbf.setBackground(new java.awt.Color(255, 255, 255));
        rbf.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbf.setForeground(new java.awt.Color(153, 102, 0));
        rbf.setText("Femme");
        rbf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfActionPerformed(evt);
            }
        });
        jPanel1.add(rbf, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        tftel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tftel.setForeground(new java.awt.Color(153, 102, 0));
        tftel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tftelFocusLost(evt);
            }
        });
        jPanel1.add(tftel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 150, -1));

        lsitu.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lsitu.setForeground(new java.awt.Color(0, 0, 255));
        lsitu.setText("Situation : ");
        jPanel1.add(lsitu, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, 20));

        cbsituation.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbsituation.setForeground(new java.awt.Color(153, 102, 0));
        cbsituation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Normale", "Pénalisé", "Suspendu" }));
        jPanel1.add(cbsituation, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 170, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Statut : ");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, 20));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Type :");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 20));

        cbtype.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbtype.setForeground(new java.awt.Color(153, 102, 0));
        cbtype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Etudiant", "Etudiant ex", "Enseignant" }));
        cbtype.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtypeItemStateChanged(evt);
            }
        });
        cbtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbtypeActionPerformed(evt);
            }
        });
        jPanel1.add(cbtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 110, -1));

        lprix.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lprix.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lprix, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 100, 20));

        labonnement.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labonnement.setForeground(new java.awt.Color(0, 0, 255));
        labonnement.setText("Abonnement : ");
        jPanel1.add(labonnement, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, 20));

        cbabonnement.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbabonnement.setForeground(new java.awt.Color(153, 102, 0));
        cbabonnement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Réglé", "Pas réglé", "/////" }));
        jPanel1.add(cbabonnement, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 170, -1));

        ldateadere.setBackground(new java.awt.Color(255, 255, 255));
        ldateadere.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        ldateadere.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(ldateadere, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 150, 20));

        cbstatut.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbstatut.setForeground(new java.awt.Color(153, 102, 0));
        cbstatut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Emprunt", "Pas d'emprunt" }));
        jPanel1.add(cbstatut, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 170, -1));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 590, 190));

        cbaction.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbaction.setForeground(new java.awt.Color(153, 102, 0));
        cbaction.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Action", "Ajouter", "Modifier", "Supprimer" }));
        cbaction.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbactionItemStateChanged(evt);
            }
        });
        jPanel2.add(cbaction, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        baction.setBackground(new java.awt.Color(255, 255, 255));
        baction.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        baction.setForeground(new java.awt.Color(153, 102, 0));
        baction.setText("Valider");
        baction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bactionActionPerformed(evt);
            }
        });
        jPanel2.add(baction, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bprecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprecedentActionPerformed
        AccueilGestionnaire ag = new AccueilGestionnaire();
        ag.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bprecedentActionPerformed

    private void tfnomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfnomFocusLost

        if (tfnom.getText().matches("^[a-zA-ZçÇ]+$")) {
            tfnom.setText(tfnom.getText().toUpperCase());
        } else if (tfnom.getText().equals("")) {
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un nom de famille. ");
            tfnom.setText("");
        }
    }//GEN-LAST:event_tfnomFocusLost

    private void tfprenomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfprenomFocusLost

        if (tfprenom.getText().matches("^[a-zA-ZçÇ]+$")) {
            tfprenom.setText(tfprenom.getText().substring(0, 1).toUpperCase() + tfprenom.getText().substring(1).toLowerCase());
        } else if (tfprenom.getText().equals("")) {
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez saisir un prenom. ");
            tfprenom.setText("");
        }
    }//GEN-LAST:event_tfprenomFocusLost

    private void rbhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbhActionPerformed
        if (rbh.isSelected()) {
            rbf.setSelected(false);
        }
    }//GEN-LAST:event_rbhActionPerformed

    private void rbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfActionPerformed
        if (rbf.isSelected()) {
            rbh.setSelected(false);

        }
    }//GEN-LAST:event_rbfActionPerformed

    private void tababonneeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tababonneeMouseClicked
        select(tababonnee.getSelectedRow());
    }//GEN-LAST:event_tababonneeMouseClicked

    private void bannulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bannulerActionPerformed
        actualiser();
        reset();
        tfdaten.setText("jj/mm/aaaa");
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfdaten.setFont(font);
        tfdaten.setForeground(Color.gray);
        tfrecherche.setText("Recherche");
        tfrecherche.setFont(font);
        tfrecherche.setForeground(Color.gray);
    }//GEN-LAST:event_bannulerActionPerformed

    private void bsignalementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsignalementActionPerformed
        try {
            String situ = "signalé";
            s = c.connectBdd().prepareStatement("SELECT * FROM abonnee WHERE situation = ?");
            s.setString(1, situ);
            rs = s.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
        dtm.setRowCount(0);
        try {
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("datenaiss"),
                    rs.getString("genre"),
                    rs.getString("type"),
                    rs.getString("tel"),
                    rs.getString("dateadere"),
                    rs.getString("statut"),
                    rs.getString("situation"),
                    rs.getString("abonnement"),
                    rs.getString("datefin")});

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bsignalementActionPerformed

    private void cbtypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtypeItemStateChanged
        if (cbtype.getSelectedItem().equals("Etudiant")) {
            cbabonnement.setSelectedItem("...");
            lprix.setText("200 DA par ans");
            cbabonnement.setVisible(true);
            cbsituation.setVisible(true);
            lsitu.setVisible(true);
            labonnement.setVisible(true);
        } else if (cbtype.getSelectedItem().equals("Etudiant ex")) {
            cbabonnement.setSelectedItem("...");
            lprix.setText("200 DA par mois");
            cbabonnement.setVisible(true);
            cbsituation.setVisible(true);
            lsitu.setVisible(true);
            labonnement.setVisible(true);
        } else if (cbtype.getSelectedItem().equals("Enseignant")) {
            cbabonnement.setSelectedItem("/////");
            cbabonnement.setVisible(false);
            cbsituation.setVisible(true);
            lsitu.setVisible(true);
            labonnement.setVisible(false);
            lprix.setText("Gratuit");
        } else if (cbtype.getSelectedItem().equals("...")) {
            cbabonnement.setVisible(true);
            cbsituation.setVisible(true);
            lsitu.setVisible(true);
            labonnement.setVisible(true);
            lprix.setText("");
        }


    }//GEN-LAST:event_cbtypeItemStateChanged

    private void bactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bactionActionPerformed
        if (cbaction.getSelectedItem().equals("Ajouter")) {
            if (!rbf.isSelected() && !rbh.isSelected() || tfdaten.getText().equals("") || tfmat.getText().equals("") || tfnom.getText().equals("") || tfprenom.getText().equals("") || tftel.getText().equals("") || cbtype.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Information incomplète, veuillez remplire tout les champs");
            } else {
                try {
                    s = c.connectBdd().prepareStatement("SELECT * FROM abonnee WHERE matricule = ?");
                    s.setString(1, tfmat.getText());
                    rs = s.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Cette personne existe déjà, veuillez reverifier.");
                        actualiser();
                        reset();

                    } else {
                        if (cbtype.getSelectedItem().equals("Etudiant")) {

                            try {
                                s = c.connectBdd().prepareStatement("INSERT INTO abonnee(matricule,nom,prenom,datenaiss,genre,type,tel,dateadere,statut,situation,nbremprunt,abonnement,datefin,datefinpenalite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                s.setString(1, tfmat.getText());
                                s.setString(2, tfnom.getText());
                                s.setString(3, tfprenom.getText());
                                s.setString(4, tfdaten.getText());
                                if (rbh.isSelected()) {
                                    s.setString(5, "Homme");
                                } else if (rbf.isSelected()) {
                                    s.setString(5, "Femme");
                                }
                                s.setString(6, cbtype.getSelectedItem() + "");
                                s.setString(7, tftel.getText());
                                s.setString(8, ldate.getText());
                                s.setString(9, "Pas d'emprunt");
                                s.setString(10, "Normale");
                                s.setString(11, "0");
                                s.setString(12, "Réglé");
                                s.setString(13, addOneYear(ldate.getText()));
                                s.setString(14, "");

                                s.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Abonnée ajouté. ");
                                actualiser();
                                reset();

                            } catch (SQLException ex) {
                                Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (cbtype.getSelectedItem().equals("Etudiant ex")) {
                            int nombreOccurrence = 0;

                            try {
                                s = c.connectBdd().prepareStatement("SELECT COUNT(*) FROM abonnés");
                                rs = s.executeQuery();
                                // Récupération du résultat
                                rs.next();
                                nombreOccurrence = rs.getInt(1);

                            } catch (SQLException e) {
                            }
                            int nombreEtudiantsExternes = 0;

                            try {
                                s = c.connectBdd().prepareStatement("SELECT COUNT(*) FROM abonnee WHERE type = 'Etudiant ex'");
                                rs = s.executeQuery();
                                // Récupération du résultat
                                rs.next();
                                nombreEtudiantsExternes = rs.getInt(1);

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            nombreOccurrence = (int) (nombreOccurrence * 0.1);
                            if (nombreEtudiantsExternes <= nombreOccurrence) {
                                try {
                                    s = c.connectBdd().prepareStatement("INSERT INTO abonnee(matricule,nom,prenom,datenaiss,genre,type,tel,dateadere,statut,situation,nbremprunt,abonnement,datefin,datefinpenalite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                    s.setString(1, tfmat.getText());
                                    s.setString(2, tfnom.getText());
                                    s.setString(3, tfprenom.getText());
                                    s.setString(4, tfdaten.getText());
                                    if (rbh.isSelected()) {
                                        s.setString(5, "Homme");
                                    } else if (rbf.isSelected()) {
                                        s.setString(5, "Femme");
                                    }
                                    s.setString(6, cbtype.getSelectedItem() + "");
                                    s.setString(7, tftel.getText());
                                    s.setString(8, ldate.getText());
                                    s.setString(9, "Pas d'emprunt");
                                    s.setString(10, "Normale");
                                    s.setString(11, "0");
                                    s.setString(12, "Réglé");
                                    s.setString(13, addOneMonth(ldate.getText()));
                                    s.setString(14, "");

                                    s.executeUpdate();
                                    JOptionPane.showMessageDialog(this, "Abonnée ajouté. ");
                                    actualiser();
                                    reset();

                                } catch (SQLException ex) {
                                    Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } else {
                                JOptionPane.showMessageDialog(this, "Impossible d'ajouter l'abonnée !, nombre maximal d'étudiant externe attein");
                            }
                        }
                        if (cbtype.getSelectedItem().equals("Enseignant")) {
                            try {
                                s = c.connectBdd().prepareStatement("INSERT INTO abonnee(matricule,nom,prenom,datenaiss,genre,type,tel,dateadere,statut,situation,nbremprunt,abonnement,datefin,datefinpenalite) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                s.setString(1, tfmat.getText());
                                s.setString(2, tfnom.getText());
                                s.setString(3, tfprenom.getText());
                                s.setString(4, tfdaten.getText());
                                if (rbh.isSelected()) {
                                    s.setString(5, "Homme");
                                } else if (rbf.isSelected()) {
                                    s.setString(5, "Femme");
                                }
                                s.setString(6, cbtype.getSelectedItem() + "");
                                s.setString(7, tftel.getText());
                                s.setString(8, ldate.getText());
                                s.setString(9, "Pas d'emprunt");
                                s.setString(10, "Normale");
                                s.setString(11, "0");
                                s.setString(12, "/////");
                                s.setString(13, "Non concerner");
                                s.setString(14, "");

                                s.executeUpdate();
                                JOptionPane.showMessageDialog(this, "Abonnée ajouté. ");
                                actualiser();
                                reset();

                            } catch (SQLException ex) {
                                Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (cbaction.getSelectedItem().equals("Modifier")) {
            if (cbtype.getSelectedItem().equals("Enseignant") && cbabonnement.getSelectedItem().equals("Réglé") || cbtype.getSelectedItem().equals("Enseignant") && cbabonnement.getSelectedItem().equals("Pas réglé")) {
                JOptionPane.showMessageDialog(this, "Un enseignant doit bénéficier d'un abonnement illimité ");
                actualiser();
                reset();
            } else if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment modifier?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (!rbf.isSelected() && !rbh.isSelected() || tfdaten.getText().equals("") || tfmat.getText().equals("") || tfnom.getText().equals("") || tfprenom.getText().equals("") || tftel.getText().equals("") || cbtype.getSelectedIndex() == 0 || cbstatut.getSelectedIndex() == 0 || cbsituation.getSelectedIndex() == 0 || cbabonnement.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Information incomplète, veuillez remplire tout les champs");
                } else {
                    try {
                        s = c.connectBdd().prepareStatement("UPDATE abonnee SET matricule=?, nom=?, prenom=?, datenaiss=?, genre=?, type=?, tel=?, situation=?, abonnement=?, datefin=? WHERE matricule='" + tababonnee.getValueAt(tababonnee.getSelectedRow(), 0) + "'");

                        s.setString(1, tfmat.getText());
                        s.setString(2, tfnom.getText());
                        s.setString(3, tfprenom.getText());
                        s.setString(4, tfdaten.getText());
                        if (rbh.isSelected()) {
                            s.setString(5, "Homme");
                        } else if (rbf.isSelected()) {
                            s.setString(5, "Femme");
                        }
                        s.setString(6, cbtype.getSelectedItem() + "");
                        s.setString(7, tftel.getText());
                        s.setString(8, cbsituation.getSelectedItem() + "");
                        if (cbtype.getSelectedItem().equals("Etudiant")) {
                            s.setString(9, cbabonnement.getSelectedItem() + "");
                            s.setString(10, addOneYear(ldate.getText()));
                        }
                        if (cbtype.getSelectedItem().equals("Etudiant ex")) {
                            s.setString(9, cbabonnement.getSelectedItem() + "");
                            s.setString(10, addOneMonth(ldate.getText()));
                        }
                        if (cbtype.getSelectedItem().equals("Enseignant")) {
                            s.setString(9, "/////");
                            s.setString(10, "Non concerné");
                        }
                        s.executeUpdate();
                        if (cbsituation.getSelectedItem() + "" == "Pénalisé") {
                            try {
                                s = c.connectBdd().prepareStatement("UPDATE abonnee SET datefinpenalite=? WHERE matricule='" + tababonnee.getValueAt(tababonnee.getSelectedRow(), 0) + "'");

                                s.setString(1, getDateInTwoWeeks(ldate.getText()));
                                s.executeUpdate();

                            } catch (SQLException ex) {
                                Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        JOptionPane.showMessageDialog(this, "Modification faite avec succes");
                        actualiser();
                        reset();

                    } catch (SQLException ex) {
                        Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (cbaction.getSelectedItem().equals("Supprimer")) {

            if (tababonnee.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez selectionner la ligne que vous souhaitez supprimé");
            } else if (JOptionPane.showConfirmDialog(this, "Voulez vous vraiment supprimer l'abonnée ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    s = c.connectBdd().prepareStatement("DELETE FROM abonnee WHERE matricule = ?");
                    s.setString(1, tfmat.getText());
                    s.executeUpdate();
                    JOptionPane.showMessageDialog(this, "La suppression est réussie");
                    actualiser();
                    reset();
                } catch (SQLException ex) {
                    Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (cbaction.getSelectedItem().equals("Action")) {
            JOptionPane.showMessageDialog(this, "Vous devez sélectionner une action");
            actualiser();
            reset();
        }
    }//GEN-LAST:event_bactionActionPerformed

    private void cbactionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbactionItemStateChanged

    }//GEN-LAST:event_cbactionItemStateChanged

    private void tftelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tftelFocusLost
        if (tftel.getText().matches("^0[5-7]\\d{8}$") || tftel.getText().equals("")) {
        } else {
            JOptionPane.showMessageDialog(this, "Vous devez saisir un numéros de téléphone valide ");
            tftel.setText("");
        }
    }//GEN-LAST:event_tftelFocusLost

    private void cbtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbtypeActionPerformed

    }//GEN-LAST:event_cbtypeActionPerformed

    private void tfdatenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdatenFocusGained
        tfdaten.setText("");
    }//GEN-LAST:event_tfdatenFocusGained

    private void tfdatenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdatenFocusLost
        // Récupération du texte du JTextField à vérifier
        String text = tfdaten.getText();

        // Obtention de l'année actuelle et du seuil d'âge
        Calendar now = Calendar.getInstance();
        int currentYear = now.get(Calendar.YEAR);
        int ageLimit = currentYear - 16;

        // Vérification du format et de la validité de la date
        if (text.matches("^(0?[1-9]|[12][0-9]|30)/(0?[1-9]|1[0-2])/(19|20)[0-9]{2}$")) {
            // Vérification de la validité de l'année
            int year = Integer.parseInt(text.substring(6));
            if (year <= ageLimit && year >= 1900) {
            } else {
                JOptionPane.showMessageDialog(this, "Cette personne est trop jeune pour être un etudiant ou un enseignant");
                tfdaten.setText("jj/mm/aaaa");
                Font font = new Font("Tahoma", Font.PLAIN, 11);
                tfdaten.setFont(font);
                tfdaten.setForeground(Color.gray);
            }
        } else if (tfdaten.getText().equals("")) {
            tfdaten.setText("jj/mm/aaaa");
            Font font = new Font("Tahoma", Font.PLAIN, 11);
            tfdaten.setFont(font);
            tfdaten.setForeground(Color.gray);
        } else {
            JOptionPane.showMessageDialog(this, "Format de date invalide");
            tfdaten.setText("jj/mm/aaaa");
            Font font = new Font("Tahoma", Font.PLAIN, 11);
            tfdaten.setFont(font);
            tfdaten.setForeground(Color.gray);
        }
    }//GEN-LAST:event_tfdatenFocusLost

    private void tfmatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfmatFocusLost
        if (tfmat.getText().matches("^[0-9]{12}$") || tfmat.getText().equals("")) {
        } else {
            JOptionPane.showMessageDialog(this, "Ce matricule ne correspond pas a un matricule etudiant ou enseignat");
            tfmat.setText("");
        }
    }//GEN-LAST:event_tfmatFocusLost

    private void tfrechercheKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfrechercheKeyTyped


    }//GEN-LAST:event_tfrechercheKeyTyped

    private void tfrechercheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfrechercheKeyReleased
        Font font = new Font("Arial", Font.BOLD, 12);
        tfrecherche.setFont(font);
        tfrecherche.setForeground(new java.awt.Color(153, 102, 0));

        if (tfrecherche.getText().equals("")) {
            actualiser();
        } else {
            try {

                String txt = tfrecherche.getText();
                s = c.connectBdd().prepareStatement("SELECT * FROM abonnee WHERE matricule like '%" + txt + "%' or nom like '%" + txt + "%' or prenom like '%" + txt + "%' or datenaiss like '%" + txt + "%' or genre like '%" + txt + "%' or type like '%" + txt + "%' or tel like '%" + txt + "%' or dateadere like '%" + txt + "%' or statut like '%" + txt + "%' or situation like '%" + txt + "%' or abonnement like '%" + txt + "%' or datefin like '%" + txt + "%'");

                rs = s.executeQuery();
                dtm.setRowCount(0);

                while (rs.next()) {
                    dtm.addRow(new Object[]{rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("datenaiss"),
                        rs.getString("genre"),
                        rs.getString("type"),
                        rs.getString("tel"),
                        rs.getString("dateadere"),
                        rs.getString("statut"),
                        rs.getString("situation"),
                        rs.getString("abonnement"),
                        rs.getString("datefin")});

                }

                tababonnee.setModel(dtm);

            } catch (SQLException ex) {
                Logger.getLogger(GestAbonnee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_tfrechercheKeyReleased

    private void tfrechercheFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfrechercheFocusGained
        tfrecherche.setText("");
    }//GEN-LAST:event_tfrechercheFocusGained

    private void tfrechercheFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfrechercheFocusLost
        if (tfrecherche.getText().equals("")) {

            Font font = new Font("Tahoma", Font.PLAIN, 11);
            tfrecherche.setText("Recherche");
            tfrecherche.setFont(font);
            tfrecherche.setForeground(Color.gray);
        } else {
        }

    }//GEN-LAST:event_tfrechercheFocusLost

    private void tfdatenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfdatenKeyReleased
        Font font = new Font("Arial", Font.BOLD, 12);
        tfdaten.setFont(font);
        tfdaten.setForeground(new java.awt.Color(153, 102, 0));
    }//GEN-LAST:event_tfdatenKeyReleased

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
            java.util.logging.Logger.getLogger(GestAbonnee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestAbonnee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestAbonnee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestAbonnee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestAbonnee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baction;
    private javax.swing.JButton bannuler;
    private javax.swing.JButton bprecedent;
    private javax.swing.JButton bsignalement;
    private javax.swing.JComboBox<String> cbabonnement;
    private javax.swing.JComboBox<String> cbaction;
    private javax.swing.JComboBox<String> cbsituation;
    private javax.swing.JComboBox<String> cbstatut;
    private javax.swing.JComboBox<String> cbtype;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labonnement;
    private javax.swing.JLabel ldate;
    private javax.swing.JLabel ldateadere;
    private javax.swing.JLabel lprix;
    private javax.swing.JLabel lsitu;
    private javax.swing.JRadioButton rbf;
    private javax.swing.JRadioButton rbh;
    private javax.swing.JTable tababonnee;
    private javax.swing.JTextField tfdaten;
    private javax.swing.JTextField tfmat;
    private javax.swing.JTextField tfnom;
    private javax.swing.JTextField tfprenom;
    private javax.swing.JTextField tfrecherche;
    private javax.swing.JTextField tftel;
    // End of variables declaration//GEN-END:variables
}
