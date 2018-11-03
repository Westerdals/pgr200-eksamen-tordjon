package no.kristiania.pgr200.server.command;


import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;
import model.Conference;
import model.Day;
import model.Talk;
import model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class ShowScheduleCommand extends Command {
    private UUID id;

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = getId(parameters.get("id"));

        return new ShowScheduleCommand()
                .withId(id);

    }



    private Command withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public ServerResponse execute(DataSource dataSource) throws SQLException {
        Dao<Conference> dao = new ConferenceDao(dataSource);
        Conference conference = dao.retrieve(id);

        System.out.println(conference.getName() + ": ");
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
        }

        return null;
    }
}
