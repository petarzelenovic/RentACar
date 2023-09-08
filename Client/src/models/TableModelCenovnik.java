/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Car;
import domain.Client;
import domain.PriceList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class TableModelCenovnik extends AbstractTableModel {

    ArrayList<PriceList> lista;
    String kolone[] = {"ID", "validFrom", "validTo"};

    public TableModelCenovnik() {
        try {
            lista = new ArrayList<>();
//            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelCenovnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PriceList objekat = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return objekat.getPriceListID();
            case 1:
                return sdf.format(objekat.getValidFrom());
            case 2:
                return sdf.format(objekat.getValidTo());
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<PriceList> priceLists) {
        lista = priceLists;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public PriceList vratiCenovnik(int row) {
        return lista.get(row);
    }

    public ArrayList<PriceList> filtriraj(Date datum, Date datumDo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        ArrayList<PriceList> filtriran = new ArrayList<>();
        if (datum != null && datumDo == null) {
            for (PriceList priceList : lista) {
                if (priceList.getValidFrom().after(datum)) {
                    filtriran.add(priceList);
                }
            }
        } else if (datum == null && datumDo != null) {
            for (PriceList priceList : lista) {
                if (priceList.getValidTo().before(datumDo)) {
                    filtriran.add(priceList);
                }
            }
        } else if (datum != null && datumDo != null) {
            
            System.out.println(datum);
            System.out.println(datumDo);
            for (PriceList priceList : lista) {
                if (priceList.getValidFrom().after(datum) && priceList.getValidTo().before(datumDo)) {
                    filtriran.add(priceList);
                }
            }
        }
        return filtriran;
    }
}
