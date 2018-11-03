package no.kristiania.pgr200.server.command.listing;

import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.Dao;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ListTimeslotsCommand extends Command {
    public Command build(HashMap<String, String> parameters) {
        return new ListTimeslotsCommand();
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Timeslot> dao = new TimeslotDao(dataSource);
        List<Timeslot> timeslots = dao.retrieveAll();

        response.setBody(gson.toJson(timeslots));
        response.setStatus(200);

        return response; 
    }
}
