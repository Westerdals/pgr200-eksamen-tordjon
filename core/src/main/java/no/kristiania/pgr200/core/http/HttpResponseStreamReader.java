package no.kristiania.pgr200.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class HttpResponseStreamReader {
    List<String> lines;

    HttpResponseStreamReader(InputStream input) throws IOException {
        this.lines = new ArrayList<String>();
        buildInputList(input);
    }

    // TODO: Refactor this
    private void buildInputList(InputStream input) throws IOException {


        StringBuilder line = new StringBuilder();
        int contentLength = 0;
        // reading status and headers
        int c;
        while ((c = input.read()) != -1) {
            char character = (char) c;
            line.append(character);

            if (character == '\n') {
                lines.add(line.toString());
                if (line.toString().equals("\r\n")) {
                    try {
                        contentLength = Integer.parseInt(getHeader("Content-Length"));
                    } catch (NumberFormatException e) {
                        contentLength = 0;
                    }
                    break;
                }

                line.setLength(0);
            }
        }

        int counter = 0;
        while (counter < contentLength) {
            char character = (char) input.read();
            line.append(character);
            counter++;
        }
        lines.add(line.toString());
    }


    /**
     * Return the value with specified header field
     * from response
     * @return the header value if exists, null otherwise
     */
    String getHeader(String field) {
        field = field.toLowerCase();
        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                String currentField = parts[0].toLowerCase();
                String value = parts[1].trim();

                if (field.equals(currentField)) {
                    return value;
                }
            }
        }
        return null;
    }

    /**
     * Returns the value of no.kristiania.pgr200.core.http body
     * @return the body if present, null if otherwise
     * @throws IOException
     */
    String getBody() throws IOException {
        String body = "";

        int startIndex = lines.indexOf("\r\n") + 1;
        if (startIndex == 0) {
            return body;
        }

        for (int i = startIndex; i < lines.size(); i++) {
            body += lines.get(i);
        }

        return body;
    }

    /**
     * Gets status code from the input
     * @return status code
     */

    int getStatusCode() { //TODO: validation -> may fail now
        String statusLine = lines.get(0);
        String[] parts = statusLine.split(" ");
        String code = parts[1];

        return Integer.parseInt(code);
    }


}
