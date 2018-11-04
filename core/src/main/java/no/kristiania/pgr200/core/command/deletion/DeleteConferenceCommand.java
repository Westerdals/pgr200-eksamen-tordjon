package no.kristiania.pgr200.core.command.deletion;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.UUID;

public abstract class DeleteConferenceCommand extends Command {

    protected UUID id;

    protected DeleteConferenceCommand withId(UUID id){
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));
        return this
                .withId(id);
    }
}
