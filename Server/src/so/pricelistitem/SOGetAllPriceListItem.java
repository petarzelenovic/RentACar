/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pricelistitem;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.PriceListItem;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOGetAllPriceListItem extends AbstractSO {

    ArrayList<PriceListItem> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PriceListItem)) {
            throw new Exception("Objekat nije instanca klase PriceListItem");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> priceListItems = DBBroker.getInstance().select(ado);
        lista = (ArrayList<PriceListItem>) (ArrayList<?>) priceListItems;
    }

    public ArrayList<PriceListItem> getLista() {
        return lista;
    }

}
