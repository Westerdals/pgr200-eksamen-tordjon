package no.kristiania.pgr200.server.command.deletion;

import no.kristiania.pgr200.server.command.Command;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class DeletionCommand extends Command {
    @Override
    public <T> void assignStandardHttp(T content) {
        throw new NotImplementedException();
    }
}
