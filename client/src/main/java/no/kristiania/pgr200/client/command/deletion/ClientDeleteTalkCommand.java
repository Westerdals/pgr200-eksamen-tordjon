package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.client.HttpResponse;
import no.kristiania.pgr200.core.command.deletion.DeleteTalkCommand;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TalkDao;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerDeleteTalkCommand extends DeleteTalkCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws SQLException {
       throw new NotImplementedException();
    }

}