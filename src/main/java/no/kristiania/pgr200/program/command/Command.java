package no.kristiania.pgr200.program.command;

import no.kristiania.pgr200.http.uri.Path;
import no.kristiania.pgr200.program.InputParser;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class Command {

    /**
     * Usage:
     * args = {"insert", "talk", "-title", "TITLE", "-description", "DESCRIPTION", "-topicTitle", "TOPIC"}
     * createComand(args) -> InsertTalkCommand
     * @return command based on user input
     * @throws IllegalArgumentException
     */
    public static Command createCommand(Path path, HashMap<String, String> parameters) throws IllegalArgumentException {
        return InputParser.decodeInput(path, parameters);
    }


    /**
     * Builds _and returns_ the command based on input from user.
     * @throws IllegalArgumentException if something is wrong with the command
     * @param parameters
     */
    public abstract Command build(HashMap<String, String> parameters) throws IllegalArgumentException;

    /**
     * Executes the command
     * @param dataSource of database to execute on
     */
    public abstract void execute(DataSource dataSource) throws SQLException;

}
