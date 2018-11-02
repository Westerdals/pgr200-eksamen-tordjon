package no.kristiania.pgr200.program.command.deletion;

import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteDayCommand extends Command {

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
    public void execute(DataSource dataSource) throws SQLException {
        DayDao dayDao = new DayDao(dataSource);
        dayDao.delete(id);
    }
}
