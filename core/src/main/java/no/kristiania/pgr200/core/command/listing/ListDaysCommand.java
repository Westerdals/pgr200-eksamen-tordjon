package no.kristiania.pgr200.core.command.listing;


import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Day;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class ListDaysCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return this;
    }

}
