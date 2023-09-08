/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package form.confirmation;

import controller.ClientController;
import domain.PriceList;
import domain.PriceListItem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.TableModelCenovnik;

/**
 *
 * @author Petar
 */
public class PregledCenovnikaUpdateForm extends javax.swing.JDialog {

    PriceListItem stavkaCenovnika;

    /**
     * Creates new form PregledCenovnika
     */
    public PregledCenovnikaUpdateForm(IzmenaPotvrdeForm parent, boolean modal, PriceListItem priceListItem) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.stavkaCenovnika = priceListItem;
        jTable1.setModel(new TableModelCenovnik());
        popuniTabelu();

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
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biranje cenovnika");

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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Odaberi cenovnik");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Odaberite red!");
            return;
        }
        TableModelCenovnik mt = (TableModelCenovnik) jTable1.getModel();
        PriceList cenovnik = mt.vratiCenovnik(row);
        OdabirStavkeUpdateForm forma = new OdabirStavkeUpdateForm(this, true,cenovnik);
        forma.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void popuniTabelu() {
        try {
            TableModelCenovnik mt = (TableModelCenovnik) jTable1.getModel();
            ArrayList<PriceList> cenovnici = ClientController.getInstance().getAllPriceList();
            mt.ubaciListu(cenovnici);
        } catch (Exception ex) {
            Logger.getLogger(PregledCenovnikaUpdateForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void setPriceListItem(PriceListItem stavka) {
        stavkaCenovnika = stavka;
        IzmenaPotvrdeForm forma = (IzmenaPotvrdeForm) getParent();
        forma.setStavkaCenovnika(stavkaCenovnika);
    }
}
