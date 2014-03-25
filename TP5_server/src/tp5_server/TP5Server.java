package tp5_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TP5Server {

    ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        new TP5Server().begin(4444);
    }

    public void begin(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for clients to connect on port " + port + "...");
            new ProtocolThread(serverSocket.accept()).start();
            //Thread.start() calls Thread.run()
        }
    }
}

class ProtocolThread extends Thread {

    Socket socket;
    PrintWriter out_socket;
    BufferedReader in_socket;
    Gson gson;
    boolean isOver = false;
    Bdd bdd;
    
    public ProtocolThread(Socket socket) {
        gson = new Gson();
        bdd = new Bdd();
        System.out.println("Accepting connection from " + socket.getInetAddress() + "...");
        this.socket = socket;
        try {
            out_socket = new PrintWriter(socket.getOutputStream(), true);
            in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Success to connect to bdd");
    }

    @Override
    public void run() {
        try {
            // listen until the client says it's over
            while(!isOver){
                System.out.println("Waiting for a reservation...");
                SendData data = gson.fromJson(in_socket.readLine(), SendData.class);
                System.out.println("action : " + data.action);
                switch (data.action) {
                    case "getDramas":
                        out_socket.println(gson.toJson(bdd.getDramasFromDB()));
                        break;
                    case "book":
                        out_socket.println(bdd.book(data));
                        break;
                    case "rank":
                        out_socket.println(gson.toJson(bdd.getRanksFromDB()));
                        break;
                    case "close":
                        isOver = true;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("Closing connection.");
                socket.close();
                try {
                    bdd.close();
                } catch (Exception ex) {
                    Logger.getLogger(ProtocolThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}