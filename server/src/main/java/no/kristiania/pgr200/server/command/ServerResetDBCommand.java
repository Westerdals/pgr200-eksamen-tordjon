package no.kristiania.pgr200.server.command;

import no.kristiania.pgr200.core.command.ResetDBCommand;
import no.kristiania.pgr200.server.HttpServer;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ServerResetDBCommand extends ResetDBCommand implements ServerCommand {
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
        //response.setBody("");

        return response;
    }

}
