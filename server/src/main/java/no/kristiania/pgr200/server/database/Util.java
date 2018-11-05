package no.kristiania.pgr200.server.database;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;

public class Util {

    public static DataSource createDataSource(String fileName) throws IOException {

        Flyway flyway = getFlyway(fileName);

        // flyway.baseline();
        // flyway.repair();
        flyway.migrate();

        return flyway.getDataSource();
    }

    private static Flyway getFlyway(String fileName) throws IOException {
        DatabaseProperties properties = new DatabaseProperties(fileName);

        Flyway flyway = Flyway.configure().dataSource(
                properties.getUrl(),
                properties.getUsername(),
                properties.getPassword()
        ).load();

        return flyway;
    }

    public static void resetDatabase(String fileName) throws IOException {
        System.out.println(fileName);
        Flyway flyway = getFlyway("filename" + fileName);
        flyway.clean();


    }
}
