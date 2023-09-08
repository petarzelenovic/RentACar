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
public class Client extends AbstractDomainObject {

    private Long clientID;
    private String jmbg;
    private String clientName;
    private String clientLastName;
    private String clientContact;
    private User user;

    @Override
    public String toString() {
        return jmbg + " | " + clientName + " | " + clientLastName + " | " + clientContact;
    }

    public Client() {
    }

    public Client(Long clientID, String jmbg, String clientName, String clientLastName, String clientContact, User user) {
        this.clientID = clientID;
        this.jmbg = jmbg;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.clientContact = clientContact;
        this.user = user;
    }

    public Long getClientID() {
        return clientID;
    }

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getClientContact() {
        return clientContact;
    }

    public void setClientContact(String clientContact) {
        this.clientContact = clientContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String nazivTabele() {
        return " client ";
    }

    @Override
    public String alijas() {
        return " cl ";
    }

    @Override
    public String join() {
        return " JOIN user u ON (u.userID=cl.userID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            User u = new User(rs.getLong("userID"), rs.getString("userName"),
                    rs.getString("password"), rs.getString("email"),
                    rs.getString("firstName"), rs.getString("lastName"));

            Client c = new Client(rs.getLong("clientID"), rs.getString("jmbg"),
                    rs.getString("clientName"),
                    rs.getString("clientLastName"),
                    rs.getString("clientContact"), u);

            lista.add(c);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (jmbg, clientName, clientLastName, clientContact, userID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " clientID = " + clientID;
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + jmbg + "', '" + clientName + "', '" + clientLastName + "', '" + clientContact + "', " + user.getUserID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " clientName='" + clientName + "', clientLastName='" + clientLastName + "', clientContact='" + clientContact + "' ";
    }

    @Override
    public String uslov() {
//        return " WHERE cl.jmbg='" + jmbg + "' OR cl.clientContact='" + clientContact + "' ";
        return "";
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

}
