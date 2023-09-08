/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.confirmation;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.Confirmation;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SODeleteConfirmation extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Confirmation)) {
            throw new Exception("Objekat nije instanca klase Confirmation");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
