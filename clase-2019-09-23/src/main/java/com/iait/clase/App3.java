package com.iait.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.sql.DataSource;

public class App3 {

    private static DataSourceProvider dsProvider;

    static {
        dsProvider = new PoolProvider();
    }

    public static void main(String[] args) throws Exception {
        App3 app = new App3();
        app.run();
    }

    public void run() throws Exception {

        DataSource ds = dsProvider.getDataSource();

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
