package no.kristiania.pgr200.server.command.insertion;

import no.kristiania.pgr200.server.command.Command;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class InsertionCommand extends Command {
    @Override
    public <T> void assignStandardHttp(T content) {
        throw new NotImplementedException();
    }
}
