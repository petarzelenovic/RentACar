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
public class SOGetClient extends AbstractSO {

    Client client;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("Objekat nije instanca klase Client");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Client klijentela = (Client) ado;
        ArrayList<AbstractDomainObject> clients = DBBroker.getInstance().select(ado);
        ArrayList<Client> lista =(ArrayList<Client>) (ArrayList<?>) clients;
        for (Client client1 : lista) {
            if(klijentela.getClientID().equals(client1.getClientID())){
                client=client1;
            }
        }
    }

    public Client getClient() {
        return client;
    }

}
