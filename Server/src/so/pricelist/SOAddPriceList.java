/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pricelist;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.PriceList;
import domain.PriceListItem;
import java.util.ArrayList;
import so.AbstractSO;
import java.sql.*;

/**
 *
 * @author Petar
 */
public class SOAddPriceList extends AbstractSO {
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PriceList)) {
            throw new Exception("Objekat nije instanca klase PriceList");
        }
        PriceList priceList = (PriceList) ado;
        if (priceList.getPriceListItems().isEmpty()) {
            throw new Exception("Cenovnik mora da ima bar jednu stavku!");
        }
        ArrayList<PriceList> cenovnici = (ArrayList<PriceList>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (PriceList cenovnik : cenovnici) {
            if ((priceList.getValidFrom().before(cenovnik.getValidTo()) && priceList.getValidFrom().after(cenovnik.getValidFrom()))
                    || (priceList.getValidTo().after(cenovnik.getValidFrom()) && priceList.getValidTo().before(cenovnik.getValidTo()))
                    || (priceList.getValidFrom().before(cenovnik.getValidFrom()) && priceList.getValidTo().after(cenovnik.getValidTo()))) {
                throw new Exception("Vec postoji cenovnik za ovaj period!");
            }
        }
    }
    
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        //vratimo ps sa generisanim kljucem
        PreparedStatement preparedStatement = DBBroker.getInstance().insert(ado);
        PriceList priceList = (PriceList) ado;

        //uzimamo kljuc i setujemo za stavku
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            Long priceListID = rs.getLong(1);
            priceList.setPriceListID(priceListID);
        }

        //dodajemo jednu po jednu stavku
        //nakon sto postavimo da je vezano za taj cenovnik  
        for (PriceListItem priceListItem : priceList.getPriceListItems()) {
            priceListItem.setPriceList(priceList);
            DBBroker.getInstance().insert(priceListItem);
        }
        
    }
    
}
