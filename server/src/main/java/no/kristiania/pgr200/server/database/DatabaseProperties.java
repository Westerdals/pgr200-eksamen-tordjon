package no.kristiania.pgr200.server.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


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

        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
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

    private String getPath(String fileName) {
        return this.getClass().getClassLoader().getResource(fileName)
                .toString()
                .replaceFirst("file:", "");
    }
}
