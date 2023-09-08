/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.pricelist;

import so.pricelistitem.*;
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
public class SOUpdatePriceList extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof PriceList)) {
            throw new Exception("Objekat nije instanca klase PriceList");
        }

        PriceList cenovnik = (PriceList) ado;

        ArrayList<PriceList> lista = (ArrayList<PriceList>) (ArrayList<?>) ServerController.getInstance().getAllPriceList();
        for (PriceList priceList : lista) {
            if (!priceList.getPriceListID().equals(cenovnik.getPriceListID())) {
                if ((priceList.getValidFrom().before(cenovnik.getValidTo()) && priceList.getValidFrom().after(cenovnik.getValidFrom()))
                        || (priceList.getValidTo().after(cenovnik.getValidFrom()) && priceList.getValidTo().before(cenovnik.getValidTo()))
                        || (priceList.getValidFrom().before(cenovnik.getValidFrom()) && priceList.getValidTo().after(cenovnik.getValidTo()))) {
                    throw new Exception("Vec postoji cenovnik za ovaj period!");
                }
            }

        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
