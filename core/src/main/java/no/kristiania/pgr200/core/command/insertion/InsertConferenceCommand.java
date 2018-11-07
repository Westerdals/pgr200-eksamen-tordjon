package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;

public abstract class InsertConferenceCommand extends Command {

    protected String name;

    protected  InsertConferenceCommand withName(String name){
        this.name = name;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        String name = parameters.get("name");
        return this
                .withName(name);
    }

}
