package no.kristiania.pgr200.core.command.connecting;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.UUID;

public abstract class ConnectDayWithConference extends Command {

    protected UUID conferenceId;
    protected UUID dayId;

    protected ConnectDayWithConference withConferenceId(UUID conferenceId) {
        this.conferenceId = conferenceId;
        return this;
    }

    protected ConnectDayWithConference withDayId(UUID dayId) {
        this.dayId = dayId;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        conferenceId = getId(parameters.get("conference"));
        dayId = getId(parameters.get("day"));

        return this
                .withConferenceId(conferenceId)
                .withDayId(dayId);
    }


}
