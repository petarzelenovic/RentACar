/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.car;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.Car;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOGetAllCar extends AbstractSO {

    ArrayList<Car> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Car)) {
            throw new Exception("Objekat nije instanca klase Car");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> cars = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Car>) (ArrayList<?>) cars;
    }

    public ArrayList<Car> getLista() {
        return lista;
    }

}
