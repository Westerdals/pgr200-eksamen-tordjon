package no.kristiania.pgr200.server.command.insertion;

import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import no.kristiania.pgr200.server.database.model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.HashMap;

public class InsertTimeslotCommand extends Command {


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

        return new InsertTimeslotCommand()
                .withStart(start)
                .withEnd(end);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        if (start == null || end == null) {
            System.out.println("Both \"-start\" and \"-end\" are required.");
            return;
        }

        TimeslotDao dao = new TimeslotDao(dataSource);
        Timeslot timeslot = new Timeslot(start, end);

        dao.insert(timeslot);
    }
}