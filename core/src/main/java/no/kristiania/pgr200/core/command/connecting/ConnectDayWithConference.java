package no.kristiania.pgr200.server.command.connecting;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class ConnectDayWithConference extends ConnectingCommand {

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
        conferenceId = getId(parameters.get("conference"));
        dayId = getId(parameters.get("day"));

        return new ConnectDayWithConference()
                .withConferenceId(conferenceId)
                .withDayId(dayId);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        DayDao dao = new DayDao(dataSource);
        dao.connectDayToConference(conferenceId, dayId);

        assignStandardHttp("");  // no content is sent back

        return response;
    }
}
