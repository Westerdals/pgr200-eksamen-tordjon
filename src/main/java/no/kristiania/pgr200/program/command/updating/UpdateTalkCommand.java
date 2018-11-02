package no.kristiania.pgr200.program.command.updating;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;
import no.kristiania.pgr200.database.dao.TalkDao;
import no.kristiania.pgr200.database.model.Talk;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

public class UpdateTalkCommand extends Command {

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
    public Command build(String[] strings) throws IllegalArgumentException {

        String idFromInput = getArgument("-id", strings, null);

        id = idFromInput != null ? UUID.fromString(getArgument("-id", strings, null)) : null;
        title = getArgument("-title", strings, null);
        description = getArgument("-description", strings, null);
        topicTitle = getArgument("-topic", strings, null);

        return new UpdateTalkCommand()
                .withId(id)
                .withTitle(title)
                .withDescription(description)
                .withTopicTitle(topicTitle);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" is required.");
            return;
        }

        TalkDao dao = new TalkDao(dataSource);

        Talk original = dao.retrieve(id);
        Talk updated = new Talk(
                id == null ? original.getId() : id,
                title == null ? original.getTitle(): title,
                description == null ? original.getDescription() : description,
                topicTitle == null ? original.getTopicTitle() : topicTitle
        );

        dao.update(updated);
    }
}
