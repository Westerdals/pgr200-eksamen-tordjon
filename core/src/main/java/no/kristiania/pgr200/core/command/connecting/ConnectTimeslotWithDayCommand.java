package no.kristiania.pgr200.core.command.connecting;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.UUID;

public abstract class ConnectTimeslotWithDayCommand extends Command {

    protected UUID timeslotId;
    protected UUID dayId;

    protected ConnectTimeslotWithDayCommand withTimeslotId(UUID timeslotId) {
        this.timeslotId = timeslotId;
        return this;
    }

    protected ConnectTimeslotWithDayCommand withDayId(UUID dayId) {
        this.dayId = dayId;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID timeslotId = getId(parameters.get("timeslot"));
        UUID dayId = getId(parameters.get("day"));

        return   this
                .withTimeslotId(timeslotId)
                .withDayId(dayId);
    }



}
