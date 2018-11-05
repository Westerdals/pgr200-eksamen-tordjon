package no.kristiania.pgr200.core.http.uri;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Uri {

    private String uri;
    private Path path;
    private Query query;

    public Uri(String uri)  {
        this.uri = uri;
        path = new Path(uri);
        query = new Query(extractQuery(uri));
    }

    public Uri(String uriWithoutParameters, HashMap<String, String> parameters) {
        this(uriWithoutParameters);
        query = new Query(parameters);
    }

    private String extractQuery(String uri) {
        String[] splitted = uri.split("\\?");
        if (splitted.length < 2) return "";
        return splitted[1];
    }

    public Path getPath() {
        return path;
    }

    public Query getQuery() {
        return query;
    }
    @Override
    public String toString() {
        return uri + query.toString();
    }
}
