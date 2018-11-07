package no.kristiania.pgr200.core.http.uri;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PathTest {
    @Test
    public void shouldReturnPath() {
        Path path = new Path("example.com/some/path");

        assertThat(path.toString().equals("/some/path"));
    }

    @Test
    public void shouldReturnAnotherPath() {
        Path path = new Path("example.com/some/other/path");

        assertThat(path.toString()).isEqualTo("/some/other/path");
    }

    @Test
    public void shouldHandleProtocol() {
        Path path = new Path("https://example.com/pathypath");

        assertThat(path.toString()).isEqualTo("/pathypath");
    }

    @Test
    public void shouldGivePathParts() {
        Path path = new Path("https://example.com/pathy/path/with/parts");

        assertThat(path.getPathParts()).containsExactly("pathy", "path", "with", "parts");
    }

    @Test
    public void shouldGiveOtherPathparts() {
        Path path = new Path("https://example.com/other/parts");

        assertThat(path.getPathParts()).containsExactly("other", "parts");
    }

    @Test
    public void shouldNotAccountForTrailingBackslash() {
        Path path = new Path("https://example.com/I/have/trailing/backslash");

        assertThat(path.getPathParts()).containsExactly("I", "have", "trailing", "backslash");
    }
}