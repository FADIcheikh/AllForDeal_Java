/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Client;

/**
 *
 * @author Maynoo
 */
public class LoginInfo {

    public static Client client;
    public static String localhost="AllForDealFinalWeb/web/frontoffice/images/uploads/";
    public static String cwamp ="C:/wamp/www/";
    public static String http = "http://localhost/";
    public static Client getClient() {
        return client;
    }

    public static void setClient(Client c) {
        LoginInfo.client = c;
    }
}
