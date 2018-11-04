package no.kristiania.pgr200.server.command.deletion;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteTalkCommand extends DeletionCommand {

    private UUID id;

    private Command withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        UUID id = getId(parameters.get("id"));

        return new DeleteTalkCommand()
                .withId(id);
    }


    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        TalkDao dao = new TalkDao(dataSource);
        dao.delete(id);
        return null;
    }

}
