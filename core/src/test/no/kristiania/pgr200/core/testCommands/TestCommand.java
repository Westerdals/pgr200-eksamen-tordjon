package no.kristiania.pgr200.core.testCommands;

import no.kristiania.pgr200.core.command.Command;

import javax.sql.DataSource;
import java.util.HashMap;


/**
 * Commands used in inputparsertest.
 * They cannot be inner classes in the test, as that
 * would throw an unwanted and irrelevant (to the test) exception.
 */
public abstract class TestCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return this;
    }

    @Override
    public <T, E extends Exception> T execute(DataSource dataSource) throws E {
        return null;
    }
}
