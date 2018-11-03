package no.kristiania.pgr200.server.database.dao;

import model.Conference;
import model.Day;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ConferenceDao extends Dao<Conference> {

    public ConferenceDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void insert(Conference conference) throws SQLException {
        String sql = "insert into conference (id, name) values (?, ?)";
        executeSQL(sql, conference.getId(), conference.getName());
    }

    @Override
    public Conference retrieve(Object id) throws SQLException {
        String sql = "select id, name from conference where id = ?";
        List<Conference> results = executeSQL(sql, this::mapResultSet, id);

        return results.size() != 0 ? results.get(0) : null;
    }

    @Override
    public List<Conference> retrieveAll() throws SQLException {
        String sql = "select id, name from conference";
        return executeSQL(sql, this::mapResultSet);
    }

    @Override
    public void update(Conference updated) throws SQLException {
        String sql = "update conference set name = ? where id = ?";

        executeSQL(
                sql,
                updated.getName(),
                updated.getId()
        );
    }

    @Override
    public void delete(Object id) throws SQLException {

        String sql = "delete from conference_day where conference_id = ?";
        executeSQL(sql, id);

        sql = "delete from conference where id = ?";
        executeSQL(sql, id);
    }

    @Override
    protected Conference mapResultSet(ResultSet resultSet) throws SQLException {

        DayDao dayDao = new DayDao(dataSource);

        UUID conferenceId = (UUID) resultSet.getObject(1);
        // går inn i koblingstabell:
        List<Day> days = dayDao.retrieveByConference(conferenceId);

        return new Conference(
                conferenceId,
                resultSet.getString(2),
                days
        );
    }
}
