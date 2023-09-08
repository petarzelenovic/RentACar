/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.Client;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOAddClient extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("Objekat nije instanca klase Client");
        }

        Client c = (Client) ado;

        ArrayList<Client> lista = (ArrayList<Client>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Client client : lista) {
            if (c.getJmbg().equals(client.getJmbg())) {
                throw new Exception("Klijent sa ovim JMBGOM postoji");
            }
            if (c.getClientContact().equals(client.getClientContact())) {
                throw new Exception("Klijent sa ovim kontaktom postoji");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
