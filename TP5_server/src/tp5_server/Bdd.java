/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class Bdd {

    Connection conn;

    public Bdd() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/tp5_communication";
            conn = DriverManager.getConnection(url, "root", "");
            //conn.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String book(SendData data) {
        String query = "SELECT id, remaining_places FROM drama WHERE name = '" + data.dramaName + "'";
        int remainingPlaces = 0;
        int drama_id = 0;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                drama_id = Integer.parseInt(rs.getString("id"));
                remainingPlaces = Integer.parseInt(rs.getString("remaining_places"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "No drama with this name";
        }

        remainingPlaces -= Integer.parseInt(data.numberOfPlaces);
        if (remainingPlaces < 0) {
            return "No tickets left";
        } else {
            try {
                Statement st = conn.createStatement();
                st.executeUpdate("UPDATE drama SET remaining_places=" + remainingPlaces + " WHERE name ='" + data.dramaName + "'");
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                return "An error occured";
            }
        }

        int reservationCode = (int) (Math.random() * 99999);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO reservations (drama_id, firstname, name, number_of_places, reservation_code) "
                    + "VALUES (" + drama_id + ", '" + data.firstname + "', '" + data.name + "', " + data.numberOfPlaces + ", " + reservationCode + ")");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Impossible d'effectuer votre réservation";
        }

        return "Reservation code : " + reservationCode;
    }

    public Drama getDramasFromDB() {
        ArrayList<String> names = new ArrayList();
        ArrayList<String> places = new ArrayList();
        String query = "SELECT name, remaining_places FROM drama";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                names.add(rs.getString("name"));
                places.add(rs.getString("remaining_places"));
            }
        } catch (SQLException ex) {
            System.out.println("Fail to connect to database");
            System.err.println(ex.getMessage());
        }
        String[] arrNames = new String[names.size()];
        String[] arrPlaces = new String[places.size()];
        for (int i = 0; i < names.size(); i++) {
            arrNames[i] = names.get(i);
            arrPlaces[i] = places.get(i);
        }
        return new Drama(arrNames, arrPlaces);
    }

    public void close() throws Exception {
        conn.close();
    }
}
