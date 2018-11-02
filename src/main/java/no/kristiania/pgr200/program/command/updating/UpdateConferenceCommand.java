package no.kristiania.pgr200.program.command.updating;

import no.kristiania.pgr200.database.dao.ConferenceDao;
import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.model.Conference;
import no.kristiania.pgr200.database.model.Day;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UpdateConferenceCommand extends Command {

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
    public Command build(String[] strings) throws IllegalArgumentException {
        String idFromInput = getArgument("-id", strings, null);

        UUID id = idFromInput != null ? UUID.fromString(idFromInput) : null;
        String name = getArgument("-name", strings, "unkown");
        return new UpdateConferenceCommand().withName(name).withId(id);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" required.");
            return;
        }

        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(id, name);
        dao.update(conference);

    }
}
