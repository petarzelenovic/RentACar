/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pricelistitem;

import baza.DBBroker;
import controller.ServerController;
import domain.AbstractDomainObject;
import domain.PriceList;
import domain.PriceListItem;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOUpdatePriceListItem extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PriceListItem)) {
            throw new Exception("Objekat nije instanca klase PriceListItem");
        }
     

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
