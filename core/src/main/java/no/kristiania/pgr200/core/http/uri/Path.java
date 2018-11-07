package no.kristiania.pgr200.core.http.uri;

import java.util.Arrays;

/**
 * Path in a URL
 */
public class Path {


    private String rawPath;

    public Path(String url) {
        this.rawPath = extractPath(url);
    }


    public String[] getPathParts() {

        String[] pathParts = toString().split("/");
        /*
            We format paths with preceding "/".
            Therefore, the first element here will always be empty.
            It is removed.
        */
        pathParts = Arrays.copyOfRange(pathParts, 1, pathParts.length);


        return pathParts;
    }


    @Override
    public String toString() {
        return rawPath; 
    }

    private String extractPath(String url) {
        url = removeProtocol(url); // comes in the way with its preceding "//", i.e. https://example.com

        int start = url.indexOf('/'); // will fail once https:// is thrown into the mix
        int end = url.indexOf('?');

        // in case no rawPath present:
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
