package no.kristiania.pgr200.server.command.insertion;

import model.Day;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class InsertDayCommand extends InsertionCommand {

    LocalDate date;
    // timeslots are added later by user

    private InsertDayCommand withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {

        LocalDate date = getDate(parameters.get("date"));
        
        return new InsertDayCommand()
                .withDate(date);
    }

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
