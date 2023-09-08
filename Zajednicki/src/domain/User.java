/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Petar
 */
public class User extends AbstractDomainObject {

    private Long userID;
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(Long userID, String userName, String password, String email, String firstName, String lastName) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String nazivTabele() {
        return " user ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            User u = new User(rs.getLong("userID"), rs.getString("userName"),
                    rs.getString("password"), rs.getString("email"),
                    rs.getString("firstName"), rs.getString("lastName"));

            lista.add(u);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (userName, password, email, firstName, lastName) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " userID = " + userID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + userName + "', '" + password + "', '" + email + "', '" + firstName + "', '" + lastName + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " userName='" + userName + "', password='" + password + "', email='" + email + "', firstName='" + firstName + "', lastName='" + lastName + "' ";
    }

    @Override
    public String uslov() {
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

   
    

}
