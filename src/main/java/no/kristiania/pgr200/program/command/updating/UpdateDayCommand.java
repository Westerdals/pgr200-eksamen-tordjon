package no.kristiania.pgr200.program.command.updating;

import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.database.model.Day;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UpdateDayCommand extends Command {

    UUID id;
    LocalDate date;

    private UpdateDayCommand withDate(LocalDate date) {
        this.date = date;
        return this;
    }
    @Override
    public Command build(String[] strings) throws IllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

        String idFromInput = getArgument("-id", strings, null);
        String dateFromInput = getArgument("-date", strings, null);

        UUID id = idFromInput != null ? UUID.fromString(idFromInput) : null;
        date = dateFromInput != null ? LocalDate.parse(dateFromInput, formatter) : null;

        return new UpdateDayCommand()
                .withDate(date)
                .withId(id);

    }

    private UpdateDayCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" required.");
            return;
        }

        DayDao dao = new DayDao(dataSource);

        Day original = dao.retrieve(id);
        Day updated = new Day(
                original.getId(),
                date == null ? original.getDate() : date
        );

        dao.update(updated);
    }
}
