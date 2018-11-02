package no.kristiania.pgr200.database.dao;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Minimum tests that should cover CRUD-operations in
 * DAO.
 * @param <T> The object the DAO represents
 */
public interface DaoTest<T> {

    Dao<T> getDao();

    @Before
    void createDao() throws IOException;

    @Test
    void testInsertingSeveral() throws SQLException;

    @Test
    void IDShouldBePrimaryKey() throws SQLException;

    @Test
    void assertGettingAll() throws SQLException;

    @Test
    void shouldUpdate() throws SQLException;

    @Test
    void shouldDelete() throws SQLException;
}
