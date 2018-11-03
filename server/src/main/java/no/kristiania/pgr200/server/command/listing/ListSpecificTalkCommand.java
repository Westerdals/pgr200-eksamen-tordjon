package no.kristiania.pgr200.server.command.listing;

import com.google.gson.Gson;
import com.sun.deploy.net.HttpResponse;
import model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ListSpecificTalkCommand extends Command {

    private UUID id;


    private ListSpecificTalkCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        UUID id = getId(parameters.get("id"));

        return new ListSpecificTalkCommand()
                .withId(id);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TalkDao dao =  new TalkDao(dataSource);
        Talk talk = dao.retrieve(id);

        response.setBody(gson.toJson(talk));
        response.setStatus(200);

        return response;
    }
}
