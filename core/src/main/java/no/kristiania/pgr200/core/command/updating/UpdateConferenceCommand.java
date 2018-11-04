package no.kristiania.pgr200.core.command.updating;


import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.UUID;

public abstract class UpdateConferenceCommand extends Command {

    protected UUID id;
    protected String name;

    protected UpdateConferenceCommand withName(String name){
        this.name = name;
        return this;
    }

    protected UpdateConferenceCommand withId(UUID id){
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));
        String name = parameters.get("name");
        return this
                .withName(name).withId(id);
    }


}
