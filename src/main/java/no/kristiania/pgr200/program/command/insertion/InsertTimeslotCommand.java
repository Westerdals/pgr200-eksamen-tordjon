package no.kristiania.pgr200.program.command.insertion;

import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.model.Timeslot;
import no.kristiania.pgr200.program.command.Command;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    public Command build(String[] strings) throws IllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        String s = getArgument("-start", strings, null);
        start = s != null ? LocalTime.parse(s, formatter) : null;

        String e = getArgument("-end", strings, null);
        end = s != null ? LocalTime.parse(e, formatter) : null;

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
