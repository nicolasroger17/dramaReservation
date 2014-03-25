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
public class Rank {
    
    public String[] users;
    public int[] reservationCount;
    
    public Rank(String[] users, int[] reservationCount) {
        this.users = users;
        this.reservationCount = reservationCount;
    }
    
}