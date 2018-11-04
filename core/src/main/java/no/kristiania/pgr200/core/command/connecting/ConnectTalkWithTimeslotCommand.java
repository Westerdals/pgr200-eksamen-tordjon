package no.kristiania.pgr200.core.command.connecting;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.UUID;

public abstract class ConnectTalkWithTimeslotCommand extends Command {

    protected UUID talkId;
    protected UUID timeslotId;

    protected ConnectTalkWithTimeslotCommand withTalkId(UUID talkId) {
        this.talkId = talkId;
        return this;
    }

    protected ConnectTalkWithTimeslotCommand withTimeslotId(UUID timeslotId) {
        this.timeslotId = timeslotId;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID talkId = getId(parameters.get("talk"));
        UUID timeslotId = getId(parameters.get("timeslot"));

        return  this
                .withTalkId(talkId)
                .withTimeslotId(timeslotId);
    }



}
