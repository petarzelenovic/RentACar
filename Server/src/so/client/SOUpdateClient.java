/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

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
public class SOUpdateClient extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("Objekat nije instanca klase Client");
        }

        Client newClient = (Client) ado;

        ArrayList<Client> lista = (ArrayList<Client>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Client client : lista) {
            if (!client.getJmbg().equals(newClient.getJmbg())) {
                if (client.getClientContact().equals(newClient.getClientContact())) {
                    throw new Exception("Klijent sa ovim kontaktom vec postoji!");
                }
            }

        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
