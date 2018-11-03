package no.kristiania.pgr200.core.http.uri;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;

public class UriTest {
    @Test
    public void shouldParseUrlWithEmptyPath() {
        Uri uri = new Uri("example.com?name=elon&age=3");

        Path path = uri.getPath();

        assertThat(path.toString()).isEqualTo("");
        assertThat(path.getPathParts()).containsExactly();
    }

    @Test
    public void shouldParseUrlWithEmptyQuery() {
        Uri uri = new Uri("example.com/path/to/file.html");

        Query query = uri.getQuery();
        Path path = uri.getPath();

        assertThat(path.toString()).isEqualTo("/path/to/file.html");

        assertThat(query.getQuery()).isEqualTo("");
    }

    @Test
    public void shouldParseUrl() {
        Uri uri = new Uri("example.com/path/to/file.html?name=elon&age=3");

        Query query = uri.getQuery();
        Path path = uri.getPath();

        assertThat(path.toString()).isEqualTo("/path/to/file.html");
        assertThat(path.getPathParts()).containsExactly("path", "to", "file.html");

        assertThat(query.getValue("name")).isEqualTo("elon");
        assertThat(query.getValue("age")).isEqualTo("3");
    }
}