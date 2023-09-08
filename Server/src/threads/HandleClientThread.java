/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import controller.ServerController;
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
import transfer.Operations;
import transfer.Receiver;
import transfer.Request;
import transfer.Response;
import transfer.ResponseStatus;
import transfer.Sender;

/**
 *
 * @author Petar
 */
public class HandleClientThread extends Thread {

    private Socket socket;

    public HandleClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
                ous.writeObject(response);
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.SUCCESS);
        try {
            switch (request.getOperacija()) {
                case Operations.LOGIN:
                    User u = (User) request.getArgument();
                    User ulogovan = ServerController.getInstance().login(u);
                    response.setOdgovor(ulogovan);
                    break;
                case Operations.ADD_BRAND:
                    ServerController.getInstance().addBrand((Brand) request.getArgument());
                    break;
//                case Operations.UPDATE_BRAND:
//                    ServerController.getInstance().updateBrand((Brand) request.getArgument());
//                    break;
                case Operations.ADD_CAR:
                    ServerController.getInstance().addCar((Car) request.getArgument());
                    break;
                case Operations.UPDATE_CAR:
                    ServerController.getInstance().updateCar((Car) request.getArgument());
                    break;
                case Operations.DELETE_CAR:
                    ServerController.getInstance().deleteCar((Car) request.getArgument());
                    break;
                case Operations.GET_ALL_CAR:
                    response.setOdgovor(ServerController.getInstance().getAllCar());
                    break;
                case Operations.ADD_CLIENT:
                    ServerController.getInstance().addClient((Client) request.getArgument());
                    break;
                case Operations.UPDATE_CLIENT:
                    ServerController.getInstance().updateClient((Client) request.getArgument());
                    break;

                case Operations.ADD_CONFIRMATION:
                    ServerController.getInstance().addConfirmation((Confirmation) request.getArgument());
                    break;
                case Operations.UPDATE_CONFIRMATION:
                    ServerController.getInstance().updateConfirmation((Confirmation) request.getArgument());
                    break;
//                case Operations.DELETE_CONFIRMATION:
//                    ServerController.getInstance().deleteConfirmation((Confirmation) request.getArgument());
//                    break;
                case Operations.GET_ALL_CONFIRMATION:
                    response.setOdgovor(ServerController.getInstance().getAllConfirmation());
                    break;

                case Operations.ADD_PRICELIST:
                    ServerController.getInstance().addPriceList((PriceList) request.getArgument());
                    break;

                case Operations.ADD_PRICE_LIST_ITEM:
                    ServerController.getInstance().addPriceListItem((PriceListItem) request.getArgument());
                    break;
                case Operations.GET_ALL_PRICE_LIST_ITEM:
                    response.setOdgovor(ServerController.getInstance().getAllPriceListItem((PriceList) request.getArgument()));
                    break;

                case Operations.GET_ALL_USER:
                    response.setOdgovor(ServerController.getInstance().getAllUser());
                    break;

                case Operations.GET_ALL_BRAND:
                    response.setOdgovor(ServerController.getInstance().getAllBrand());
                    break;

                case Operations.GET_ALL_CLIENT:
                    response.setOdgovor(ServerController.getInstance().getAllClient());
                    break;
                case Operations.GET_CLIENT:
                    response.setOdgovor(ServerController.getInstance().getClient((Client) request.getArgument()));
                    break;

                case Operations.GET_ALL_PRICELIST:
                    response.setOdgovor(ServerController.getInstance().getAllPriceList());
                    break;

                case Operations.UPDATE_PRICE_LIST_ITEM:
                    ServerController.getInstance().updatePriceListItem((PriceListItem) request.getArgument());
                    break;

                case Operations.UPDATE_PRICELIST:
                    ServerController.getInstance().updatePriceList((PriceList) request.getArgument());
                    break;

                case Operations.LOGOUT:
                    ServerController.getInstance().logout((User) request.getArgument());
                    break;

                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.ERROR);
            response.setException(ex);

        }
        return response;
    }

    public Socket getSocket() {
        return socket;
    }

    private Response login(Request request) {
        Response response = new Response();

        return response;
    }

}
