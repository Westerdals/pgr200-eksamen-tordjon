package no.kristiania.pgr200.program.command.deletion;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import no.kristiania.pgr200.database.dao.TalkDao;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

public class DeleteTalkCommand extends Command {

    private UUID id;

    private Command withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public Command build(String[] strings) throws IllegalArgumentException {

        String id = getArgument("-id", strings, null);

        return new DeleteTalkCommand()
                .withId(UUID.fromString(id));
    }


    @Override
    public void execute(DataSource dataSource) throws SQLException {
        TalkDao dao = new TalkDao(dataSource);
        dao.delete(id);
    }

}
