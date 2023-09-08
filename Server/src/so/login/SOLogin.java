/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import baza.DBBroker;
import controller.ServerController;
import domain.AbstractDomainObject;
import domain.User;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Petar
 */
public class SOLogin extends AbstractSO {
    
    User ulogovani;
    ArrayList<User> listaPrijavljenih;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof User)) {
            throw new Exception("Objekat nije instanca klase User");
        }
        listaPrijavljenih = ServerController.getInstance().getUlogovani();
        User korisnik = (User) ado;
        if (listaPrijavljenih != null && listaPrijavljenih.contains(korisnik)) {
            throw new Exception("Ovaj korisnik je vec ulogovan!");
        }
        
    }
    
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        User u = (User) ado;
        
        ArrayList<User> users = (ArrayList<User>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (User user1 : users) {
            if (user1.getUserName().equals(u.getUserName()) && user1.getPassword().equals(u.getPassword())) {
                ulogovani = user1;
                ServerController.getInstance().dodajUsera(ulogovani);
                return;
            }
        }
        
        throw new Exception("Ne postoji user sa ovim kredencijalima!");
        
    }
    
    public User getUlogovani() {
        return ulogovani;
    }
    
}
