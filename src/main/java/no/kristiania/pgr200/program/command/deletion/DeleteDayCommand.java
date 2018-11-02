package no.kristiania.pgr200.program.command.deletion;

import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteDayCommand extends Command {

    UUID id;

    private DeleteDayCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(String[] strings) throws IllegalArgumentException {

        id = UUID.fromString(getArgument("-id", strings, null));
         return new DeleteDayCommand()
                 .withId(id);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        DayDao dayDao = new DayDao(dataSource);
        dayDao.delete(id);
    }
}
