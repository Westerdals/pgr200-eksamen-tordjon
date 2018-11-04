package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Day;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public abstract class InsertDayCommand extends Command {

    protected LocalDate date;
    // timeslots are added later by user

    protected InsertDayCommand withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        LocalDate date = getDate(parameters.get("date"));
        
        return this
                .withDate(date);
    }

}
