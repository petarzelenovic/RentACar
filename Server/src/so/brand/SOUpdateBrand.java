/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.brand;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.Brand;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOUpdateBrand extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Brand)) {
            throw new Exception("Objekat nije instanca klase Brand");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
