package no.kristiania.pgr200.program;

import no.kristiania.pgr200.database.Util;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

public class Program {


    static DataSource dataSource;
    static String propertiesFileName = "innlevering.properties";

    public static void main(String[] args) {

        try {

            dataSource = Util.createDataSource(propertiesFileName);

            // User input is passed on
            Command command = Command.createCommand(args);

            command.execute(dataSource);

        } catch (IOException e) {
            System.out.println("Something went wrong when configuring datasource");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Program: Something is wrong when accessing db.."); 
        }
    }

    public static void setPropertiesFilename(String fn) throws IOException {
        propertiesFileName = fn;
    }

    public static String getPropertiesFilename() {
        return propertiesFileName;
    }



}
