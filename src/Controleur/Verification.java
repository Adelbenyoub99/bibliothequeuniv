/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;



import com.toedter.calendar.JCalendar;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.text.SimpleDateFormat;


/**
 *
 * @author HP
 */
public class Verification {



    public static String addThreeWeeks(String dateString) throws Exception {
        // Créer un objet JCalendar
        JCalendar calendar = new JCalendar();
        // Définir la date du calendrier en utilisant la chaîne de caractères fournie
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setDate(sdf.parse(dateString));
        // Ajouter trois semaines à la date du calendrier
        Calendar cal = calendar.getCalendar();
        cal.add(Calendar.WEEK_OF_YEAR, 3);
        


        // Formater la nouvelle date en tant que chaîne de caractères et la retourner
        return sdf.format(cal.getTime());
    }

    public static String datePassée(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar date = Calendar.getInstance();
        try {
            java.util.Date parsedDate = formatter.parse(dateString);
            date.setTime(parsedDate);
        } catch (ParseException e) {
            return "Format de date invalide";
        }
        Calendar today = Calendar.getInstance();
        boolean isPassed = date.before(today);
        if (isPassed) {
            return "La date de retour est dépassée";
        } else {
            return "La date n'est pas dépassée";
        }
    }

    public static String getDateAujourdhui() {
        // Obtenir la date d'aujourd'hui
        Date date = new Date();

        // Définir le format de date
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        // Retourner la date d'aujourd'hui formatée
        return format.format(date);
    }

    public static void impimerRapport(JTable jt, String titre) {
        MessageFormat header = new MessageFormat("Rapport");
        MessageFormat footer = new MessageFormat("Page {0,number,integer}");
        try {
            jt.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null, "Erreur d'impression : " + pe.getMessage());
        }

    }

    public static String getDateInTwoWeeks(String dateString) throws ParseException {
        // Créer un objet JCalendar
        JCalendar calendar = new JCalendar();

        // Définir la date du calendrier en utilisant la chaîne de caractères fournie
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setDate(sdf.parse(dateString));

        // Ajouter trois semaines à la date du calendrier
        Calendar cal = calendar.getCalendar();
        cal.add(Calendar.WEEK_OF_YEAR, 2);

        // Formater la nouvelle date en tant que chaîne de caractères et la retourner
        return sdf.format(cal.getTime());
    }

    public static String addOneMonth(String dateString) throws Exception {
        // Créer un objet JCalendar
        JCalendar calendar = new JCalendar();

        // Définir la date du calendrier en utilisant la chaîne de caractères fournie
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setDate(sdf.parse(dateString));

        // Ajouter trois semaines à la date du calendrier
        Calendar cal = calendar.getCalendar();
        cal.add(Calendar.WEEK_OF_YEAR, 4);

        // Formater la nouvelle date en tant que chaîne de caractères et la retourner
        return sdf.format(cal.getTime());
    }

    public static String addOneYear(String dateString) throws Exception {
        // Créer un objet JCalendar
        JCalendar calendar = new JCalendar();

        // Définir la date du calendrier en utilisant la chaîne de caractères fournie
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        calendar.setDate(sdf.parse(dateString));

        // Ajouter trois semaines à la date du calendrier
        Calendar cal = calendar.getCalendar();
        cal.add(Calendar.WEEK_OF_YEAR, 48);

        // Formater la nouvelle date en tant que chaîne de caractères et la retourner
        return sdf.format(cal.getTime());
    }

    public static String obtenirHeureActuelle() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return format.format(date);
    }



}
