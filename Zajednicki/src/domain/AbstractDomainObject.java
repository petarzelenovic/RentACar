/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Petar
 */
public abstract class AbstractDomainObject implements Serializable {

    public abstract String nazivTabele();
    //naziv tabele

    public abstract String alijas();
    //alijas tabele

    public abstract String join();
    //uslov po kom spajamo tabele

    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;

    public abstract String koloneZaInsert();
    //ako ubacijeumo u sistem, koje kolone popunjavamo (ako je autoincrement necemo ubacivati ovde)

    public abstract String vrednostZaPrimarniKljuc();
    //primarni kljucevi npr. confirmationID

    public abstract String vrednostiZaInsert();
    //konkretne vrednosti koje se ubacuju Petar petrovic

    public abstract String vrednostiZaUpdate();
    //isto ko gore

    public abstract String uslov();
    //uslov, najcesce slabi obj imaju

}
