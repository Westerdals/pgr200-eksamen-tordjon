package no.kristiania.pgr200.http.uri;

public class Uri {

    private String uri;
    private Path path;
    private Query query;

    public Uri(String uri) {
        this.uri = uri;
        path = new Path(uri);
        query = new Query(uri);
    }

    public Path getPath() {
        return path;
    }

    public Query getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return uri;
    }
}
