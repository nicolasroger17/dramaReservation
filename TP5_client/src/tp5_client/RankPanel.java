/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tp5_client;

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class RankPanel extends JPanel{
    
    public RankPanel(int numero, String name, int number){
        this.setLayout(new GridLayout(1,3));
        String rankImage;
        switch(numero){
            case 0:
                rankImage = "gold";
                break;
            case 1:
                rankImage = "silver";
                break;
            case 2:
                rankImage = "bronze";
                break;
            default:
                rankImage = "white";
                break;
        }
        JLabel image = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\src\\tp5_client\\images\\" + rankImage + ".jpg"));
        JLabel userName = new JLabel(name);
        userName.setHorizontalTextPosition(JLabel.CENTER);
        userName.setHorizontalAlignment(JLabel.CENTER);
        JLabel userNumber = new JLabel(""+number);
        userNumber.setHorizontalTextPosition(JLabel.CENTER);
        userNumber.setHorizontalAlignment(JLabel.CENTER);
        this.add(image);
        this.add(userName);
        this.add(userNumber);
    }
}