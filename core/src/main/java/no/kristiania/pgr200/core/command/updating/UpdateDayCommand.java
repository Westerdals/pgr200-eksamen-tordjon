package no.kristiania.pgr200.core.command.updating;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Day;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public abstract class UpdateDayCommand extends Command {

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

        return this
                .withDate(date)
                .withId(id);

    }

    private UpdateDayCommand withId(UUID id) {
        this.id = id;
        return this;
    }


}
