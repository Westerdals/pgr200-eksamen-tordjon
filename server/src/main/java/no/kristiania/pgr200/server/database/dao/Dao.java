package no.kristiania.pgr200.server.database.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @param <T> The class being represented
 */
public abstract class Dao<T> {

    public DataSource dataSource;

    public Dao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Insert object into the no.kristiania.pgr200.server.database
     */
    public abstract void insert(T object) throws SQLException;

    /**
     * Retrieve object from the no.kristiania.pgr200.server.database
     */
    public abstract T retrieve(Object v) throws SQLException;

    /**
     * Regtrieves all the objects in no.kristiania.pgr200.server.database
     */
    public abstract List<T> retrieveAll() throws SQLException;

    /**
     * Set item with given V to the updated object
     */
    public abstract void update(T updated) throws SQLException;

    /**
     * Delete the item
     */
    public abstract void delete(Object v) throws SQLException;


    /**
     * Maps a ResultSet to a T
     */
    protected abstract T mapResultSet(ResultSet resultSet) throws SQLException;



    public List<T> executeSQL(String sql, Mapper<T> mapper, Object... values) throws SQLException {

        List<T> results = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                int counter = 1;
                for (Object value : values) {
                    preparedStatement.setObject(counter, value);
                    counter++;
                }

                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    results.add(
                            mapper.map(resultSet)
                    );
                }
            }
        }

        return results;
    }

    public void executeSQL(String sql, Object... values) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                int counter = 1;
                for (Object value : values) {
                    preparedStatement.setObject(counter, value);
                    counter++;
                }

                preparedStatement.executeUpdate();
            }
        }
    }


}
