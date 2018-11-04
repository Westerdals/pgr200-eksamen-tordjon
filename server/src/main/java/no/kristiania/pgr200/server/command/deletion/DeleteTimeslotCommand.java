package no.kristiania.pgr200.core.command.deletion;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteTimeslotCommand extends DeletionCommand {

    private UUID id;

    private DeleteTimeslotCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));

        return new DeleteTimeslotCommand()
                .withId(id);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TimeslotDao dao = new TimeslotDao(dataSource);
        dao.delete(id);
        assignStandardHttp(id);
        return response;
    }
}
