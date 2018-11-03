package no.kristiania.pgr200.server.command.updating;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalTime;
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

        UUID id = getId(parameters.get("id"));

        LocalTime start = getTime(parameters.get("start"));
        LocalTime end = getTime(parameters.get("end"));

        return new UpdateTimeslotCommand()
                .withId(id)
                .withStart(start)
                .withEnd(end);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" is required.");
            return null;
        }


        TimeslotDao dao = new TimeslotDao(dataSource);

        Timeslot original = dao.retrieve(id);
        Timeslot updated = new Timeslot(
                id,
                start == null ? original.getStart() : start,
                end == null ? original.getEnd() : end
        );

        dao.update(updated);
        return null;
    }
}
