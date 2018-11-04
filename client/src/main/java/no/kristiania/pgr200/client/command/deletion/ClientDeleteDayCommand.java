package no.kristiania.pgr200.client.command.deletion;

import no.kristiania.pgr200.core.command.deletion.DeleteDayCommand;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.ServerCommand;
import no.kristiania.pgr200.server.database.dao.DayDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ServerDeleteDayCommand extends DeleteDayCommand implements ServerCommand {


    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        DayDao dayDao = new DayDao(dataSource);
        dayDao.delete(id);
        assignStandardHttp(id);
        return response;
    }
}
