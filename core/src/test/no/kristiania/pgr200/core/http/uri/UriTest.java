package no.kristiania.pgr200.core.http.uri;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UriTest {
    @Test
    public void shouldParseUrlWithEmptyPath() throws UnsupportedEncodingException {
        Uri uri = new Uri("example.com?name=elon&age=3");

        Path path = uri.getPath();

        assertThat(path.toString()).isEqualTo("");
        assertThat(path.getPathParts()).containsExactly();
    }

    @Test
    public void shouldParseUrlWithEmptyQuery() throws UnsupportedEncodingException {
        Uri uri = new Uri("example.com/path/to/file.html");

        Query query = uri.getQuery();
        Path path = uri.getPath();

        assertThat(path.toString()).isEqualTo("/path/to/file.html");

        assertThat(query.toString()).isEqualTo("");
    }

    @Test
    public void shouldParseUrl() throws UnsupportedEncodingException {
        Uri uri = new Uri("example.com/path/to/file.html?name=elon&age=3");

        Query query = uri.getQuery();
        Path path = uri.getPath();

        assertThat(path.toString()).isEqualTo("/path/to/file.html");
        assertThat(path.getPathParts()).containsExactly("path", "to", "file.html");

        assertThat(query.getArgument("name")).isEqualTo("elon");
        assertThat(query.getArgument("age")).isEqualTo("3");
    }
}