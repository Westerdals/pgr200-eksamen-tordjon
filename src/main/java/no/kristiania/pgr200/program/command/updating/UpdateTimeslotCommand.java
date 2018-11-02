package no.kristiania.pgr200.program.command.updating;

import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.model.Timeslot;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

public class UpdateTimeslotCommand extends Command {

    private UUID id;
    private LocalTime start;
    private LocalTime end;

    private UpdateTimeslotCommand withId(UUID id) {

        this.id = id != null ? id : null;
        return this;
    }

    private UpdateTimeslotCommand withStart(LocalTime start) {
        this.start = start != null ? start : null;
        return this;
    }

    private UpdateTimeslotCommand withEnd(LocalTime end) {
        this.end = end;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        String idFromInput = getArgument("-id", parameters, null);
        //String idFromInput = parameters.get("id");

        id = idFromInput != null ? UUID.fromString(getArgument("-id", parameters, null)) : null;

        String s = getArgument("-start", parameters, null);
        if(s != null)
            start = LocalTime.parse(s, formatter);

        String e = getArgument("-end", parameters, null);
        if(e != null)
            end = LocalTime.parse(e, formatter);

        return new UpdateTimeslotCommand()
                .withId(id)
                .withStart(start)
                .withEnd(end);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" is required.");
            return;
        }


        TimeslotDao dao = new TimeslotDao(dataSource);

        Timeslot original = dao.retrieve(id);
        Timeslot updated = new Timeslot(
                id,
                start == null ? original.getStart() : start,
                end == null ? original.getEnd() : end
        );

        dao.update(updated);
    }
}
