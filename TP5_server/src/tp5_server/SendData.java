/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp5_server;

/**
 *
 * @author Nicolas
 */
public class SendData{
    String action;
    String firstname;
    String name;
    String dramaName;
    String numberOfPlaces;
    
    public SendData(String action) {
        this.action = action;
        this.firstname = "";
        this.name = "";
        this.dramaName = "";
        this.numberOfPlaces = "";
    }
    
    public SendData(String action, String firstname, String name, String dramaName, String numberOfPlaces) {
        this.action = action;
        this.firstname = firstname;
        this.name = name;
        this.dramaName = dramaName;
        this.numberOfPlaces = numberOfPlaces;
    }    
}