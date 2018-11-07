package no.kristiania.pgr200.core.http.uri;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QueryTest {
    @Test
    public void shouldReturnQuery() throws UnsupportedEncodingException {
        Query query = new Uri("example.com/path?first=value1&second=value2").getQuery();

        assertThat(query.toString()).isEqualTo("?first=value1&second=value2");
    }

    @Test
    public void shouldHandleEmtpyQuery() throws UnsupportedEncodingException {
        Query query = new Uri("example.com/path").getQuery();

        assertThat(query.toString()).isEqualTo("");
    }

    @Test
    public void shouldHandleEmptyButSpecifiedQuery() throws UnsupportedEncodingException {
        Query query = new Query("ex.com/path?");

        assertThat(query.toString()).isEqualTo("");
    }

    @Test
    public void shoudGiveValue() throws UnsupportedEncodingException {
        Query query = new Query("name=john");

        assertThat(query.getArgument("name")).isEqualTo("john");
    }

    @Test
    public void shoudGiveAnotherValue() throws UnsupportedEncodingException {
        Query query = new Query("name=guro");

        assertThat(query.getArgument("name")).isEqualTo("guro");
    }



    @Test
    public void shouldHandleMultipleParameters() throws UnsupportedEncodingException {
        Query query = new Query("name=john&age=25");

        assertThat(query.getArgument("name")).isEqualTo("john");
        assertThat(query.getArgument("age")).isEqualTo("25");
    }

    @Test
    public void shouldHandleInvalidPair() throws UnsupportedEncodingException {
        Query query = new Query("example.com/path?name&age=25");

        assertThat(query.getArgument("age")).isEqualTo("25");
        assertThat(query.getArgument("name")).isEqualTo(null);
    }
}