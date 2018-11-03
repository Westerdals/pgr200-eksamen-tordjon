package no.kristiania.pgr200.server.command;

import com.google.gson.Gson;
import no.kristiania.pgr200.core.http.uri.Path;
import no.kristiania.pgr200.server.InputParser;
import no.kristiania.pgr200.server.ServerResponse;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

public abstract class Command {

    protected Gson gson = new Gson();
    protected ServerResponse response = new ServerResponse();

    /**
     * Usage:
     * args = {"insert", "talk", "-title", "TITLE", "-description", "DESCRIPTION", "-topicTitle", "TOPIC"}
     * createComand(args) -> InsertTalkCommand
     * @return no.kristiania.pgr200.server.command based on user input
     * @throws IllegalArgumentException
     */
    public static Command createCommand(Path path, HashMap<String, String> parameters) throws IllegalArgumentException {
        return InputParser.decodeInput(path, parameters);
    }


    /**
     * Builds _and returns_ the no.kristiania.pgr200.server.command based on input from user.
     * @throws IllegalArgumentException if something is wrong with the no.kristiania.pgr200.server.command
     * @param parameters
     */
    public abstract Command build(HashMap<String, String> parameters) throws IllegalArgumentException;

    /**
     * Executes the no.kristiania.pgr200.server.command
     * Returns the ServerResponse that should be returned to client (as protected field)
     * @param dataSource of no.kristiania.pgr200.server.database to execute on
     */
    public abstract ServerResponse execute(DataSource dataSource) throws SQLException;


    protected UUID getId(String id) {
        try{
            return UUID.fromString(id);
        }catch(IllegalArgumentException e){
            return null;
        }
    }

    protected LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");

        try{
            return LocalDate.parse(date, formatter);
        }catch(IllegalArgumentException e){
            return null;
        }
    }

    protected LocalTime getTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        try{
            return LocalTime.parse(time, formatter);
        }catch(IllegalArgumentException e){
            return null;
        }
    }

}
