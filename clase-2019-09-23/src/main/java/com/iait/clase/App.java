package com.iait.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.h2.tools.Server;

public class App {

    private static final String URL = "jdbc:h2:mem:testdb;"
            + "INIT=runscript from 'src/main/resources/import.sql'";
    private static final String USER = "sa";
    private static final String PWD = "";

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run();
    }

    public void run() throws Exception {

        Class.forName("org.h2.Driver");

        try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
                PreparedStatement stmt = conn.prepareStatement(
                        "select * from localidades where provincia_id = ?;");
                Scanner scanner = new Scanner(System.in)) {

            Server.main();

            System.out.println("Ingrese el id de provincia:");
            Integer provinciaId = scanner.nextInt();
            stmt.setInt(1, provinciaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                System.out.println(String.format("Localidad: [%s] %s", id, nombre));
            }

            scanner.nextLine();
        }
    }
}
