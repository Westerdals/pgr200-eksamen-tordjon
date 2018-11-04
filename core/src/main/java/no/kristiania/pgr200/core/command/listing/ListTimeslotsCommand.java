package no.kristiania.pgr200.core.command.listing;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;

public abstract class ListTimeslotsCommand extends Command {
    public Command build(HashMap<String, String> parameters) {
        return this;
    }


}
