package no.kristiania.pgr200.core.command.listing;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;

public abstract class ListConferencesCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return this;
    }

}
