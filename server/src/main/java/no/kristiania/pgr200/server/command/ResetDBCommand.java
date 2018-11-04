package no.kristiania.pgr200.server.command;

import no.kristiania.pgr200.server.HttpServer;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ResetDBCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ResetDBCommand();
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        //FIXME:
        String filename = HttpServer.propertiesFileName;
        try {
            Util.resetDatabase(filename);
            response.setStatus(200);
        } catch (IOException e) {
            System.out.println("Could not reset no.kristiania.pgr200.server.database.");
            response.setStatus(500);
        }
        return response;
    }

    @Override
    public <T> void assignStandardHttp(T content) {
        throw new NotImplementedException();
    }

}
