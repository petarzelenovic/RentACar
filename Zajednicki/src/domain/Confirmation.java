/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import enumerations.Category;
import enumerations.Currency;
import enumerations.TypeOfPayment;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Petar
 */
public class Confirmation extends AbstractDomainObject {

    private Long confirmationID;
    private Date dateFrom;
    private Date dateTo;
    private double rentPrice;
    private Category category;
    private Car car;
    private Client client;
    private PriceListItem priceListItem;
    private User user;
    PriceList pl;

    public Confirmation() {
    }

    public Confirmation(Long confirmationID, Date dateFrom, Date dateTo, double rentPrice, Category category, Car car, Client client, PriceListItem priceListItem, User user) {
        this.confirmationID = confirmationID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.rentPrice = rentPrice;
        this.category = category;
        this.car = car;
        this.client = client;
        this.priceListItem = priceListItem;
        this.user = user;
    }

    public Long getConfirmationID() {
        return confirmationID;
    }

    public void setConfirmationID(Long confirmationID) {
        this.confirmationID = confirmationID;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PriceListItem getPriceListItem() {
        return priceListItem;
    }

    public void setPriceListItem(PriceListItem priceListItem) {
        this.priceListItem = priceListItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String nazivTabele() {
        return " confirmation ";
    }

    @Override
    public String alijas() {
        return " con ";
    }

    @Override
    public String join() {
        return " JOIN car ca ON (ca.regNumber=con.regNumber) JOIN client cl ON (cl.clientID=con.clientID) JOIN pricelistitem pli ON (pli.priceListItemID=con.priceListItemID) JOIN pricelist pl ON(pl.priceListID=pli.priceListID) JOIN user u ON (u.userID=con.userID) JOIN brand b ON (b.brandID=ca.brandID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            User u = new User(rs.getLong("userID"), rs.getString("userName"),
                    rs.getString("password"), rs.getString("email"),
                    rs.getString("firstName"), rs.getString("lastName"));

            pl = new PriceList(rs.getLong("priceListID"), rs.getDate("validFrom"),
                    rs.getDate("validTo"), u, null);

            PriceListItem pli = new PriceListItem(rs.getLong("priceListItemID"),
                    pl, rs.getDouble("price"),
                    Currency.valueOf(rs.getString("currency")),
                    TypeOfPayment.valueOf(rs.getString("payingPer")), Category.valueOf(rs.getString("category")));

            Brand b = new Brand(rs.getLong("brandID"), rs.getString("brandName"), rs.getString("brandModel"));

            Car car = new Car(rs.getString("regNumber"), rs.getInt("horsePower"), rs.getString("color"), b);

            Client client = new Client(rs.getLong("clientID"), rs.getString("jmbg"),
                    rs.getString("clientName"),
                    rs.getString("clientLastName"),
                    rs.getString("clientContact"), u);

            Confirmation con = new Confirmation(rs.getLong("confirmationID"),
                    rs.getDate("dateFrom"), rs.getDate("dateTo"),
                    rs.getDouble("rentPrice"),
                    Category.valueOf(rs.getString("category")),
                    car, client, pli, u);

            lista.add(con);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (dateFrom, dateTo, rentPrice, category, regNumber, clientID, priceListItemID, userID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " confirmationID=" + confirmationID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Date(dateFrom.getTime()) + "', '" + new java.sql.Date(dateTo.getTime()) + "', " + rentPrice + ", '" + category + "', '" + car.getRegNumber() + "', " + client.getClientID() + ", " + priceListItem.getPriceListItemID() + ", " + user.getUserID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " dateFrom='" + new java.sql.Date(dateFrom.getTime()) + "', dateTo='" + new java.sql.Date(dateTo.getTime()) + "', rentPrice=" + rentPrice + ", category='" + category + "', regNumber='"+car.getRegNumber()+"', clientID="+client.getClientID()+", priceListItemID="+priceListItem.getPriceListItemID();
    }

    @Override
    public String uslov() {
//        if (pl != null) {
//            return " WHERE pl.priceListID=" + pl.getPriceListID();
//        } else {
        return "ORDER BY con.dateFrom";

//        }
    }

}
