package no.kristiania.pgr200.server.command;

import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.DayDao;
import no.kristiania.pgr200.server.database.dao.TalkDao;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import no.kristiania.pgr200.server.database.model.Conference;
import no.kristiania.pgr200.server.database.model.Day;
import no.kristiania.pgr200.server.database.model.Talk;
import no.kristiania.pgr200.server.database.model.Timeslot;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class CreateDemoConferenceCommand extends Command {
    @Override
    public Command build(HashMap<String, String> parameters) throws IllegalArgumentException {
        return new CreateDemoConferenceCommand();
    }

    @Override
    public void execute(DataSource dataSource) throws SQLException {
        ConferenceDao conferenceDao = new ConferenceDao(dataSource);
        Conference blizzcon = new Conference("Blizzcon");
        conferenceDao.insert(blizzcon);

        DayDao dayDao = new DayDao(dataSource);
        //2 x days
        Day day1 = new Day(LocalDate.now().plusDays(1));
        Day day2 = new Day(LocalDate.now().plusDays(2));
        //insert days
        dayDao.insert(day1);
        dayDao.insert(day2);

        //connect days to conference
        dayDao.connectDayToConference(blizzcon.getId(), day1.getId());
        dayDao.connectDayToConference(blizzcon.getId(), day2.getId());

        TimeslotDao timeslotDao = new TimeslotDao(dataSource);
        //4 x timeslots
        Timeslot timeslot11 = new Timeslot(LocalTime.of(11, 00), LocalTime.of(13,30));
        Timeslot timeslot12 = new Timeslot(LocalTime.of(14,00), LocalTime.of(16,15));
        Timeslot timeslot21 = new Timeslot(LocalTime.of(10, 00), LocalTime.of(12,30));
        Timeslot timeslot22 = new Timeslot(LocalTime.of(13,00), LocalTime.of(15,15));
        //insert timeslots
        timeslotDao.insert(timeslot11);
        timeslotDao.insert(timeslot12);
        timeslotDao.insert(timeslot21);
        timeslotDao.insert(timeslot22);
        //connect timeslots to days
        timeslotDao.connectTimeslotToDay(timeslot11.getId(), day1.getId());
        timeslotDao.connectTimeslotToDay(timeslot12.getId(), day1.getId());
        timeslotDao.connectTimeslotToDay(timeslot21.getId(), day2.getId());
        timeslotDao.connectTimeslotToDay(timeslot22.getId(), day2.getId());


        TalkDao talkDao = new TalkDao(dataSource);
        // 4 x talks
        Talk talk1 = new Talk("WoW", "Bfa sucks", "bad games");
        Talk talk2 = new Talk("Dialo4", "Not this year", "bad games");
        Talk talk3 = new Talk("New blizzard game", "not happening", "bad games");
        Talk talk4 = new Talk("Blizzard making money", "lots of it", "blizzard");
        //insert talks
        talkDao.insert(talk1);
        talkDao.insert(talk2);
        talkDao.insert(talk3);
        talkDao.insert(talk4);
        //connect talks to timeslots
        timeslotDao.connectTalkToTimeslot(talk1.getId(), timeslot11.getId());
        timeslotDao.connectTalkToTimeslot(talk2.getId(), timeslot12.getId());
        timeslotDao.connectTalkToTimeslot(talk3.getId(), timeslot21.getId());
        timeslotDao.connectTalkToTimeslot(talk4.getId(), timeslot22.getId());


        Conference retrievedBlizzcon = conferenceDao.retrieve(blizzcon.getId());
        System.out.println(retrievedBlizzcon);



    }
}
