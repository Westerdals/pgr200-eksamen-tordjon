package no.kristiania.pgr200.program.command.deletion;
import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteTimeslotCommand extends Command {

    private UUID id;

    private DeleteTimeslotCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(String[] strings) throws IllegalArgumentException {
        UUID id = UUID.fromString(getArgument("-id", strings, null));

        return new DeleteTimeslotCommand()
                .withId(id);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        TimeslotDao dao = new TimeslotDao(dataSource);
        dao.delete(id);
    }
}
