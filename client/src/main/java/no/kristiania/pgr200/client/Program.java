package no.kristiania.pgr200.client;




import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class Program {


    static DataSource dataSource;
    static String propertiesFileName = "innlevering.properties";

    public static void main(String[] args) {





            // User input is passed on

        //path = args[0] + " " + [1];
        //parameters = ArgumentParser.parse(args); <- returnerer et map med f.eks. "title", "toalettguiden"

           // Command command = Command.createCommand(path, parameters);

            //command.execute(dataSource);


    }

    public static void setPropertiesFilename(String fn) throws IOException {
        propertiesFileName = fn;
    }

    public static String getPropertiesFilename() {
        return propertiesFileName;
    }



}