package no.kristiania.pgr200.server.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents a properties-file with the
 * following values:
 * -url
 * -username
 * -password
 */
public class DatabaseProperties {

    private Properties properties;

    private String url;
    private String username;
    private String password;

    public DatabaseProperties(String fileName) throws IOException {

        File file = new File(fileName);

        properties = new Properties();

        FileInputStream in = new FileInputStream(file);
        properties.load(in);
        in.close();

        url = properties.getProperty("dataSource.url");
        username = properties.getProperty("dataSource.username");
        password = properties.getProperty("dataSource.password");
    }



    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
