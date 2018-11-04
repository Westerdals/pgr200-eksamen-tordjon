package no.kristiania.pgr200.client.command;


import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.ShowScheduleCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;


public class ClientShowScheduleCommand extends ShowScheduleCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }


}
