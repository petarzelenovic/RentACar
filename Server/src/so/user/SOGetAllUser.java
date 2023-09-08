/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.user;

import baza.DBBroker;
import domain.AbstractDomainObject;
import domain.User;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOGetAllUser extends AbstractSO {

    ArrayList<User> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof User)) {
            throw new Exception("Objekat nije instanca klase User");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> users = DBBroker.getInstance().select(ado);
        lista = (ArrayList<User>) (ArrayList<?>) users;
        //ovo arrayList<?> ce kastovati unutar liste
    }

    public ArrayList<User> getLista() {
        return lista;
    }

}
