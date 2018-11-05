package no.kristiania.pgr200.core.http.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class Query {

    private Map<String, String> arguments;

    public Query(String arguments) throws UnsupportedEncodingException {
        this.arguments = getArgumentsFrom(arguments);
    }

    public Query(Map<String, String> arguments) {
        this.arguments = arguments;
    }

    public String getArgument(String key) {
        return arguments.get(key);
    }

    private HashMap<String, String> getArgumentsFrom(String url) throws UnsupportedEncodingException {

        HashMap<String, String> arguments = new HashMap<>();
        String[] pairs = url.split("&");

        for (String pair : pairs) {
            String[] splitted = pair.split("=");


            if (splitted.length != 2) continue;

            String parameter = URLDecoder.decode(splitted[0], "UTF-8");
            String value = URLDecoder.decode(splitted[1], "UTF-8");

            arguments.put(parameter, value);

        }

        return arguments;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry entry : arguments.entrySet()) {
                stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        if (stringBuilder.length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }
}