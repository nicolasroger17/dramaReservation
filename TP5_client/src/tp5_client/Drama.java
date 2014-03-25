/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp5_client;

import java.io.Serializable;

/**
 *
 * @author Nicolas
 */
public class Drama implements Serializable{
    static private final long serialVersionUID = 6L;
    String[] names;
    String[] numberOfPlaces;
    
    public Drama(String[] names, String[] numberOfPlaces) {
        this.names = names;
        this.numberOfPlaces = numberOfPlaces;
    }
}
