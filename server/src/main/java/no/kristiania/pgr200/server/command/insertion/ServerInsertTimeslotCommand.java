package no.kristiania.pgr200.server.command.insertion;

import no.kristiania.pgr200.core.command.insertion.InsertTimeslotCommand;
import no.kristiania.pgr200.core.model.Timeslot;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerInsertTimeslotCommand extends InsertTimeslotCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (start == null || end == null) {
            //System.out.println("\"-date\" required");
            response.setStatus(400);
            return response;
        }

        TimeslotDao dao = new TimeslotDao(dataSource);
        Timeslot timeslot = new Timeslot(start, end);

        dao.insert(timeslot);
        
        assignStandardHttp(timeslot);
        return response;
    }
}
