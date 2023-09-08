/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Car;
import domain.Confirmation;
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
public class TableModelPotvrda extends AbstractTableModel {

    ArrayList<Confirmation> lista;
    String kolone[] = {"ConfirmationID", "Date from", "Date to", "Total", "Category", "Car", "Client", "Price"};
    String parametar = "";
    String parametarBrand = "";

    public TableModelPotvrda() {
        try {
            lista = ClientController.getInstance().getAllConfirmation();
        } catch (Exception ex) {
            Logger.getLogger(TableModelPotvrda.class.getName()).log(Level.SEVERE, null, ex);
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
        Confirmation objekat = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return objekat.getConfirmationID();
            case 1:
                return sdf.format(objekat.getDateFrom());
            case 2:
                return sdf.format(objekat.getDateTo());
            case 3:
                return objekat.getRentPrice();
            case 4:
                return objekat.getCategory();
            case 5:
                return objekat.getCar();
            case 6:
                return objekat.getClient();
            case 7:
                return objekat.getPriceListItem().toString();
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<Confirmation> potvrde) {
        lista = potvrde;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public Confirmation vratiPotvrdu(int row) {
        return lista.get(row);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
    }

    public void setParametarBrand(String parametarBrand) {
        this.parametarBrand = parametarBrand;
    }

    public ArrayList<Confirmation> vratiListuFiltriranih(Date datumOd, String registracija) {
        ArrayList<Confirmation> filtrirana = new ArrayList<>();
        System.out.println(registracija);
        if (datumOd != null && registracija.equals("  -   -  ")) {
            for (Confirmation confirmation : lista) {
                if (confirmation.getDateFrom().after(datumOd)) {
                    filtrirana.add(confirmation);
                }
            }
        }

        if (datumOd == null && !registracija.equals("  -   -  ")) {
            for (Confirmation confirmation : lista) {
                if (confirmation.getCar().getRegNumber().equals(registracija)) {
                    filtrirana.add(confirmation);
                }
            }
        }

        if (datumOd != null && !registracija.equals("  -   -  ")) {
            for (Confirmation confirmation : lista) {
                if (confirmation.getCar().getRegNumber().equals(registracija) && confirmation.getDateFrom().after(datumOd)) {
                    filtrirana.add(confirmation);
                }
            }
        }

        return filtrirana;
    }

}
