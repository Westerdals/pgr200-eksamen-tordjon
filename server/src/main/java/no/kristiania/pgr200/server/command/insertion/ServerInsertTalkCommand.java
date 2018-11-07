package no.kristiania.pgr200.server.command.insertion;

import no.kristiania.pgr200.core.command.insertion.InsertTalkCommand;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public class ServerInsertTalkCommand extends InsertTalkCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        if (title == null) {
            response.setStatus(400);
            return response;
        }

        Dao<Talk> dao = new TalkDao(dataSource);
        Talk talk = new Talk(title, description, topic);

        dao.insert(talk);


        assignStandardHttp(talk);
        return response;
    }
}