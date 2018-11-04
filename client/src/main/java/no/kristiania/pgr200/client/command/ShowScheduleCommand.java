package no.kristiania.pgr200.client.command;


import com.google.gson.Gson;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;


public class ShowScheduleCommand extends Command {
    private UUID id;

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));

        return new ShowScheduleCommand()
                .withId(id);

    }

    @Override
    public <T> void assignStandardHttp(T content) {
        Gson gson = new Gson();
        String json = gson.toJson(content);

        response.setStatus(200);
        response.getHeaders().put("Content-Type", "application/json");
        response.getHeaders().put("Content-Length", json.length() + "");
        response.setBody(json);
    }

    private Command withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = dao.retrieve(id);

        /*TODO: move to client. System.out.println(conference.getName() + ": ");
        List<Day> days = conference.getDays();
        for(Day day : days) {

            System.out.println("\t" + day.getDate());
            List<Timeslot> timeslots = day.getTimeslots();
            for(Timeslot timeslot : timeslots) {

                System.out.println("\t\t" + timeslot.getStart() + " - " + timeslot.getEnd());
                Talk talk = timeslot.getTalk();

                System.out.println("\t\t\t title: " + talk.getTitle());
                System.out.println("\t\t\t description: " + talk.getDescription());
            }
        }*/
        assignStandardHttp(conference);
        return response;
    }
}
