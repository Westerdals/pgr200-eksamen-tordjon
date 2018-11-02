package no.kristiania.pgr200.program.command.deletion;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import no.kristiania.pgr200.database.dao.TalkDao;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class DeleteTalkCommand extends Command {

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
    public void execute(DataSource dataSource) throws SQLException {
        TalkDao dao = new TalkDao(dataSource);
        dao.delete(id);
    }

}
