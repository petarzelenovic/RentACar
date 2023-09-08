/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

import so.car.*;
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
public class SOGetAllClient extends AbstractSO {

    ArrayList<Client> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("Objekat nije instanca klase Client");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> clients = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Client>) (ArrayList<?>) clients;
    }

    public ArrayList<Client> getLista() {
        return lista;
    }

}
