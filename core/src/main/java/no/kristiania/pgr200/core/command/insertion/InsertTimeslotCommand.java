package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

public abstract class InsertTimeslotCommand extends Command {


    private LocalTime start;
    private LocalTime end;
    // talk manuelt av bruker senere

    private InsertTimeslotCommand withStart(LocalTime start) {
        this.start = start;
        return this;
    }

    private InsertTimeslotCommand withEnd(LocalTime end) {
        this.end = end;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        LocalTime start = getTime(parameters.get("start"));
        LocalTime end = getTime(parameters.get("end"));

        return this
                .withStart(start)
                .withEnd(end);
    }


}
