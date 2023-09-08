/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.car;

import baza.DBBroker;
import controller.ServerController;
import domain.AbstractDomainObject;
import domain.Car;
import domain.Confirmation;
import java.util.ArrayList;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SODeleteCar extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Car)) {
            throw new Exception("Objekat nije instanca klase Car");
        }
        Car auto = (Car) ado;
        ArrayList<Confirmation> potvrde = ServerController.getInstance().getAllConfirmation();
        for (Confirmation confirmation : potvrde) {
            if (confirmation.getCar().getRegNumber().equals(auto.getRegNumber())) {
                Date danasnji = new Date();
                if (confirmation.getDateTo().after(danasnji)) {
                    throw new Exception("Auto je trenutno ili u buducnosti rentiran, ne moze se obrisati");
                }
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
