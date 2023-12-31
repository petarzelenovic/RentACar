/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package form.pricelist;

import forms.*;
import controller.ClientController;
import domain.PriceList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.TableModelCenovnik;

/**
 *
 * @author Petar
 */
public class IzmenaCenovnikaForm extends javax.swing.JDialog {

    /**
     * Creates new form IzmenaCenovnikaForm
     */
    public IzmenaCenovnikaForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
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
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jFormattedDatumOd = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jFormattedDatumDo = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Izmena cenovnika");

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

        jButton1.setText("Izmeni");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Datum od:");

        jButton2.setText("Filter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jFormattedDatumOd.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Datum do:");

        jFormattedDatumDo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedDatumOd, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedDatumDo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jFormattedDatumOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedDatumDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
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
        PriceList priceList = mt.vratiCenovnik(row);
        IzmeniCenovnikForm form = new IzmeniCenovnikForm(this, true, priceList);
        form.setVisible(true);
        popuniTabelu();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            Date datumOd = null;
            Date datumDo = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

            TableModelCenovnik mt = (TableModelCenovnik) jTable1.getModel();
            if (!jFormattedDatumOd.getText().equals("")) {
                datumOd = sdf.parse(jFormattedDatumOd.getText());
            }

            if (!jFormattedDatumDo.getText().equals("")) {
                datumDo = sdf.parse(jFormattedDatumDo.getText());
            }

            if (jFormattedDatumDo.getText().isEmpty() && jFormattedDatumOd.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Unesite bar jedan datum");
                return;
            }

            ArrayList<PriceList> filtrirana = mt.filtriraj(datumOd, datumDo);
            mt.ubaciListu(filtrirana);

        } catch (ParseException ex) {
            Logger.getLogger(IzmenaCenovnikaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jFormattedDatumOd.setText("");
        jFormattedDatumDo.setText("");
        popuniTabelu();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedDatumDo;
    private javax.swing.JFormattedTextField jFormattedDatumOd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public void popuniTabelu() {
        try {
            TableModelCenovnik mt = (TableModelCenovnik) jTable1.getModel();
            ArrayList<PriceList> lista = ClientController.getInstance().getAllPriceList();
            for (PriceList priceList : lista) {
                System.out.println(priceList.getPriceListItems());
            }
            mt.ubaciListu(lista);

        } catch (Exception ex) {
            Logger.getLogger(IzmenaCenovnikaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
