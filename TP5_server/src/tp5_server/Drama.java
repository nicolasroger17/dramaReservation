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
public class Drama{
    String[] names;
    String[] numberOfPlaces;
    
    public Drama(String[] names, String[] numberOfPlaces) {
        this.names = names;
        this.numberOfPlaces = numberOfPlaces;
    }
    
    public Drama() {
        this.names = null;
        this.numberOfPlaces = null;
    }
}
