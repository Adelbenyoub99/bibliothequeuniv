/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impression;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 *
 * @author HP
 */
public class ImprimRecu implements Printable {

    private Recu receipt;

    public ImprimRecu(Recu receipt) {
        this.receipt = receipt;
    }

    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex == 0) {
            Graphics2D g2d = (Graphics2D) graphics;
            
            g2d.translate(100, 300);
            
            // Dessiner les informations du reçu
            g2d.drawLine(100, 35, 200, 35);//dessiner ligne
            g2d.drawString("Rayon : " + receipt.getRayon(), 50, 80);
            g2d.drawString("Date de retour : " + receipt.getDateRetour(), 50, 100);
            g2d.setFont(new Font("Arial", Font.PLAIN, 40));
            g2d.drawString("Reçu", 100, 30);

            return Printable.PAGE_EXISTS;
        }

        return Printable.NO_SUCH_PAGE;
    }
}
