/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Brand;
import domain.Car;
import domain.Client;
import domain.Confirmation;
import domain.PriceList;
import domain.PriceListItem;
import domain.User;
import java.util.ArrayList;
import so.brand.SOAddBrand;
import so.brand.SOGetAllBrand;
import so.brand.SOUpdateBrand;
import so.car.SOAddCar;
import so.car.SODeleteCar;
import so.car.SOGetAllCar;
import so.car.SOUpdateCar;
import so.client.SOAddClient;
import so.client.SOGetAllClient;
import so.client.SOGetClient;
import so.client.SOUpdateClient;
import so.confirmation.SOAddConfirmation;
import so.confirmation.SODeleteConfirmation;
import so.confirmation.SOGetAllConfirmation;
import so.confirmation.SOUpdateConfirmation;
import so.login.SOLogin;
import so.pricelist.SOAddPriceList;
import so.pricelist.SOGetAllPriceList;
import so.pricelist.SOUpdatePriceList;
import so.pricelistitem.SOAddPriceListItem;
import so.pricelistitem.SOGetAllPriceListItem;
import so.pricelistitem.SOUpdatePriceListItem;
import so.user.SOGetAllUser;

/**
 *
 * @author Petar
 */
public class ServerController {

    private static ServerController instance;
    ArrayList<User> ulogovani;

    private ServerController() {
        ulogovani = new ArrayList<>();
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public User login(User user) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(user);
        return so.getUlogovani();
    }

    //pozivanje operacija za brend
    public void addBrand(Brand brand) throws Exception {
        (new SOAddBrand()).templateExecute(brand);
    }

    public void updateBrand(Brand brand) throws Exception {
        (new SOUpdateBrand()).templateExecute(brand);
    }

    //pozivanje operacija za car
    public void addCar(Car car) throws Exception {
        (new SOAddCar()).templateExecute(car);
    }

    public void updateCar(Car car) throws Exception {
        (new SOUpdateCar()).templateExecute(car);
    }

    public void deleteCar(Car car) throws Exception {
        (new SODeleteCar()).templateExecute(car);
    }

    //pozivanje operacija za clienta
    public void addClient(Client client) throws Exception {
        (new SOAddClient()).templateExecute(client);
    }

    public void updateClient(Client client) throws Exception {
        (new SOUpdateClient()).templateExecute(client);
    }

    public void updatePriceListItem(PriceListItem pli) throws Exception {
        (new SOUpdatePriceListItem()).templateExecute(pli);
    }

    public void updatePriceList(PriceList pl) throws Exception {
        (new SOUpdatePriceList()).templateExecute(pl);
    }

    //pozivanje operacija za confirmation
    public void addConfirmation(Confirmation confirmation) throws Exception {
        (new SOAddConfirmation()).templateExecute(confirmation);
    }

    public void updateConfirmation(Confirmation confirmation) throws Exception {
        (new SOUpdateConfirmation()).templateExecute(confirmation);
    }

//    public void deleteConfirmation(Confirmation confirmation) throws Exception {
//        (new SODeleteConfirmation()).templateExecute(confirmation);
//    }

    //pozivanje operacije za priceList
    public void addPriceList(PriceList priceList) throws Exception {
        (new SOAddPriceList()).templateExecute(priceList);
    }

    //pozivanje operacije za priceListItem
    public void addPriceListItem(PriceListItem priceListItem) throws Exception {
        (new SOAddPriceListItem()).templateExecute(priceListItem);
    }

    //getAll operacije
    public ArrayList<Car> getAllCar() throws Exception {
        SOGetAllCar so = new SOGetAllCar();
        so.templateExecute(new Car());
        return so.getLista();
    }

    public ArrayList<Confirmation> getAllConfirmation() throws Exception {
        SOGetAllConfirmation so = new SOGetAllConfirmation();
        so.templateExecute(new Confirmation());
        return so.getLista();
    }

    public ArrayList<PriceListItem> getAllPriceListItem(PriceList cenovnik) throws Exception {
        SOGetAllPriceListItem so = new SOGetAllPriceListItem();
        PriceListItem pli = new PriceListItem();
        pli.setPriceList(cenovnik);
        so.templateExecute(pli);
        return so.getLista();
    }

    public ArrayList<User> getAllUser() throws Exception {
        SOGetAllUser so = new SOGetAllUser();
        so.templateExecute(new User());
        return so.getLista();
    }

    public ArrayList<Brand> getAllBrand() throws Exception {
        SOGetAllBrand so = new SOGetAllBrand();
        so.templateExecute(new Brand());
        return so.getLista();
    }

    public ArrayList<Client> getAllClient() throws Exception {
        SOGetAllClient so = new SOGetAllClient();
        so.templateExecute(new Client());
        return so.getLista();
    }
    
    public Client getClient(Client client) throws Exception {
        SOGetClient so = new SOGetClient();
        so.templateExecute(client);
        return so.getClient();
    }

    public Object getAllPriceList() throws Exception {
        SOGetAllPriceList so = new SOGetAllPriceList();
        so.templateExecute(new PriceList());
        return so.getLista();
    }

    public ArrayList<User> getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(ArrayList<User> ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void dodajUsera(User user) {
        ulogovani.add(user);
    }

    public void logout(User user) {
        for (User user1 : ulogovani) {
            if (user1.equals(user)) {
                ulogovani.remove(user1);
                return;
            }
        }
    }

}
