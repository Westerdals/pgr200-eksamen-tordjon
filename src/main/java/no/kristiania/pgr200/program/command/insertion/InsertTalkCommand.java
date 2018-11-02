package no.kristiania.pgr200.program.command.insertion;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.dao.TalkDao;
import no.kristiania.pgr200.database.model.Talk;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;

public class InsertTalkCommand extends Command {

    private String title;
    private String description;
    private String topic;


    private InsertTalkCommand withTitle(String title) {
        this.title = title;
        return this;
    }

    private InsertTalkCommand withDescription(String description) {
        this.description = description;
        return this;
    }

    private InsertTalkCommand withTopic(String topic) {
        this.topic = topic;
        return this;
    }


    @Override
    public InsertTalkCommand build(String[] strings) throws IllegalArgumentException {
        String title = getArgument("-title", strings, "unknown");
        String description = getArgument("-description", strings, "unknown");
        String topic = getArgument("-topic", strings, "unknown");

        return new InsertTalkCommand()
                .withTitle(title)
                .withDescription(description)
                .withTopic(topic);
    }


    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Talk> dao = new TalkDao(dataSource);
        Talk talk = new Talk(title, description, topic);

        dao.insert(talk);
    }
}