package no.kristiania.pgr200.server;

import no.kristiania.pgr200.database.Util;
import no.kristiania.pgr200.http.uri.Path;
import no.kristiania.pgr200.http.uri.Query;
import no.kristiania.pgr200.http.uri.Uri;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;


public class HttpServer {

    private HashMap<String, String> parameters;
    private int port;
    private int actualPort;
    private int statusCode = 200;
    private String statusLine;
    private String server;
    private String location = "localhost";
    private String body = "Hello World!\r\n";
    private String contentType = "text/plain";
    private Path path;

    DataSource dataSource;
    static String propertiesFileName = "innlevering.properties";


    public HttpServer(int port) {
        this.port = port;
    }

    public HttpServer() {
        this(0);
    }

    public void start() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(port);
        this.actualPort = serverSocket.getLocalPort();
        Thread thread = new Thread(() ->  serverThread(serverSocket));
        thread.start();
    }

    public void serverThread(ServerSocket serverSocket) {
        while (true) {
            try {

                Socket clientSocket = serverSocket.accept();

                handleRequest(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace(); //TODO: ???
            }
        }
    }


    private void handleRequest(Socket clientSocket) throws IOException, SQLException {
        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();

        getStatusLine(input);
        parseUri(statusLine);
        //done "retrieving request"




        dataSource = Util.createDataSource(propertiesFileName);

        // User input is passed on
        Command command = Command.createCommand(path, parameters);

        command.execute(dataSource); //TODO: handle sqlexception as 500 or 404 w/e


        /*if (parameters != null) {

            if (parameters.get("location") != null) {
                this.location = parameters.get("location");
            }
            if (parameters.get("status") != null) {
                this.statusCode = Integer.parseInt(parameters.get("status"));
            }
            if (parameters.get("body") != null) {
                this.body = parameters.get("body");
            }
            if (parameters.get("content-type") != null) {
                this.contentType = parameters.get("content-type");
            }
        }*/





        //send response
        writeResponse(output, body);
    }

    private void writeResponse(OutputStream output, String body) throws IOException {
        output.write(("HTTP/1.1 " + statusCode + " Ok\r\n").getBytes());
        output.write(("X-Server-Name:" + server + "\r\n").getBytes());
        output.write(("Content-Type: " + contentType + "\r\n").getBytes());
        output.write(("Location: " + location + "\r\n").getBytes());
        output.write("Connection: close\r\n".getBytes());
        output.write(("Content-Length: " + body.length() + "\r\n").getBytes());
        output.write("\r\n".getBytes());
        output.write(body.getBytes());
        output.flush();
    }

    // Todo: bruke path-klassen
    private void parseUri(String statusLine) throws UnsupportedEncodingException {
        Uri uri = new Uri(getPathFromStatusLine(statusLine));
        path = uri.getPath();
        Query query = uri.getQuery(); //new Query(path);
        parameters = query.getParameterValuePairs();

    }

    private String getPathFromStatusLine(String statusLine) {
        String[] parts = statusLine.split(" ");
        return parts[1];
    }

    // Todo: separeres ut?
    private void getStatusLine(InputStream input) throws IOException {
        this.statusLine = readNextLine(input);
    }

    private String getMethod() {
        return statusLine.split( " ")[0];
    }

    // Todo: separeres ut?
    private static String readNextLine(InputStream input) throws IOException {
        StringBuilder response = new StringBuilder();
        int c;

        while ((c = input.read()) != -1) {
            if (c == '\r') {
                input.mark(1);
                c = input.read();
                if (c != '\n') {
                    input.reset();
                }
                break;
            }
            response.append((char)c);
        }
        return response.toString();
    }

    public int getPort() {
        return actualPort;
    }

}