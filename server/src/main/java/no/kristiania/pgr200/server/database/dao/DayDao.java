package no.kristiania.pgr200.server.database.dao;

import no.kristiania.pgr200.server.database.model.Day;
import no.kristiania.pgr200.server.database.model.Timeslot;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class DayDao extends Dao<Day> {

    public DayDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void insert(Day day) throws SQLException {
        String sql = "insert into day (id, date) values (?, ?)";

        executeSQL(
                sql,
                day.getId(),
                day.getDate()
        );
    }

    //TODO: burde koblingstabell ha egen dao? Consider.
    public void connectDayToConference(UUID conferenceId, UUID dayId) throws SQLException {
        String sql = "insert into conference_day (conference_id, day_id) values (?, ?)";

        executeSQL(sql, conferenceId, dayId);
    }

    @Override
    public Day retrieve(Object id) throws SQLException {
        String sql = "select id, date from day where id = ?";

        List<Day> days = executeSQL(sql, this::mapResultSet, id);

        return days.size() != 0 ? days.get(0) : null;
    }

    @Override
    public List<Day> retrieveAll() throws SQLException {
        String sql = "select id, date from day";
        return executeSQL(sql, this::mapResultSet);
    }


    public List<Day> retrieveByConference(UUID conferenceId) throws SQLException {

        String sql = "" +
                "select id, date " +
                "from day inner join conference_day " +
                "on day.id = day_id " +
                "where conference_id = ?";

        List<Day> days = executeSQL(sql, this::mapResultSet, conferenceId);

        return days;
    }

    @Override
    public void update(Day updated) throws SQLException {
        String sql = "update day set date = ? where id = ?";

        executeSQL(
                sql,
                updated.getDate(),
                updated.getId()
        );
    }

    @Override
    public void delete(Object id) throws SQLException {

        // references
        String sql = "delete from conference_day where day_id = ?";
        executeSQL(sql, id);
        sql = "delete from timeslot_day where day_id = ?";
        executeSQL(sql, id);

        // main storage
        sql = "delete from day where id = ?";
        executeSQL(sql, id);
    }

    @Override
    protected Day mapResultSet(ResultSet resultSet) throws SQLException {

        UUID dayId = (UUID) resultSet.getObject(1);

        TimeslotDao timeslotDao = new TimeslotDao(dataSource);
        // går inn i koblingstabell:
        List<Timeslot> timeslots = timeslotDao.retrieveByDay(dayId);

        return new Day(
                dayId,
                resultSet.getDate(2).toLocalDate(),
                timeslots
        );
    }


}
