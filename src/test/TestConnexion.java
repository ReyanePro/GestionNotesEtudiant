package test;

import model.DBManager;
import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {
        try {
            Connection conn = DBManager.getConnection();
            System.out.println("Connexion OK !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}