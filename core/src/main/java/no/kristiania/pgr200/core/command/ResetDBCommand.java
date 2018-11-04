package no.kristiania.pgr200.core.command;

import java.util.HashMap;

public abstract class ResetDBCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return this;
    }



}
