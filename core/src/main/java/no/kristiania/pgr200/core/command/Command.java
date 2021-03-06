package no.kristiania.pgr200.core.command;

import no.kristiania.pgr200.core.InputParser;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * A command represents an action that the program should execute.
 *
 * Each command has methods to build itself (for example by getting values)
 * and to execute.
 */
public abstract class Command {


    /**
     * Usage:
     * args = {"insert", "talk", "-title", "TITLE", "-description", "DESCRIPTION", "-topicTitle", "TOPIC"}
     * createComand(args) -> InsertTalkCommand
     * @return no.kristiania.pgr200.server.command based on user input
     * @throws IllegalArgumentException
     */
    public static Command createCommand(Map<String, Class<? extends Command>> map, String path, HashMap<String, String> parameters) throws IllegalArgumentException {
        return InputParser.decodeInput(map, path, parameters);
    }


    /**
     * Builds _and returns_ the no.kristiania.pgr200.server.command based on input from a map.
     * @throws IllegalArgumentException if something is wrong with the no.kristiania.pgr200.server.command
     * @param parameters
     */
    public abstract Command build(HashMap<String, String> parameters) throws IllegalArgumentException;

    /**
     * Executes the no.kristiania.pgr200.server.command
     * Returns the ServerResponse that should be returned to client (as protected field)
     * @param dataSource of no.kristiania.pgr200.server.database to execute on
     */
    public abstract <T, E extends Exception> T execute(DataSource dataSource) throws E, IOException;




    protected UUID getId(String id) {
        try{
            return UUID.fromString(id);
        }catch(IllegalArgumentException e){
            return null;
        }
    }

    protected LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        if(date == null){
            return null;
        }
        try{
            return LocalDate.parse(date, formatter);
        }catch(NullPointerException | IllegalArgumentException | DateTimeParseException e){
            return null;
        }
    }

    protected LocalTime getTime(String time) {
        if(time == null)
            return null;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        try{
            return LocalTime.parse(time, formatter);
        }catch(IllegalArgumentException  | NullPointerException | DateTimeParseException e){
            return null;
        }
    }

}
