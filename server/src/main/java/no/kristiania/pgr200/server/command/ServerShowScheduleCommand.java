package no.kristiania.pgr200.server.command;


import com.google.gson.Gson;
import no.kristiania.pgr200.core.command.ShowScheduleCommand;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.Dao;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;


public class ServerShowScheduleCommand extends ShowScheduleCommand implements ServerCommand {

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
