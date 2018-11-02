package no.kristiania.pgr200.program.command;

import no.kristiania.pgr200.program.InputParser;

import javax.sql.DataSource;
import java.sql.SQLException;

public abstract class Command {

    /**
     * Usage:
     * args = {"insert", "talk", "-title", "TITLE", "-description", "DESCRIPTION", "-topicTitle", "TOPIC"}
     * createComand(args) -> InsertTalkCommand
     * @return command based on user input
     * @throws IllegalArgumentException
     */
    public static Command createCommand(String[] input) throws IllegalArgumentException {
        return InputParser.decodeInput(input);
    }


    /**
     * Builds _and returns_ the command based on input from user.
     * @throws IllegalArgumentException if something is wrong with the command
     */
    public abstract Command build(String[] strings) throws IllegalArgumentException;

    /**
     * Executes the command
     * @param dataSource of database to execute on
     */
    public abstract void execute(DataSource dataSource) throws SQLException;

}
