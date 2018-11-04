package no.kristiania.pgr200.core.command.updating;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.UUID;

public abstract class UpdateTalkCommand extends Command {

    UUID id;
    String title;
    String description;
    String topicTitle;

    public UpdateTalkCommand withId(UUID id) {
        this.id = id;
        return this;
    }

    public UpdateTalkCommand withTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateTalkCommand withDescription(String description) {
        this.description = description;
        return this;
    }

    public UpdateTalkCommand withTopicTitle(String topic) {
        this.topicTitle = topic;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {


        UUID id = getId(parameters.get("id"));
        String title = parameters.get("title");
        String description = parameters.get("description");
        String topicTitle = parameters.get("topic");

        return this
                .withId(id)
                .withTitle(title)
                .withDescription(description)
                .withTopicTitle(topicTitle);
    }

}
