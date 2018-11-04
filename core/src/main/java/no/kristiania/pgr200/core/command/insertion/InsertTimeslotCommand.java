package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

public abstract class InsertTimeslotCommand extends Command {


    protected LocalTime start;
    protected LocalTime end;
    // talk manuelt av bruker senere

    protected InsertTimeslotCommand withStart(LocalTime start) {
        this.start = start;
        return this;
    }

    protected InsertTimeslotCommand withEnd(LocalTime end) {
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
