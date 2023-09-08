/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import domain.AbstractDomainObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Petar
 */
public class DBBroker {

    private static DBBroker instance;
    private Connection connection;

    private DBBroker() throws Exception {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            if (ex instanceof FileNotFoundException) {
                throw new Exception("NEMA FAJLA");
            }
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBBroker getInstance() throws Exception {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public ArrayList<AbstractDomainObject> select(AbstractDomainObject ado) throws SQLException {
        String query = "SELECT * FROM" + ado.nazivTabele() + ado.alijas() + " " + ado.join() + " " + ado.uslov();
        System.out.println(query);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        return ado.vratiListu(rs);
        //vrati listu pravi listu od ResultSeta tj od tabele
    }

    public PreparedStatement insert(AbstractDomainObject ado) throws SQLException {
        String query = "INSERT INTO " + ado.nazivTabele() + " " + ado.koloneZaInsert() + " VALUES(" + ado.vrednostiZaInsert() + ")";
        System.out.println(query);
        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public void update(AbstractDomainObject ado) throws SQLException {
        String query = "UPDATE " + ado.nazivTabele() + " SET " + ado.vrednostiZaUpdate() + " WHERE " + ado.vrednostZaPrimarniKljuc();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void delete(AbstractDomainObject ado) throws SQLException {
        String query = "DELETE FROM " + ado.nazivTabele() + " WHERE " + ado.vrednostZaPrimarniKljuc();
        System.out.println(query);
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

}
