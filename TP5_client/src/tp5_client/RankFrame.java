/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp5_client;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Nicolas
 */
public class RankFrame extends JFrame{
    
    public RankFrame(Rank rank){
        
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setTitle("Ranks");
         this.setLocation(500, 0);
         this.setLayout(new GridLayout(rank.users.length,1));
         for(int i = 0; i < rank.users.length; i++){
             this.add(new RankPanel(i, rank.users[i], rank.reservationCount[i]));
         }
         pack();
         this.setVisible(true);
    }
}
