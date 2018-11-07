package no.kristiania.pgr200.server.command.updating;

import no.kristiania.pgr200.core.command.updating.UpdateTalkCommand;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TalkDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerUpdateTalkCommand extends UpdateTalkCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            //System.out.println("\"-id\" is required.");
            response.setStatus(400);
            return response;
        }

        TalkDao dao = new TalkDao(dataSource);

        Talk original = dao.retrieve(id);
        if(original == null){
            assignStandardHttp("You cannot update a talk that does not exist.");
            response.setStatus(400);
            return response;
        }
        Talk updated = new Talk(
                id == null ? original.getId() : id,
                title == null ? original.getTitle(): title,
                description == null ? original.getDescription() : description,
                topic == null ? original.getTopicTitle() : topic
        );

        dao.update(updated);

        Talk afterUpdate = dao.retrieve(updated.getId());

        assignStandardHttp(afterUpdate);
        return response;
    }
}
