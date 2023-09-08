/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Car;
import domain.Client;
import domain.PriceListItem;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class TableModelStavkaCenovnika extends AbstractTableModel {

    ArrayList<PriceListItem> lista;
    String kolone[] = {"RB", "Price", "Category", "Currency", "Paying per"};
    Long br = 0l;

    public TableModelStavkaCenovnika() {
        try {
            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkaCenovnika.class.getName()).log(Level.SEVERE, null, ex);
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
        PriceListItem objekat = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return objekat.getPriceListItemID();
            case 1:
                return objekat.getPrice();
            case 2:
                return objekat.getCategory();
            case 3:
                return objekat.getCurrency();
            case 4:
                return objekat.getPayingPer();
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<PriceListItem> items) {
        lista = items;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {

        lista.remove(row);
        br = 0l;
        for (PriceListItem priceListItem : lista) {
            priceListItem.setPriceListItemID(++br);
        }
        fireTableDataChanged();
    }

    public PriceListItem vratiStavku(int row) {
        return lista.get(row);
    }

    public void dodajUListu(PriceListItem stavka) {
        br = (long) lista.size() + 1;
        stavka.setPriceListItemID(br);
        lista.add(stavka);
        fireTableDataChanged();
    }

    public ArrayList<PriceListItem> vratiListu() {
        return lista;
    }

    public boolean postojiStavka(PriceListItem stavka) {
        for (PriceListItem priceListItem : lista) {
            if (priceListItem.getCategory().equals(stavka.getCategory()) && priceListItem.getPayingPer().equals(stavka.getPayingPer())) {
                return true;
            }
        }
        return false;
    }

}
