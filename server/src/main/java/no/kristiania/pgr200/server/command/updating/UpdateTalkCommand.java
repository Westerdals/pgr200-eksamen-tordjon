package no.kristiania.pgr200.server.command.updating;

import model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class UpdateTalkCommand extends UpdatingCommand {

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

        return new UpdateTalkCommand()
                .withId(id)
                .withTitle(title)
                .withDescription(description)
                .withTopicTitle(topicTitle);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" is required.");
            return null;
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

        Talk afterUpdate = dao.retrieve(updated.getId());

        assignStandardHttp(afterUpdate);
        return response;
    }
}
