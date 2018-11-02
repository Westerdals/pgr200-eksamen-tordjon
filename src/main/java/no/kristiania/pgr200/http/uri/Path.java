package no.kristiania.pgr200.http.uri;

import java.util.Arrays;

public class Path {


    private String path;

    public Path(String url) {
        this.path = extractPath(url);
    }

    public String getPath() {
        return path;
    }

    public String[] getPathParts() {

        String[] pathParts = getPath().split("/");
        /*
            We format paths with preceding "/".
            Therefore, the first element here will always be empty.
            It is removed.
        */
        pathParts = Arrays.copyOfRange(pathParts, 1, pathParts.length);


        return pathParts;
    }





    private String extractPath(String url) {
        url = removeProtocol(url); // comes in the way with its preceding "//", i.e. https://example.com

        int start = url.indexOf('/'); // will fail once https:// is thrown into the mix
        int end = url.indexOf('?');

        // in case no path present:
        if (start == -1) return "";
        // in case no arguments
        if (end == -1) end = url.length();

        String path = url.substring(start, end);

        return path;
    }

    private String removeProtocol(String url) {
        if (!url.contains("://")) return url;

        int start = url.indexOf("://") + 3; // 3 is "://".length()
        int end = url.length();

        return url.substring(start, end);
    }
}
