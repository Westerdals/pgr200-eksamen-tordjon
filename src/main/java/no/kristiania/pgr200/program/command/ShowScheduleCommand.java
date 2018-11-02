package no.kristiania.pgr200.program.command;

import no.kristiania.pgr200.database.dao.ConferenceDao;
import no.kristiania.pgr200.database.dao.Dao;
import no.kristiania.pgr200.database.model.Conference;
import no.kristiania.pgr200.database.model.Day;
import no.kristiania.pgr200.database.model.Talk;
import no.kristiania.pgr200.database.model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static no.kristiania.pgr200.program.ArgumentParser.getArgument;

public class ShowScheduleCommand extends Command {
    private UUID id;

    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        UUID id = UUID.fromString(getArgument("-id", parameters, null));

        return new ShowScheduleCommand()
                .withId(id);

    }

    private Command withId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
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

    }
}
