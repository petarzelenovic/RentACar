/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pricelist;

import so.pricelistitem.*;
import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.PriceList;
import domain.PriceListItem;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOGetAllPriceList extends AbstractSO {

    ArrayList<PriceList> lista;
    ArrayList<PriceListItem> stavke;
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PriceList)) {
            throw new Exception("Objekat nije instanca klase PriceList");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> priceLists = DBBroker.getInstance().select(ado);
        lista = (ArrayList<PriceList>) (ArrayList<?>) priceLists;

    }

    public ArrayList<PriceList> getLista() {
        return lista;
    }

}
