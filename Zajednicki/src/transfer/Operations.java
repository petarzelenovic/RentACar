/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package transfer;

/**
 *
 * @author Petar
 */
public interface Operations {

    public static final int LOGIN = 1;

    public static final int ADD_BRAND = 2;
    public static final int GET_ALL_BRAND = 3;

    public static final int ADD_CAR = 4;
    public static final int DELETE_CAR = 5;
    public static final int UPDATE_CAR = 6;
    public static final int GET_ALL_CAR = 7;

    public static final int ADD_CLIENT = 8;
    public static final int UPDATE_CLIENT = 9;
    public static final int GET_ALL_CLIENT = 10;

    public static final int ADD_CONFIRMATION = 11;
    public static final int UPDATE_CONFIRMATION = 12;
    public static final int GET_ALL_CONFIRMATION = 13;

    public static final int ADD_PRICELIST = 14;
    public static final int GET_ALL_PRICELIST = 15;
    public static final int UPDATE_PRICELIST = 16;

    public static final int ADD_PRICE_LIST_ITEM = 17;
    public static final int GET_ALL_PRICE_LIST_ITEM = 18;
    public static final int UPDATE_PRICE_LIST_ITEM = 19;

    public static final int GET_ALL_USER = 20;

    public static final int LOGOUT = 21;
    public static final int GET_CLIENT = 22;
}
