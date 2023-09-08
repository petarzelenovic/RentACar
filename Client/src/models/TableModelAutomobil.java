/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ClientController;
import domain.Car;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Petar
 */
public class TableModelAutomobil extends AbstractTableModel {

    ArrayList<Car> lista;
    String kolone[] = {"Registarski broj", "Konjska snaga", "Brend", "Model"};
    String parametar = "";
    String parametarBrand = "";

    public TableModelAutomobil() {
        try {
            lista = ClientController.getInstance().getAllCar();
//            lista = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(TableModelAutomobil.class.getName()).log(Level.SEVERE, null, ex);
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
        Car objekat = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return objekat.getRegNumber();
            case 1:
                return objekat.getHorsePower();
            case 2:
                return objekat.getBrand().getBrandName();
            case 3:
                return objekat.getBrand().getBrandModel();
            default:
                return "return!";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void ubaciListu(ArrayList<Car> cars) {
        lista = cars;
        fireTableDataChanged();
    }

    public void obrisiRed(int row) {
        lista.remove(row);
        fireTableDataChanged();
    }

    public Car vratiAutomobil(int row) {
        return lista.get(row);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
    }

    public void setParametarBrand(String parametarBrand) {
        this.parametarBrand = parametarBrand;
    }

    public void refreshTabele() {
        try {
            lista = ClientController.getInstance().getAllCar();

            if (!parametar.equals("  -   -  ") && parametarBrand.isEmpty()) {
                ArrayList<Car> novaLista = new ArrayList<>();
                for (Car car : lista) {
                    if (car.getRegNumber().contains(parametar)) {
                        novaLista.add(car);
                    }
                }
                lista = novaLista;
            } else if (!parametarBrand.isEmpty() && parametar.equals("  -   -  ")) {
                ArrayList<Car> novaLista = new ArrayList<>();
                for (Car car : lista) {
                    if (car.getBrand().getBrandName().toLowerCase().contains(parametarBrand.toLowerCase())) {
                        novaLista.add(car);
                    }
                }
                lista = novaLista;
            } else if (!parametar.equals("  -   -  ") && !parametarBrand.isEmpty()) {
                ArrayList<Car> novaLista = new ArrayList<>();
                for (Car car : lista) {
                    if (car.getBrand().getBrandName().toLowerCase().contains(parametarBrand.toLowerCase()) && car.getRegNumber().contains(parametar)) {
                        novaLista.add(car);
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
