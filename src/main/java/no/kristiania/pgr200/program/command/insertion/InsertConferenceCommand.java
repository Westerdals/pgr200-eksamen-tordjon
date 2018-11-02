package no.kristiania.pgr200.program.command.insertion;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

import no.kristiania.pgr200.database.dao.ConferenceDao;
import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.model.Conference;
import no.kristiania.pgr200.program.command.Command;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public class InsertConferenceCommand extends Command {

    private String name;

    private  InsertConferenceCommand withName(String name){
        this.name = name;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        String name = getArgument("-name", parameters, "unkown");
        return new InsertConferenceCommand()
                .withName(name);
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(name);
        dao.insert(conference);
    }
}
