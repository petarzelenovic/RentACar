/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Petar
 */
public class PriceList extends AbstractDomainObject {

    private Long priceListID;
    private Date validFrom;
    private Date validTo;
    private User user;

    private ArrayList<PriceListItem> priceListItems;

    public PriceList() {
        priceListItems = new ArrayList<>();
    }

    public PriceList(Long priceListID, Date validFrom, Date validTo, User user, ArrayList<PriceListItem> priceListItems) {
        this.priceListID = priceListID;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.user = user;
        this.priceListItems = priceListItems;
    }

    public Long getPriceListID() {
        return priceListID;
    }

    public void setPriceListID(Long priceListID) {
        this.priceListID = priceListID;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<PriceListItem> getPriceListItems() {
        return priceListItems;
    }

    public void setPriceListItems(ArrayList<PriceListItem> priceListItems) {
        this.priceListItems = priceListItems;
    }

    @Override
    public String nazivTabele() {
        return " pricelist ";
    }

    @Override
    public String alijas() {
        return " pl ";
    }

    @Override
    public String join() {
        return " JOIN user u ON (u.userID=pl.userID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            User u = new User(rs.getLong("userID"), rs.getString("userName"),
                    rs.getString("password"), rs.getString("email"),
                    rs.getString("firstName"), rs.getString("lastName"));

            PriceList pl = new PriceList(rs.getLong("priceListID"), rs.getDate("validFrom"),
                    rs.getDate("validTO"), u, null);

            lista.add(pl);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (validFrom, validTo, userID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " priceListID = " + priceListID;
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new java.sql.Date(validFrom.getTime()) + "', '" + new java.sql.Date(validTo.getTime()) + "', " + user.getUserID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " validFrom='" + new java.sql.Date(validFrom.getTime()) + "', validTo='" + new java.sql.Date(validTo.getTime()) + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

}
