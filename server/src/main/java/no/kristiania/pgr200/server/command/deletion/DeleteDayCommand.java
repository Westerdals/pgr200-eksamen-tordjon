package no.kristiania.pgr200.core.command.deletion;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteDayCommand extends DeletionCommand {

    UUID id;

    private DeleteDayCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        UUID id = getId(parameters.get("id"));

        return new DeleteDayCommand()
                 .withId(id);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        DayDao dayDao = new DayDao(dataSource);
        dayDao.delete(id);
        assignStandardHttp(id);
        return response;
    }
}
