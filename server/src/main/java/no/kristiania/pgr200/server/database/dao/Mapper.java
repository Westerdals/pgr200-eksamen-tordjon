package no.kristiania.pgr200.server.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The takes resultset as input and
 * returns T.
 *
 * @param <T> The resultset should be mapped to
 */
public interface Mapper<T> {

    T map(ResultSet resultSet) throws SQLException;

}
