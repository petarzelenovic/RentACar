/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.car;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.Car;
import domain.Client;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOAddCar extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Car)) {
            throw new Exception("Objekat nije instanca klase Car");
        }
        Car c = (Car) ado;

        ArrayList<Car> lista = (ArrayList<Car>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Car car : lista) {
            if (c.getRegNumber().equals(car.getRegNumber())) {
                throw new Exception("Automobil sa ovom registracijom vec postoji!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
