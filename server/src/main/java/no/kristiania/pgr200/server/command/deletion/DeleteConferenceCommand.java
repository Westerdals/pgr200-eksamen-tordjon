package no.kristiania.pgr200.server.command.deletion;

import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.model.Conference;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteConferenceCommand extends Command {

    private UUID id;

    private DeleteConferenceCommand withId(UUID id){
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));
        return new DeleteConferenceCommand()
                .withId(id);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        dao.delete(id);
    }

}
