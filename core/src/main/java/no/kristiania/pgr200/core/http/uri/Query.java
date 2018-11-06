package no.kristiania.pgr200.core.http.uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Query {

    private HashMap<String, String> arguments = new HashMap<>();

    public Query(String arguments) {
        this.arguments = getArgumentsFrom(arguments);
    }

    public Query(HashMap<String, String> arguments) {
        for(Map.Entry e : arguments.entrySet()){
            if (e.getValue() == null)
                continue;
            this.arguments.put(URLEncoder.encode(e.getKey().toString()), URLEncoder.encode(e.getValue().toString()));
        }
    }

    public String getArgument(String key) {
        return arguments.get(key);
    }

    private HashMap<String, String> getArgumentsFrom(String url) {

        HashMap<String, String> arguments = new HashMap<>();
        String[] pairs = url.split("&");

        for (String pair : pairs) {
            String[] splitted = pair.split("=");


            if (splitted.length != 2) continue;

            String parameter = null;
            String value = null;

            try {
                parameter = URLDecoder.decode(splitted[0], "UTF-8");
                value = URLDecoder.decode(splitted[1], "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // UnsupportedEncodingException should never happen @jhannes  :-)
                continue;
            }

            arguments.put(parameter, value);

        }

        return arguments;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("?");
        for(Map.Entry entry : arguments.entrySet()) {
                stringBuilder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        if (stringBuilder.length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public HashMap<String, String> getArguments() {
        return arguments;
    }
}