package no.kristiania.pgr200.http.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class Query {
    private String query;

    public Query(String url) {
        this.query = extractQuery(url);
    }

    public String getValue(String parameterToFind) {
        for(String query : getQueries()) {
            String[] pair = query.split("=");

            if (isValidPair(pair)) {

                String parameter = pair[0];
                String value = pair[1];

                if (parameter.equals(parameterToFind)) {
                    return value;
                }
            }
        }

        return "";
    }

    public String getQuery() {
        return query;
    }

    private String extractQuery(String url) {
        String[] parts = url.split("\\?");

        // no "?"
        if (parts.length == 1) return "";

        return url.split("\\?")[1];
    }

    private String[] getQueries() {
        String[] queries = this.query.split("&");
        return queries;
    }

    private boolean isValidPair(String[] pair) {
        return pair.length == 2;
    }

    public HashMap<String, String> getParameterValuePairs() throws UnsupportedEncodingException {

        HashMap<String, String> parameterValuePairs = new HashMap<>();
        String[] pairs = getQueries();

        for (String pair : pairs) {
            String[] splitted = pair.split("=");


            if (splitted.length != 2) continue;

            String parameter = URLDecoder.decode(splitted[0], "UTF-8");
            String value = URLDecoder.decode(splitted[1], "UTF-8");

            parameterValuePairs.put(parameter.toLowerCase(), value);

        }

        return parameterValuePairs;
    }
}