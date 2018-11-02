package no.kristiania.pgr200.program.command.connecting;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ConnectDayWithConference extends Command {

    private UUID conferenceId;
    private UUID dayId;

    private ConnectDayWithConference withConferenceId(UUID conferenceId) {
        this.conferenceId = conferenceId;
        return this;
    }

    private ConnectDayWithConference withDayId(UUID dayId) {
        this.dayId = dayId;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        conferenceId = UUID.fromString(getArgument("-conference", parameters, null));
        dayId = UUID.fromString(getArgument("-day", parameters, null));

        return new ConnectDayWithConference()
                .withConferenceId(conferenceId)
                .withDayId(dayId);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        DayDao dao = new DayDao(dataSource);
        dao.connectDayToConference(conferenceId, dayId);
    }
}
