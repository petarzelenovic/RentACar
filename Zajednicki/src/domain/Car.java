/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Petar
 */
public class Car extends AbstractDomainObject {

    private String regNumber;
    private int horsePower;
    private String color;
    private Brand brand;

    @Override
    public String toString() {
        return regNumber+" | "+brand.getBrandName()+" | "+brand.getBrandModel();
    }

    
    
    
    public Car() {
    }

    public Car(String regNumber, int horsePower, String color, Brand brand) {
        this.regNumber = regNumber;
        this.horsePower = horsePower;
        this.color = color;
        this.brand = brand;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String nazivTabele() {
        return " car ";
    }

    @Override
    public String alijas() {
        return " ca ";
    }

    @Override
    public String join() {
        return " JOIN brand b ON (b.brandID=ca.brandID) ";
    }
    
    

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Brand b = new Brand(rs.getLong("brandID"), rs.getString("brandName"), rs.getString("brandModel"));

            Car car = new Car(rs.getString("regNumber"), rs.getInt("horsePower"), rs.getString("color"), b);

            lista.add(car);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (regNumber, horsePower, color, brandID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " regNumber ='" + regNumber + "'";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + regNumber + "', " + +horsePower + ", '" + color + "', " + brand.getBrandID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " horsePower=" + horsePower + ", color='" + color + "' ";
    }

    @Override
    public String uslov() {
//        return " WHERE ca.regNumber='" + regNumber+"'";
        return "";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
