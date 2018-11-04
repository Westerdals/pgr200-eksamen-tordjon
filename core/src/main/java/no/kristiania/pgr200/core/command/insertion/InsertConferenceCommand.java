package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Conference;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class InsertConferenceCommand extends Command {

    private String name;

    private  InsertConferenceCommand withName(String name){
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
