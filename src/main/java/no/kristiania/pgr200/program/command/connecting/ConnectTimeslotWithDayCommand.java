package no.kristiania.pgr200.program.command.connecting;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ConnectTimeslotWithDayCommand extends Command {

    UUID timeslotId;
    UUID dayId;

    public ConnectTimeslotWithDayCommand withTimeslotId(UUID timeslotId) {
        this.timeslotId = timeslotId;
        return this;
    }

    public ConnectTimeslotWithDayCommand withDayId(UUID dayId) {
        this.dayId = dayId;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        String timeslotFromInput = getArgument("-timeslot", parameters, null);
        String dayFromInput = getArgument("-day", parameters, null);

        timeslotId = timeslotFromInput != null ? UUID.fromString(timeslotFromInput) : null;
        dayId = dayFromInput != null ? UUID.fromString(dayFromInput) : null;

        return new ConnectTimeslotWithDayCommand()
                .withTimeslotId(timeslotId)
                .withDayId(dayId);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        if (timeslotId == null || dayId == null) {
            System.out.println("both \"-timeslot\" and \"-day\" is required.");
            return;
        }

        TimeslotDao dao = new TimeslotDao(dataSource);
        dao.connectTimeslotToDay(timeslotId, dayId);
    }

}
