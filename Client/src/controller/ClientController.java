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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import session.Session;
import transfer.Operations;
import transfer.Request;
import transfer.Response;
import transfer.ResponseStatus;

/**
 *
 * @author Petar
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {

    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public User login(User user) throws Exception {
        return (User) sendRequest(user, Operations.LOGIN);
    }

    public void addBrand(Brand brand) throws Exception {
        sendRequest(brand, Operations.ADD_BRAND);
    }

    public void addCar(Car car) throws Exception {
        sendRequest(car, Operations.ADD_CAR);
    }

    public void addClient(Client client) throws Exception {
        sendRequest(client, Operations.ADD_CLIENT);
    }

    public void addConfirmation(Confirmation confirmation) throws Exception {
        sendRequest(confirmation, Operations.ADD_CONFIRMATION);
    }

    public void addPriceList(PriceList priceList) throws Exception {
        sendRequest(priceList, Operations.ADD_PRICELIST);
    }

    public void addPriceListItem(PriceListItem priceListItem) throws Exception {
        sendRequest(priceListItem, Operations.ADD_PRICE_LIST_ITEM);
    }

//    public void updateBrand(Brand brand) throws Exception {
//        sendRequest(brand, Operations.UPDATE_BRAND);
//    }
    public void updateCar(Car car) throws Exception {
        sendRequest(car, Operations.UPDATE_CAR);
    }

    public void updateClient(Client client) throws Exception {
        sendRequest(client, Operations.UPDATE_CLIENT);
    }

    public void updatePriceListItem(PriceListItem pli) throws Exception {
        sendRequest(pli, Operations.UPDATE_PRICE_LIST_ITEM);
    }

    public void updatePriceList(PriceList pl) throws Exception {
        sendRequest(pl, Operations.UPDATE_PRICELIST);
    }

    public void updateConfirmation(Confirmation confirmation) throws Exception {
        sendRequest(confirmation, Operations.UPDATE_CONFIRMATION);
    }

    public void deleteCar(Car car) throws Exception {
        sendRequest(car, Operations.DELETE_CAR);
    }

//   public void deleteConfirmation(Confirmation confirmation) throws Exception {
//       sendRequest(confirmation, Operations.DELETE_CONFIRMATION);
//    }
    public ArrayList<Car> getAllCar() throws Exception {
        return (ArrayList<Car>) sendRequest(null, Operations.GET_ALL_CAR);
    }

    public ArrayList<Confirmation> getAllConfirmation() throws Exception {
        return (ArrayList<Confirmation>) sendRequest(null, Operations.GET_ALL_CONFIRMATION);
    }

    public ArrayList<PriceListItem> getAllPriceListItem(PriceList cenovnik) throws Exception {
        return (ArrayList<PriceListItem>) sendRequest(cenovnik, Operations.GET_ALL_PRICE_LIST_ITEM);
    }

    public ArrayList<User> getAllUser() throws Exception {
        return (ArrayList<User>) sendRequest(null, Operations.GET_ALL_USER);
    }

    public ArrayList<Brand> getAllBrand() throws Exception {
        return (ArrayList<Brand>) sendRequest(null, Operations.GET_ALL_BRAND);
    }

    public ArrayList<Client> getAllClient() throws Exception {
        return (ArrayList<Client>) sendRequest(null, Operations.GET_ALL_CLIENT);
    }

    public Client getClient(Client client) throws Exception {
        return (Client) sendRequest(client, Operations.GET_CLIENT);
    }

    public ArrayList<PriceList> getAllPriceList() throws Exception {
        return (ArrayList<PriceList>) sendRequest(null, Operations.GET_ALL_PRICELIST);
    }

    private Object sendRequest(Object data, int operation) throws Exception {
        Request request = new Request(data, operation);

        ObjectOutputStream ous = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        ous.writeObject(request);

        ObjectInputStream ois = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) ois.readObject();

        System.out.println(response);
        if (response.getResponseStatus().equals(ResponseStatus.ERROR)) {
            throw response.getException();
        } else {
            return response.getOdgovor();
        }
    }

    public void logout(User ulogovani) throws Exception {
        sendRequest(ulogovani, Operations.LOGOUT);
    }

}
