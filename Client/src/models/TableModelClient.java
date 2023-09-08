/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Car;
import domain.Client;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class TableModelClient extends AbstractTableModel {

    ArrayList<Client> lista;
    String kolone[] = {"JBMG", "Ime", "Prezime", "Kontakt"};
    String parametar = "";

    public TableModelClient() {
        try {
            lista = ClientController.getInstance().getAllClient();
//            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelClient.class.getName()).log(Level.SEVERE, null, ex);
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
        Client objekat = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return objekat.getJmbg();
            case 1:
                return objekat.getClientName();
            case 2:
                return objekat.getClientLastName();
            case 3:
                return objekat.getClientContact();
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<Client> clients) {
        lista = clients;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public Client vratiKlijenta(int row) {
        return lista.get(row);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
    }

    public void refreshTabele() {
        try {
            lista = ClientController.getInstance().getAllClient();

            if (!parametar.equals("")) {
                ArrayList<Client> novaLista = new ArrayList<>();
                for (Client client : lista) {
                    if (client.getClientName().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(client);
                    }
                }
                lista = novaLista;

            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
