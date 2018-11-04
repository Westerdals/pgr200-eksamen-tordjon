package no.kristiania.pgr200.server.command.updating;

import no.kristiania.pgr200.core.command.updating.UpdateDayCommand;
import no.kristiania.pgr200.core.model.Day;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerUpdateDayCommand extends UpdateDayCommand implements ServerCommand {

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {

        if (id == null) {
            System.out.println("\"-id\" required.");
            return null;
        }

        DayDao dao = new DayDao(dataSource);

        Day original = dao.retrieve(id);
        Day updated = new Day(
                original.getId(),
                date == null ? original.getDate() : date
        );
        dao.update(updated);

        Day afterUpdate = dao.retrieve(updated.getId());

        assignStandardHttp(afterUpdate);

        return response;
    }
}
