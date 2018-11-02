package no.kristiania.pgr200.program.command.deletion;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import no.kristiania.pgr200.database.dao.ConferenceDao;
import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.model.Conference;
import no.kristiania.pgr200.program.command.Command;

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
        UUID id = UUID.fromString(getArgument("-id", parameters, "0"));
        return new DeleteConferenceCommand()
                .withId(id);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        dao.delete(id);
    }
}
