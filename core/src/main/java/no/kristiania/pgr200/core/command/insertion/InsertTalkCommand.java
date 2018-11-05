package no.kristiania.pgr200.core.command.insertion;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.model.Talk;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class InsertTalkCommand extends Command {

    protected String title;
    protected String description;
    protected String topic;



    protected InsertTalkCommand withTitle(String title) {
        this.title = title;
        return this;
    }

    protected InsertTalkCommand withDescription(String description) {
        this.description = description;
        return this;
    }

    protected InsertTalkCommand withTopic(String topic) {
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