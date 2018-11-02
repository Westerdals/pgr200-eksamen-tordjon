package no.kristiania.pgr200.program.command.listing;

import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.dao.TimeslotDao;
import no.kristiania.pgr200.database.model.Timeslot;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ListTimeslotsCommand extends Command {
    public Command build(String[] strings) {
        return new ListTimeslotsCommand();
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Timeslot> dao = new TimeslotDao(dataSource);
        List<Timeslot> timeslots = dao.retrieveAll();
        for(Timeslot timeslot : timeslots){
            System.out.println(timeslot);
        }
    }
}
