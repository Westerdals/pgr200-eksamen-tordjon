package no.kristiania.pgr200.server.command.updating;

import no.kristiania.pgr200.server.command.Command;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public abstract class UpdatingCommand extends Command {
    @Override
    public <T> void assignStandardHttp(T content) {
        throw new NotImplementedException(); 
    }
}
