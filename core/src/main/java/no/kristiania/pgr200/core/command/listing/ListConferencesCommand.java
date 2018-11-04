package no.kristiania.pgr200.core.command.listing;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Conference;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class ListConferencesCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return this;
    }

}
