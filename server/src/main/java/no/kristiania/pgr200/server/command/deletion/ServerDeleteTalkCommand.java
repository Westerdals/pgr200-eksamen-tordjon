package no.kristiania.pgr200.server.command.deletion;

import no.kristiania.pgr200.core.command.deletion.DeleteTalkCommand;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerDeleteTalkCommand extends DeleteTalkCommand implements ServerCommand {


    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TalkDao dao = new TalkDao(dataSource);
        dao.delete(id);
        assignStandardHttp(id);
        return response;
    }

}
