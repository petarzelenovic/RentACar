/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ServerController;
import domain.Car;
import domain.Client;
import domain.User;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class TableModelUser extends AbstractTableModel implements Runnable {

    ArrayList<User> lista;
    String kolone[] = {"Username", "email", "Ime", "Prezime"};
    String parametar = "";

    public TableModelUser() {
        try {
            lista = new ArrayList<>();
//            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelUser.class.getName()).log(Level.SEVERE, null, ex);
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
        User objekat = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return objekat.getUserName();
            case 1:
                return objekat.getEmail();
            case 2:
                return objekat.getFirstName();
            case 3:
                return objekat.getLastName();
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<User> users) {
        lista = users;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public User vratiUsera(int row) {
        return lista.get(row);
    }


    @Override
    public void run() {
        while(true){
            try {
                lista = ServerController.getInstance().getUlogovani();
                fireTableDataChanged();
                sleep(3000);
            } catch (Exception ex) {
                Logger.getLogger(TableModelUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
