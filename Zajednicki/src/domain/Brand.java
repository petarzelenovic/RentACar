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
public class Brand extends AbstractDomainObject {

    private Long brandID;
    private String brandName;
    private String brandModel;

    @Override
    public String toString() {
        return brandName + " " + brandModel;
    }

    public Brand() {
    }

    public Brand(Long brandID, String brandName, String brandModel) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.brandModel = brandModel;
    }

    public Long getBrandID() {
        return brandID;
    }

    public void setBrandID(Long brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    @Override
    public String nazivTabele() {
        return " brand ";
    }

    @Override
    public String alijas() {
        return " b ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Brand b = new Brand(rs.getLong("brandID"), rs.getString("brandName"), rs.getString("brandModel"));

            lista.add(b);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (brandName, brandModel) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " brandID = " + brandID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + brandName + "', '" + brandModel + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " brandName='" + brandName + "', brandModel='" + brandModel + "' ";
    }

    @Override
    public String uslov() {
        return " ORDER BY b.brandName ASC";
    }

}
