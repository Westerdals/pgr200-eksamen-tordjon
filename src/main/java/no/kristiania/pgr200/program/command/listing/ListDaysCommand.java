package no.kristiania.pgr200.program.command.listing;


import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.dao.DayDao;
import no.kristiania.pgr200.database.model.Day;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class ListDaysCommand extends Command {

    @Override
    public Command build(String[] strings) throws IllegalArgumentException {
        return new ListDaysCommand();
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Day> dao = new DayDao(dataSource);

        List<Day> days = dao.retrieveAll();

        for(Day day : days){
            System.out.println(day);
        }
    }
}
