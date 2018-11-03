package no.kristiania.pgr200.server.database.dao;


import no.kristiania.pgr200.server.database.Util;
import model.Conference;
import model.Day;
import model.Talk;
import model.Timeslot;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ConferenceDaoTest implements DaoTest<Conference> {

    private Dao<Conference> dao;
    private DataSource dataSource;

    @Override
    public Dao<Conference> getDao() {
        return dao;
    }

    @Before
    @Override
    public void createDao() throws IOException {
        dataSource = Util.createDataSource("./../test.properties");
        dao = new ConferenceDao(dataSource);
    }

    @Override
    @Test
    public void testInsertingSeveral() throws SQLException {


        Conference[] conferences = new Conference[]{
                new Conference("Javazone"),
                new Conference("mobileEra"),
                new Conference("Blizzcon")
        };

        for(Conference conference : conferences) {
            getDao().insert(conference);
        }

        for(Conference conference : conferences) {
            Conference received = getDao().retrieve(conference.getId());

            assertThat(received).isEqualToComparingFieldByFieldRecursively(conference);
        }

    }
    @Test
    public void testEntireConference() throws SQLException {
        Conference blizzcon = new Conference("Blizzcon");
        getDao().insert(blizzcon);

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
        Talk talk1 = new Talk("WoW 2.0", "Bfa sucks", "bad games");
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


        Conference retrievedBlizzcon = getDao().retrieve(blizzcon.getId());
        System.out.println(retrievedBlizzcon);


        Conference[] conferences = new Conference[]{
                new Conference("Javazone"),
                new Conference("mobileEra"),
                new Conference("Blizzcon")
        };

        for(Conference conference : conferences) {
            getDao().insert(conference);
        }

        for(Conference conference : conferences) {
            Conference received = getDao().retrieve(conference.getId());

            assertThat(received).isEqualToComparingFieldByFieldRecursively(conference);
        }

    }

    @Override
    @Test
    public void IDShouldBePrimaryKey() throws SQLException {
        Conference conference = new Conference("Minecon");
        Conference duplicate = new Conference(conference.getId(), "CopyCatCon");

        getDao().insert(conference);

        Assertions.assertThatExceptionOfType(SQLException.class).isThrownBy(() -> {
            getDao().insert(duplicate);
        });
    }

    @Override
    @Test
    public void assertGettingAll() throws SQLException {
        Conference[] conferences = new Conference[]{
                new Conference("CSS for humans"),
                new Conference("Turistforeningsens kosecon"),
                new Conference("Keycon"),
                new Conference("Conference of the year"),
                new Conference("OrcEnt lovemeet")
        };

        for(Conference conference : conferences) {
            getDao().insert(conference);
        }

        List<Conference> retrieved = getDao().retrieveAll();
        assertThat(retrieved).asList().contains(conferences);
    }

    @Override
    @Test
    public void shouldUpdate() throws SQLException {
        Conference original = new Conference("Lovecon XD");
        getDao().insert(original);

        Conference updated = new Conference(original.getId(), "Kjaerlaekscon XD");
        getDao().update(updated);

        Conference retrieved = getDao().retrieve(original.getId());
        assertThat(retrieved)
                .isEqualToComparingFieldByFieldRecursively(updated);
    }

    @Override
    @Test
    public void shouldDelete() throws SQLException {
        Conference conference = new Conference("Tordcon");


        getDao().insert(conference);
        Conference retrieved = getDao().retrieve(conference.getId());
        assertThat(retrieved)
                .isNotNull();

        getDao().delete(conference.getId());
        retrieved = getDao().retrieve(conference.getId());
        assertThat(retrieved)
                .isNull();
    }
}