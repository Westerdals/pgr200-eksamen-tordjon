package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.insertion.InsertTalkCommand;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ClientInsertTalkCommand extends InsertTalkCommand {

    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
        throw new NotImplementedException();
    }
}