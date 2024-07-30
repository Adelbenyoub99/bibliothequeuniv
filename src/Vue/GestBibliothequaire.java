/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Connexion;
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class GestBibliothequaire extends javax.swing.JFrame {

    /**
     * Creates new form GestBibliothequaire
     */
    Connexion c = new Connexion();
    PreparedStatement s;
    DefaultTableModel dtm = new DefaultTableModel();
    ResultSet rs;

    public GestBibliothequaire() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        setTitle("Bibliothèquaire");
        ImageIcon icon = new ImageIcon("C:\\Users\\HP\\Desktop\\ressources admin\\NetBeansProjects\\Bibliothèque\\src\\Images\\Sans titre.png");
        setIconImage(icon.getImage());
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfdaten.setText("jj/mm/aaaa");
        tfdaten.setFont(font);
        tfdaten.setForeground(Color.gray);
        tfrechercher.setText("Recherche");
        tfrechercher.setFont(font);
        tfrechercher.setForeground(Color.gray);

        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM bibliothecaire");
            rs = s.executeQuery();
            String tabtitre[] = {"matricule", "nom", "prenom", "datenaiss", "genre", "pass"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("datenaiss"),
                    rs.getString("genre"),
                    rs.getString("pass")});
            }
            tbibliotecaire.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(GestBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Methode reset 
    public void reset() {
        lmat.setText("");
        tfnom.setText("");
        tfprenom.setText("");
        rbh.setSelected(false);
        rbf.setSelected(false);
        lmdp.setText("");
        Font font = new Font("Tahoma", Font.PLAIN, 11);
        tfdaten.setText("jj/mm/aaaa");
        tfdaten.setFont(font);
        tfdaten.setForeground(Color.gray);
        tfrechercher.setText("Recherche");
        tfrechercher.setFont(font);
        tfrechercher.setForeground(Color.gray);

    }

    public void actualiser() {
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        try {
            s = c.connectBdd().prepareStatement("SELECT * FROM bibliothecaire");
            rs = s.executeQuery();
            String tabtitre[] = {"matricule", "nom", "prenom", "datenaiss", "genre", "pass"};
            for (int i = 0; i < tabtitre.length; i++) {
                dtm.addColumn(tabtitre[i]);
            }
            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString("matricule"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("datenaiss"),
                    rs.getString("genre"),
                    rs.getString("pass")});
            }
            tbibliotecaire.setModel(dtm);
        } catch (SQLException ex) {
            Logger.getLogger(GestBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Methode de selection 
    public void select(int ls) {

        lmat.setText(tbibliotecaire.getValueAt(ls, 0) + "");
        tfnom.setText(tbibliotecaire.getValueAt(ls, 1) + "");
        tfprenom.setText(tbibliotecaire.getValueAt(ls, 2) + "");
        Font font = new Font("Arial", Font.BOLD, 12);
        tfdaten.setForeground(new java.awt.Color(153, 102, 0));
        tfdaten.setFont(font);
        tfdaten.setText(tbibliotecaire.getValueAt(ls, 3) + "");
        if (tbibliotecaire.getValueAt(ls, 4).equals("Homme")) {
            rbf.setSelected(false);
            rbh.setSelected(true);

        }
        if (tbibliotecaire.getValueAt(ls, 4).equals("Femme")) {
            rbh.setSelected(false);
            rbf.setSelected(true);

        }
        lmdp.setText(tbibliotecaire.getValueAt(ls, 5) + "");
    }

    public String matricul() {

        String mat = null;
        mat = tfnom.getText().substring(0, 3) + tfprenom.getText().substring(0, 1) + tfdaten.getText().substring(9, 10) + (tbibliotecaire.getRowCount() + 1);
        return mat;
    }

    public String mdp() {

        String mdp = null;
        mdp = tfnom.getText().substring(0, 1) + tfprenom.getText().substring(0, 1) + tfdaten.getText().substring(9, 10) + (tbibliotecaire.getRowCount() + 1);
        return mdp;
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
        bretour = new javax.swing.JButton();
        tfrechercher = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        bsupp = new javax.swing.JButton();
        bannuler = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbibliotecaire = new javax.swing.JTable();
        bmodifier = new javax.swing.JButton();
        bajouter = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lmat = new javax.swing.JLabel();
        tfnom = new javax.swing.JTextField();
        tfprenom = new javax.swing.JTextField();
        tfdaten = new javax.swing.JTextField();
        rbh = new javax.swing.JRadioButton();
        rbf = new javax.swing.JRadioButton();
        lmdp = new javax.swing.JLabel();
        bgenerer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bretour.setBackground(new java.awt.Color(255, 255, 255));
        bretour.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bretour.setForeground(new java.awt.Color(153, 102, 0));
        bretour.setText("Retour");
        bretour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bretourActionPerformed(evt);
            }
        });
        jPanel2.add(bretour, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, -1, -1));

        tfrechercher.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfrechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfrechercherActionPerformed(evt);
            }
        });
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
        jPanel2.add(tfrechercher, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 160, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Informations : ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Bibliotecaires");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, -1));

        bsupp.setBackground(new java.awt.Color(255, 255, 255));
        bsupp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bsupp.setForeground(new java.awt.Color(153, 102, 0));
        bsupp.setText("Supprimer");
        bsupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsuppActionPerformed(evt);
            }
        });
        jPanel2.add(bsupp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 130, -1));

        bannuler.setBackground(new java.awt.Color(255, 255, 255));
        bannuler.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bannuler.setForeground(new java.awt.Color(153, 102, 0));
        bannuler.setText("Annuler");
        bannuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bannulerActionPerformed(evt);
            }
        });
        jPanel2.add(bannuler, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, 110, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(0, 0, 255));

        tbibliotecaire.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tbibliotecaire.setForeground(new java.awt.Color(0, 0, 255));
        tbibliotecaire.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Matricule", "Nom", "Prénom", "Date de naissance", "Genre", "Mot de passe"
            }
        ));
        tbibliotecaire.setGridColor(new java.awt.Color(0, 0, 255));
        tbibliotecaire.setSelectionBackground(new java.awt.Color(0, 0, 255));
        tbibliotecaire.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbibliotecaireMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbibliotecaire);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 490, 250));

        bmodifier.setBackground(new java.awt.Color(255, 255, 255));
        bmodifier.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bmodifier.setForeground(new java.awt.Color(153, 102, 0));
        bmodifier.setText("Modifier");
        bmodifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmodifierActionPerformed(evt);
            }
        });
        jPanel2.add(bmodifier, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 120, -1));

        bajouter.setBackground(new java.awt.Color(255, 255, 255));
        bajouter.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bajouter.setForeground(new java.awt.Color(153, 102, 0));
        bajouter.setText("Ajouter");
        bajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bajouterActionPerformed(evt);
            }
        });
        jPanel2.add(bajouter, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 110, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Matricule : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Nom :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("Prénom : ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 20));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Date de naissance :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Genre : ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Mot de passe : ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, 20));

        lmat.setBackground(new java.awt.Color(255, 255, 255));
        lmat.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lmat.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lmat, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 140, 20));

        tfnom.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfnom.setForeground(new java.awt.Color(153, 102, 0));
        tfnom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfnomFocusLost(evt);
            }
        });
        jPanel1.add(tfnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 140, -1));

        tfprenom.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tfprenom.setForeground(new java.awt.Color(153, 102, 0));
        tfprenom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfprenomFocusLost(evt);
            }
        });
        jPanel1.add(tfprenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 140, -1));

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
        jPanel1.add(tfdaten, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 140, -1));

        rbh.setBackground(new java.awt.Color(255, 255, 255));
        rbh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbh.setForeground(new java.awt.Color(153, 102, 0));
        rbh.setText("Homme");
        rbh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbhActionPerformed(evt);
            }
        });
        jPanel1.add(rbh, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        rbf.setBackground(new java.awt.Color(255, 255, 255));
        rbf.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbf.setForeground(new java.awt.Color(153, 102, 0));
        rbf.setText("Femme");
        rbf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfActionPerformed(evt);
            }
        });
        rbf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                rbfFocusLost(evt);
            }
        });
        jPanel1.add(rbf, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        lmdp.setBackground(new java.awt.Color(255, 255, 255));
        lmdp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lmdp.setForeground(new java.awt.Color(153, 102, 0));
        jPanel1.add(lmdp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 140, 20));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 310, 240));

        bgenerer.setBackground(new java.awt.Color(255, 255, 255));
        bgenerer.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bgenerer.setForeground(new java.awt.Color(153, 102, 0));
        bgenerer.setText("Générer");
        bgenerer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bgenererActionPerformed(evt);
            }
        });
        jPanel2.add(bgenerer, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 110, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bretourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bretourActionPerformed
        AccueilGestionnaire ag = new AccueilGestionnaire();
        ag.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_bretourActionPerformed

    private void tfrechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfrechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfrechercherActionPerformed

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

    private void rbfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rbfFocusLost

    }//GEN-LAST:event_rbfFocusLost

    private void bannulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bannulerActionPerformed
        actualiser();
        reset();
    }//GEN-LAST:event_bannulerActionPerformed

    private void bgenererActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bgenererActionPerformed
        if (tfnom.getText().equals("") || tfprenom.getText().equals("") || tfdaten.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Information incomplète veuillez remplire tout les champs");

        } else {
            lmat.setText(matricul());
            lmdp.setText(mdp());
        }

    }//GEN-LAST:event_bgenererActionPerformed

    private void bajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bajouterActionPerformed
        if (!rbf.isSelected() && !rbh.isSelected() || tfnom.getText().equals("") || tfprenom.getText().equals("") || tfdaten.getText().equals("") || lmat.getText().equals("") || lmdp.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Information incomplète veuillez remplire tout les champs");

        } else {
            try {
                s = c.connectBdd().prepareStatement("SELECT * FROM bibliothecaire WHERE matricule = ?");
                s.setString(1, lmat.getText());
                rs = s.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Cette personne existe déjà, veuillez reverifier.");
                    actualiser();
                    reset();

                } else {
                    try {
                        s = c.connectBdd().prepareStatement("INSERT INTO bibliothecaire(matricule,nom,prenom,datenaiss,genre,pass,connect) VALUES(?,?,?,?,?,?,?) ");
                        s.setString(1, lmat.getText());
                        s.setString(2, tfnom.getText());
                        s.setString(3, tfprenom.getText());
                        s.setString(4, tfdaten.getText());
                        if (rbh.isSelected()) {
                            s.setString(5, "Homme");
                        } else if (rbf.isSelected()) {
                            s.setString(5, "Femme");
                        }
                        s.setString(6, lmdp.getText());
                        s.setString(7, "Déconnecté");
                        s.executeUpdate();
                        JOptionPane.showMessageDialog(this, "La personne à été ajouté. ");
                        actualiser();
                        reset();

                    } catch (SQLException ex) {
                        Logger.getLogger(GestBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(GestBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_bajouterActionPerformed

    private void bsuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsuppActionPerformed
        if (tbibliotecaire.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez selectionner une ligne a supprimer");
        } else if (JOptionPane.showConfirmDialog(this, "Voulez vous vraiment supprimer cette personne ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                s = c.connectBdd().prepareStatement("DELETE FROM bibliothecaire WHERE matricule = ?");
                s.setString(1, lmat.getText());
                s.executeUpdate();
                JOptionPane.showMessageDialog(this, "La suppression est réussie");
                actualiser();
                reset();
            } catch (SQLException ex) {
                Logger.getLogger(GestBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_bsuppActionPerformed

    private void bmodifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmodifierActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment modifier?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (tfnom.getText().equals("") || tfprenom.getText().equals("") || tfdaten.getText().equals("") || lmat.getText().equals("") || lmdp.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Information incomplète veuillez remplire tout les champs");
            } else {

                try {
                    String genre = "";
                    if (rbh.isSelected()) {
                        genre = rbh.getText();
                    }
                    if (rbf.isSelected()) {
                        genre = rbf.getText();
                    }

                    s = c.connectBdd().prepareStatement("UPDATE bibliothecaire SET matricule=?, nom=?, prenom=?, datenaiss=?, genre=?, pass=? WHERE matricule='" + tbibliotecaire.getValueAt(tbibliotecaire.getSelectedRow(), 0) + "'");

                    s.setString(1, lmat.getText());
                    s.setString(2, tfnom.getText());
                    s.setString(3, tfprenom.getText());
                    s.setString(4, tfdaten.getText());
                    s.setString(5, genre);
                    s.setString(6, lmdp.getText());
                    s.executeUpdate();

                } catch (SQLException ex) {
                    Logger.getLogger(GestBibliothequaire.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            JOptionPane.showMessageDialog(this, "Modification faite avec succes");
            actualiser();
            reset();

        }


    }//GEN-LAST:event_bmodifierActionPerformed

    private void rbfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfActionPerformed
        if (rbf.isSelected()) {
            rbh.setSelected(false);

        }
    }//GEN-LAST:event_rbfActionPerformed

    private void tbibliotecaireMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbibliotecaireMouseClicked
        select(tbibliotecaire.getSelectedRow());
    }//GEN-LAST:event_tbibliotecaireMouseClicked

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

    private void tfdatenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfdatenFocusGained
        tfdaten.setText("");
    }//GEN-LAST:event_tfdatenFocusGained

    private void tfrechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfrechercherKeyReleased
        Font font = new Font("Arial", Font.BOLD, 12);
        tfrechercher.setFont(font);
        tfrechercher.setForeground(new java.awt.Color(153, 102, 0));
        if (tfrechercher.getText().equals("")) {
            actualiser();
        } else {
            try {

                String txt = tfrechercher.getText();
                s = c.connectBdd().prepareStatement("SELECT * FROM bibliothecaire WHERE matricule like '%" + txt + "%' or nom like '%" + txt + "%' or prenom like '%" + txt + "%' or datenaiss like '%" + txt + "%' or genre like '%" + txt + "%' or pass like '%" + txt + "%'");

                rs = s.executeQuery();
                dtm.setRowCount(0);

                while (rs.next()) {
                    dtm.addRow(new Object[]{rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("datenaiss"),
                        rs.getString("genre"),
                        rs.getString("pass")});

                }

                tbibliotecaire.setModel(dtm);

            } catch (SQLException ex) {
                Logger.getLogger(GestBibliothequaire.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_tfrechercherKeyReleased

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
            java.util.logging.Logger.getLogger(GestBibliothequaire.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestBibliothequaire.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestBibliothequaire.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestBibliothequaire.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestBibliothequaire().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bajouter;
    private javax.swing.JButton bannuler;
    private javax.swing.JButton bgenerer;
    private javax.swing.JButton bmodifier;
    private javax.swing.JButton bretour;
    private javax.swing.JButton bsupp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lmat;
    private javax.swing.JLabel lmdp;
    private javax.swing.JRadioButton rbf;
    private javax.swing.JRadioButton rbh;
    private javax.swing.JTable tbibliotecaire;
    private javax.swing.JTextField tfdaten;
    private javax.swing.JTextField tfnom;
    private javax.swing.JTextField tfprenom;
    private javax.swing.JTextField tfrechercher;
    // End of variables declaration//GEN-END:variables
}
