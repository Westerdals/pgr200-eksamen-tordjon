package no.kristiania.pgr200.server.command.insertion;

import model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

public class InsertConferenceCommand extends InsertionCommand {

    private String name;

    private  InsertConferenceCommand withName(String name){
        this.name = name;
        return this;
    }

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        String name = parameters.get("name");
        return new InsertConferenceCommand()
                .withName(name);
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = new Conference(name);
        dao.insert(conference);
        return null;
    }
}
