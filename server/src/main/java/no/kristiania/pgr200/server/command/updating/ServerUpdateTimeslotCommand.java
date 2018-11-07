package no.kristiania.pgr200.server.command.updating;

import no.kristiania.pgr200.core.command.updating.UpdateTimeslotCommand;
import no.kristiania.pgr200.core.model.Timeslot;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerUpdateTimeslotCommand extends UpdateTimeslotCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        if (id == null) {
            //System.out.println("\"-id\" is required.");
            response.setStatus(400);
            return response;
        }

        TimeslotDao dao = new TimeslotDao(dataSource);
        Timeslot original = dao.retrieve(id);
        if(original == null){
            assignStandardHttp("You cannot update a timeslot that does not exist.");
            response.setStatus(400);
            return response;
        }
        Timeslot updated = new Timeslot(
                id,
                start == null ? original.getStart() : start,
                end == null ? original.getEnd() : end
        );

        dao.update(updated);

        Timeslot afterUpdate = dao.retrieve(updated.getId());
        assignStandardHttp(afterUpdate);
        return response;
    }
}
