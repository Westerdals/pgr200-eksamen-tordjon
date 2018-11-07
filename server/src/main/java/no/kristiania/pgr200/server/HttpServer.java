package no.kristiania.pgr200.server;


import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.http.uri.Path;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.server.command.ServerCreateDemoConferenceCommand;
import no.kristiania.pgr200.server.command.ServerResetDBCommand;
import no.kristiania.pgr200.server.command.ServerShowScheduleCommand;
import no.kristiania.pgr200.server.command.connecting.ServerConnectDayWithConference;
import no.kristiania.pgr200.server.command.connecting.ServerConnectTalkWithTimeslotCommand;
import no.kristiania.pgr200.server.command.connecting.ServerConnectTimeslotWithDayCommand;
import no.kristiania.pgr200.server.command.deletion.ServerDeleteConferenceCommand;
import no.kristiania.pgr200.server.command.deletion.ServerDeleteDayCommand;
import no.kristiania.pgr200.server.command.deletion.ServerDeleteTalkCommand;
import no.kristiania.pgr200.server.command.deletion.ServerDeleteTimeslotCommand;
import no.kristiania.pgr200.server.command.insertion.ServerInsertConferenceCommand;
import no.kristiania.pgr200.server.command.insertion.ServerInsertDayCommand;
import no.kristiania.pgr200.server.command.insertion.ServerInsertTalkCommand;
import no.kristiania.pgr200.server.command.insertion.ServerInsertTimeslotCommand;
import no.kristiania.pgr200.server.command.listing.ServerListConferencesCommand;
import no.kristiania.pgr200.server.command.listing.ServerListDaysCommand;
import no.kristiania.pgr200.server.command.listing.ServerListTalksCommand;
import no.kristiania.pgr200.server.command.listing.ServerListTimeslotsCommand;
import no.kristiania.pgr200.server.command.updating.ServerUpdateConferenceCommand;
import no.kristiania.pgr200.server.command.updating.ServerUpdateDayCommand;
import no.kristiania.pgr200.server.command.updating.ServerUpdateTalkCommand;
import no.kristiania.pgr200.server.command.updating.ServerUpdateTimeslotCommand;
import no.kristiania.pgr200.server.database.Util;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The server for our appliaction.
 */
public class HttpServer {

    private int port;
    private int actualPort;

    DataSource dataSource;
    public static String propertiesFileName = "innlevering.properties";

    private HashMap<String, String> parameters;
    private Path path;

    /**
     * Starts a server with custom database-properties
     * @param port port for server to run on
     * @param propertiesFileName custom .properties-file
     */
    public HttpServer(int port, String propertiesFileName) {
        this(port);
        this.propertiesFileName = propertiesFileName;
    }

    /**
     * Runs a server based on default database-properties
     * @param port port for server to run on
     */
    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        this.actualPort = serverSocket.getLocalPort();
        Thread thread = new Thread(() ->  serverThread(serverSocket));
        thread.start();
    }

    public void serverThread(ServerSocket serverSocket) {
        System.out.println("Server running on port: " + port);
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
        ServerResponse serverResponse = new ServerResponse();

        InputStream input = clientSocket.getInputStream();
        OutputStream output = clientSocket.getOutputStream();

        //getStatusLine(input);
        parseUri(readNextLine(input));
        //done "retrieving request"


        if(path.toString().equals("/favicon.ico")){
            ServerResponse sr = new ServerResponse();
            sr.setStatus(404);
            writeResponse(output, sr );
            return;
        }



        dataSource = Util.createDataSource(propertiesFileName);
        // User input is passed on
        Command command = Command.createCommand(populateCommandMap(), path.toString(), parameters);

        try {
            if(command == null){
                serverResponse.setStatus(404);
                serverResponse.getHeaders().put("Content-Length", "0");
            } else{
                serverResponse = command.execute(dataSource);
            }
        } catch (Exception e) {
            if(e instanceof SQLException){
                serverResponse.setStatus(500);
            }else{
                e.printStackTrace();
            }
        }


        writeResponse(output, serverResponse);
    }

    private void writeResponse(OutputStream output, ServerResponse response) throws IOException {

        Map<String, String> headers = response.getHeaders();

        output.write(("HTTP/1.1 " + response.getStatus() + "\r\n").getBytes());
        for(Map.Entry entry : headers.entrySet()) {
            output.write((entry.getKey() + ": " + entry.getValue() + "\r\n").getBytes());
        }

        output.write("\r\n".getBytes());

        if(response.getBody() != null){
            output.write(response.getBody().getBytes());
        }

        output.flush();

    }

    private void parseUri(String statusLine) throws UnsupportedEncodingException {
        Uri uri = new Uri(statusLine.split(" ")[1]);
        path = uri.getPath();
        parameters = uri.getQuery().getArguments();
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


    private Map<String, Class<? extends Command>> populateCommandMap() {
        Map<String, Class<? extends Command>> map = new HashMap<>();

        // talk
        map.put("/api/insert/talk", ServerInsertTalkCommand.class);
        map.put("/api/list/talks", ServerListTalksCommand.class);
        map.put("/api/delete/talk", ServerDeleteTalkCommand.class);
        map.put("/api/update/talk", ServerUpdateTalkCommand.class);

        // day
        map.put("/api/insert/day", ServerInsertDayCommand.class);
        map.put("/api/list/days", ServerListDaysCommand.class);
        map.put("/api/delete/day", ServerDeleteDayCommand.class);
        map.put("/api/update/day", ServerUpdateDayCommand.class);

        // timeslot
        map.put("/api/insert/timeslot", ServerInsertTimeslotCommand.class);
        map.put("/api/list/timeslots", ServerListTimeslotsCommand.class);
        map.put("/api/delete/timeslot", ServerDeleteTimeslotCommand.class);
        map.put("/api/update/timeslot", ServerUpdateTimeslotCommand.class);

        // day
        map.put("/api/insert/conference", ServerInsertConferenceCommand.class);
        map.put("/api/list/conferences", ServerListConferencesCommand.class);
        map.put("/api/delete/conference", ServerDeleteConferenceCommand.class);
        map.put("/api/update/conference", ServerUpdateConferenceCommand.class);

        // connecting
        map.put("/api/connect/day-with-conference", ServerConnectDayWithConference.class);
        map.put("/api/connect/talk-with-timeslot", ServerConnectTalkWithTimeslotCommand.class);
        map.put("/api/connect/timeslot-with-day", ServerConnectTimeslotWithDayCommand.class);


        //show conference program
        map.put("/api/showschedule", ServerShowScheduleCommand.class);
        //create demo conference
        map.put("/api/createdemo", ServerCreateDemoConferenceCommand.class);
        // resetting the database
        map.put("/api/resetdb", ServerResetDBCommand.class);
        return map;

    }

}