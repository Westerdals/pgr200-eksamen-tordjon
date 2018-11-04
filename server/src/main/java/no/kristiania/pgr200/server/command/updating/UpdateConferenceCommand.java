package no.kristiania.pgr200.server.command.updating;


import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class UpdateConferenceCommand extends UpdatingCommand {

    private UUID id;
    private String name;

    private UpdateConferenceCommand withName(String name){
        this.name = name;
        return this;
    }

    private UpdateConferenceCommand withId(UUID id){
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));
        String name = parameters.get("name");
        return new UpdateConferenceCommand().withName(name).withId(id);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" required.");
            return null;
        }

        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(id, name);
        dao.update(conference);

        return null;
    }
}
