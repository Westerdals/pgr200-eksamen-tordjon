package no.kristiania.pgr200.program.command;

import no.kristiania.pgr200.database.Util;
import no.kristiania.pgr200.program.Program;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ResetDBCommand extends Command {

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new ResetDBCommand();
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        String filename = Program.getPropertiesFilename();
        try {
            Util.resetDatabase(filename);
        } catch (IOException e) {
            System.out.println("Could not reset database.");
        }
    }

}
