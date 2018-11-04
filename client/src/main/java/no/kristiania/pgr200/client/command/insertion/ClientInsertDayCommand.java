package no.kristiania.pgr200.client.command.insertion;

import no.kristiania.pgr200.core.command.insertion.InsertDayCommand;
import no.kristiania.pgr200.core.model.Day;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerInsertDayCommand extends InsertDayCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (date == null) {
            //System.out.println("\"-date\" required");
            response.setStatus(400);
            return response;
        }

        DayDao dao = new DayDao(dataSource);
        Day day = new Day(date);
        dao.insert(day);

        assignStandardHttp(day);
        return response;
    }
}
