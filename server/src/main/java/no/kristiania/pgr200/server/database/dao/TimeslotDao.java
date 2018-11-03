package no.kristiania.pgr200.server.database.dao;

import no.kristiania.pgr200.server.database.model.Talk;
import no.kristiania.pgr200.server.database.model.Timeslot;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TimeslotDao extends Dao<Timeslot> {

    public TimeslotDao(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public void insert(Timeslot timeslot) throws SQLException {
        String sql = "insert into timeslot (id, start_time, end_time, talk_id) values (?, ?, ?, ?)";
        executeSQL(
                sql,
                timeslot.getId(),
                timeslot.getStart(),
                timeslot.getEnd(),
                timeslot.getTalkId()
        );
    }

    @Override
    public Timeslot retrieve(Object id) throws SQLException {

        String sql = "select id, start_time, end_time, talk_id from timeslot where id = ?";
        List<Timeslot> results = executeSQL(sql, this::mapResultSet, id);

        return results.size() != 0 ? results.get(0) :  null;
    }

    public void connectTimeslotToDay(UUID timeslotid, UUID dayId) throws SQLException {
        String sql = "insert into timeslot_day (timeslot_id, day_id) values (?, ?)";
        executeSQL(sql, timeslotid, dayId);
    }

    public void connectTalkToTimeslot(UUID talkId, UUID timeslotId) throws SQLException {
        /*String sql = "insert into talk_timeslot (talk_id, timeslot_id) values (?, ?)";
        executeSQL(sql, talkId, timeslotId);*/

        String sql = "update timeslot set talk_id = ? where id = ?";
        executeSQL(sql, talkId, timeslotId);
    }



    public List<Timeslot> retrieveByDay(UUID dayId) throws SQLException {

        String sql = "" +
                "select id, start_time, end_time, talk_id " +
                "from timeslot inner join timeslot_day " +
                "on timeslot.id = timeslot_id " +
                "where day_id = ?";

        return executeSQL(sql, this::mapResultSet, dayId);
    }

    @Override
    public List<Timeslot> retrieveAll() throws SQLException {
        String sql = "select id, start_time, end_time, talk_id from timeslot";
        List<Timeslot> results = executeSQL(sql, this::mapResultSet);

        return results;
    }

    @Override
    public void update(Timeslot updated) throws SQLException {
        String sql = "update timeslot set start_time = ?, end_time = ?, talk_id = ? where id = ?";

        executeSQL(
                sql,
                updated.getStart(),
                updated.getEnd(),
                updated.getTalkId(),
                updated.getId()
        );
    }

    @Override
    public void delete(Object id) throws SQLException {
        String sql = "delete from timeslot_day where timeslot_id = ?";
        executeSQL(sql, id);

        sql = "delete from talk_timeslot where timeslot_id = ?";
        executeSQL(sql, id);

        sql = "delete from timeslot where id = ?";
        executeSQL(sql, id);
    }

    @Override
    protected Timeslot mapResultSet(ResultSet resultSet) throws SQLException {
        Talk talk = new TalkDao(dataSource).retrieve((UUID) resultSet.getObject(4));
        Timeslot timeslot = new Timeslot(
                (UUID) resultSet.getObject(1),
                resultSet.getTime(2).toLocalTime(),
                resultSet.getTime(3).toLocalTime(),
                talk

        );
        return timeslot;
    }
}
