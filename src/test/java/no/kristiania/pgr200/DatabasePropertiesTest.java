package no.kristiania.pgr200;

import no.kristiania.pgr200.database.DatabaseProperties;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DatabasePropertiesTest {

    DatabaseProperties databaseProperties;

    @Before
    public void init() throws IOException {
        databaseProperties = new DatabaseProperties("innlevering.properties");
    }
    // only testing insensitive data

    @Test
    public void testReadingProperty() {
        String username = "databaseuser";
        assertThat(databaseProperties.getUsername()).isEqualTo(username);
    }

    @Test
    public void testReadingUrl() {
        String url = "jdbc:postgresql://localhost/arbeidskrav2";
        assertThat(databaseProperties.getUrl()).isEqualTo(url);
    }
}