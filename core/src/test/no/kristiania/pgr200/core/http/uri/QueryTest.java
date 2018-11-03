package no.kristiania.pgr200.core.http.uri;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;

public class QueryTest {
    @Test
    public void shouldReturnQuery() {
        Query query = new Query("example.com/path?first=value1&second=value2");

        assertThat(query.getQuery()).isEqualTo("first=value1&second=value2");
    }

    @Test
    public void shouldHandleEmtpyQuery() {
        Query query = new Query("example.com/path");

        assertThat(query.getQuery()).isEqualTo("");
    }

    @Test
    public void shouldHandleEmptyButSpecifiedQuery() {
        Query query = new Query("ex.com/path?");

        assertThat(query.getQuery()).isEqualTo("");
    }

    @Test
    public void shoudGiveValue() {
        Query query = new Query("example.com/path?name=john");

        assertThat(query.getValue("name")).isEqualTo("john");
    }

    @Test
    public void shoudGiveAnotherValue() {
        Query query = new Query("example.com/path?name=guro");

        assertThat(query.getValue("name")).isEqualTo("guro");
    }



    @Test
    public void shouldHandleMultipleParameters() {
        Query query = new Query("example.com/path?name=john&age=25");

        assertThat(query.getValue("name")).isEqualTo("john");
        assertThat(query.getValue("age")).isEqualTo("25");
    }

    @Test
    public void shouldHandleInvalidPair() {
        Query query = new Query("example.com/path?name&age=25");

        assertThat(query.getValue("age")).isEqualTo("25");
        assertThat(query.getValue("name")).isEqualTo("");
    }
}