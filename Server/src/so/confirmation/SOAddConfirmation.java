/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.confirmation;

import baza.DBBroker;
import controller.ServerController;
import domain.AbstractDomainObject;
import domain.Confirmation;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOAddConfirmation extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Confirmation)) {
            throw new Exception("Objekat nije instanca klase Confirmation");
        }
        Confirmation confirmation = (Confirmation) ado;
        ArrayList<Confirmation> potvrde = ServerController.getInstance().getAllConfirmation();
        for (Confirmation potvrda : potvrde) {
            if (confirmation.getCar().getRegNumber().equals(potvrda.getCar().getRegNumber()) && 
                    ((confirmation.getDateFrom().before(potvrda.getDateTo()) && confirmation.getDateFrom().after(potvrda.getDateFrom()))
                    || (confirmation.getDateTo().after(potvrda.getDateFrom()) && confirmation.getDateTo().before(potvrda.getDateTo()))
                    || (confirmation.getDateFrom().before(potvrda.getDateFrom()) && confirmation.getDateTo().after(potvrda.getDateTo())))) {
                throw new Exception("Auto je vec rentiran od " + potvrda.getDateFrom() + " do " + potvrda.getDateTo() + ", odaberite neki drugi period");
            }
        }
        // to do: ovo valja izmeni za cenovnike
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
