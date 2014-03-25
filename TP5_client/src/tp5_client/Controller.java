/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5_client;

import com.google.gson.Gson;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 * 
 * chat with the server and update the frame
 */
public final class Controller {

    static Socket socket;
    static PrintWriter outSocket;
    static BufferedReader inSocket;
    Drama drama;
    TP5Frame frame;
    Gson gson;

    /**
     * Creates new form reservation_form
     *
     * @throws java.lang.Exception
     */
    public Controller() throws Exception {
        // object that allows to serialize in JSON
        gson = new Gson();
        // socket to connect to the server
        socket = new Socket("localhost", 4444);
        outSocket = new PrintWriter(socket.getOutputStream(), true);
        inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //to get the ip address
        System.out.println((java.net.InetAddress.getLocalHost()).toString());

        System.out.println("Requesting dramas list from server");
        
        //first request to get the dramas list
        outSocket.println(gson.toJson(new SendData("getDramas")));
        
        //transform the answer in object and create the frame with the data
        this.drama = gson.fromJson(inSocket.readLine(), Drama.class);
        this.frame = new TP5Frame(this);
        
        outSocket.println(gson.toJson(new SendData("rank")));
        new RankFrame(gson.fromJson(inSocket.readLine(), Rank.class));
        
        // tells the server to shutdown when the client leaves
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                outSocket.println(gson.toJson(new SendData("close")));
                e.getWindow().dispose();
                System.exit(1);
            }
        });
    }

    public void book(SendData sendData) throws Exception {
        outSocket.println(gson.toJson(sendData));
        // a bit dirty but didn't want to create another object :(
        final String response = inSocket.readLine();
        frame.setReservationStatus(response);
        
        // timer to let some time to the frame to display
        // the message before playing the little music
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            
            @Override
            public void run() {
                try {
                    playSound(response.substring(0, 11).equals("Reservation"));
                    timer.cancel();
                } catch (Exception ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 1000);
    }

    public void playSound(boolean isSuccess) throws Exception{
        String music = isSuccess?"success":"fail";
        String linkToMusic = System.getProperty("user.dir") + "\\src\\tp5_client\\music\\" + music + ".wav";
        System.out.println("linkToMusic : "+linkToMusic);
        new SoundIsSimple(linkToMusic);
    }
}