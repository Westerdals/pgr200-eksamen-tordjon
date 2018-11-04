package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Talk;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class InsertTalkCommand extends Command {

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
    public InsertTalkCommand build(HashMap<String, String> parameters) throws IllegalArgumentException {
        String title = parameters.get("title");
        String description = parameters.get("description");
        String topic = parameters.get("topic");


        return this
                .withTitle(title)
                .withDescription(description)
                .withTopic(topic);
    }




}