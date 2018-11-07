package no.kristiania.pgr200.server.database;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;

public class Util {

    /**
     * Creates and returns the datasource from a database
     * @param fileName properties-file with database-config
     * @throws IOException
     */
    public static DataSource createDataSource(String fileName) throws IOException {

        Flyway flyway = getFlyway(fileName);

        // flyway.baseline();
        // flyway.repair();
        flyway.migrate();

        return flyway.getConfiguration().getDataSource();
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

    /**
     * Resets the entire database
     * @param fileName properties-file with database-config
     * @throws IOException
     */
    public static void resetDatabase(String fileName) throws IOException {
        Flyway flyway = getFlyway(fileName);
        flyway.clean();
        getFlyway(fileName).migrate();


    }
}
