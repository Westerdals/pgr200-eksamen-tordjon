package no.kristiania.pgr200.server.command.updating;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.DayDao;
import model.Day;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class UpdateDayCommand extends Command {

    UUID id;
    LocalDate date;

    private UpdateDayCommand withDate(LocalDate date) {
        this.date = date;
        return this;
    }
    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        UUID id = getId(parameters.get("id"));
        LocalDate date = getDate(parameters.get("date"));

        return new UpdateDayCommand()
                .withDate(date)
                .withId(id);

    }

    private UpdateDayCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" required.");
            return null;
        }

        DayDao dao = new DayDao(dataSource);

        Day original = dao.retrieve(id);
        Day updated = new Day(
                original.getId(),
                date == null ? original.getDate() : date
        );

        dao.update(updated);
        return null;
    }
}
