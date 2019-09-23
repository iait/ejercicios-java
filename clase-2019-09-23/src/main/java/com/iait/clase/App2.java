package com.iait.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;

public class App2 {

    private static final String URL = "jdbc:h2:mem:testdb;"
            + "INIT=runscript from 'src/main/resources/import.sql'";
    private static final String USER = "sa";
    private static final String PWD = "";

    public static void main(String[] args) throws Exception {
        App2 app = new App2();
        app.run();
    }

    public DataSource getDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setUrl(URL);
        ds.setUser(USER);
        ds.setPassword(PWD);
        return ds;
    }

    public void run() throws Exception {

        DataSource ds = getDataSource();

        try (Connection conn = ds.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "select * from localidades where provincia_id = ?;");
                Scanner scanner = new Scanner(System.in)) {

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
