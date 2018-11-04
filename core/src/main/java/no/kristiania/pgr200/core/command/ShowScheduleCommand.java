package no.kristiania.pgr200.core.command;


import java.util.HashMap;
import java.util.UUID;


public abstract class ShowScheduleCommand extends Command {
    private UUID id;

    private Command withId(UUID id) {
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
