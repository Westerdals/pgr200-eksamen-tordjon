package no.kristiania.pgr200.core.command.updating;

import no.kristiania.pgr200.core.command.Command;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.UUID;

public abstract class UpdateTimeslotCommand extends Command {

    protected UUID id;
    protected LocalTime start;
    protected LocalTime end;

    protected UpdateTimeslotCommand withId(UUID id) {

        this.id = id != null ? id : null;
        return this;
    }

    protected UpdateTimeslotCommand withStart(LocalTime start) {
        this.start = start != null ? start : null;
        return this;
    }

    protected UpdateTimeslotCommand withEnd(LocalTime end) {
        this.end = end;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        UUID id = getId(parameters.get("id"));

        LocalTime start = getTime(parameters.get("start"));
        LocalTime end = getTime(parameters.get("end"));

        return this
                .withId(id)
                .withStart(start)
                .withEnd(end);
    }


}
