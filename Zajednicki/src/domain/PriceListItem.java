/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enumerations.Category;
import enumerations.TypeOfPayment;
import enumerations.Currency;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Petar
 */
public class PriceListItem extends AbstractDomainObject {

    private Long priceListItemID;
    private PriceList priceList;
//    private double total;
    private double price;
    private Currency currency;
    private TypeOfPayment payingPer;
    private Category category;

    @Override
    public String toString() {
        return price + " " + currency + " | " + payingPer + " | " + category;
    }

    public PriceListItem() {
    }

    public PriceListItem(Long priceListItemID, PriceList priceList, double price, Currency currency, TypeOfPayment payingPer, Category category) {
        this.priceListItemID = priceListItemID;
        this.priceList = priceList;
        this.price = price;
        this.currency = currency;
        this.payingPer = payingPer;
        this.category = category;
    }

  

    public Long getPriceListItemID() {
        return priceListItemID;
    }

    public void setPriceListItemID(Long priceListItemID) {
        this.priceListItemID = priceListItemID;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TypeOfPayment getPayingPer() {
        return payingPer;
    }

    public void setPayingPer(TypeOfPayment payingPer) {
        this.payingPer = payingPer;
    }

    @Override
    public String nazivTabele() {
        return " pricelistitem ";
    }

    @Override
    public String alijas() {
        return " pli ";
    }

    @Override
    public String join() {
        return " JOIN priceList pl ON (pl.priceListID=pli.priceListID) JOIN user u ON (pl.userID=u.userID) ";
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

            PriceListItem pli = new PriceListItem(rs.getLong("priceListItemID"),
                    pl, rs.getDouble("price"),
                    Currency.valueOf(rs.getString("currency")),
                    TypeOfPayment.valueOf(rs.getString("payingPer")), Category.valueOf(rs.getString("category")));

            lista.add(pli);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (priceListID, price, currency, payingPer, category) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " priceListID = " + priceList.getPriceListID() + " AND priceListItemID=" + priceListItemID;
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + priceList.getPriceListID() + ", " + price + ", '" + String.valueOf(currency) + "', '" + String.valueOf(payingPer) + "', '" + category + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " price=" + price + ", currency='" + currency + "' ";
    }

    @Override
    public String uslov() {
        return " WHERE pli.priceListID=" + priceList.getPriceListID();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
