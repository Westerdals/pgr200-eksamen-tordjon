package no.kristiania.pgr200.server.database.dao;

import no.kristiania.pgr200.core.model.Talk;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TalkDao extends Dao<Talk> {

    public TalkDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void insert(Talk talk) throws SQLException {
        String sql = "insert into talk (id, title, description, topic_title) values (?, ?, ?, ?)";

        executeSQL(
                sql,
                talk.getId(),
                talk.getTitle(),
                talk.getDescription(),
                talk.getTopicTitle()
        );

    }

    @Override
    public Talk retrieve(Object id) throws SQLException {
        String sql = "select id, title, description, topic_title from talk where id = ?";
        List<Talk> results = executeSQL(sql, this::mapResultSet, id);

        return results.size() != 0 ? results.get(0) :  null;
    }



    public List<Talk> retrieveByTimeslot(UUID timeslotId) throws SQLException {

        String sql = "" +
                "select talk.id, title, description, topic_title " +
                "from talk inner join timeslot " +
                "on talk.id = timeslot.talk_id " +
                "where timeslot.id = ?";

        return executeSQL(sql, this::mapResultSet, timeslotId);
    }

    @Override
    public List<Talk> retrieveAll() throws SQLException {
        String sql = "select id, title, description, topic_title from talk";
        List<Talk> results = executeSQL(sql, this::mapResultSet);

        return results;
    }

    @Override
    public void update(Talk updated) throws SQLException {
        String sql = "update talk set title = ?, description = ?, topic_title = ? where id = ?";

        executeSQL(
                sql,
                updated.getTitle(),
                updated.getDescription(),
                updated.getTopicTitle(),
                updated.getId()
        );
    }

    @Override
    public void delete(Object id) throws SQLException {

        String sql = "delete from talk where id = ?";
        executeSQL(sql, id);
    }

    @Override
    protected Talk mapResultSet(ResultSet resultSet) throws SQLException {

            Talk talk = new Talk(
                    (UUID) resultSet.getObject(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );

        return talk;
    }
}
