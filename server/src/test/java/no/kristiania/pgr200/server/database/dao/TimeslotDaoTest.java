package no.kristiania.pgr200.server.database.dao;


import no.kristiania.pgr200.server.database.Util;
import model.Day;
import model.Talk;
import model.Timeslot;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class TimeslotDaoTest implements DaoTest<Timeslot> {



    protected TimeslotDao dao;
    DataSource dataSource;

    @Override
    @Before
    public void createDao() throws IOException {
         dataSource = Util.createDataSource("test.properties");
        dao = new TimeslotDao(dataSource);
    }


    @Override
    public TimeslotDao getDao() {
        return dao;
    }

    @Override
    @Test
    public void testInsertingSeveral() throws SQLException {
        Timeslot[] timeslots = new Timeslot[]{
                new Timeslot( LocalTime.of(13,0), LocalTime.of(14, 0)),
                new Timeslot( LocalTime.of(7,30), LocalTime.of(9, 0)),
                new Timeslot( LocalTime.of(14,30), LocalTime.of(17, 0))
        };

        for(Timeslot timeslot : timeslots) {
            getDao().insert(timeslot);
        }

        for (Timeslot timeslot : timeslots) {
            Timeslot received = getDao().retrieve(timeslot.getId());

            assertThat(received).isEqualToComparingFieldByFieldRecursively(timeslot);
        }
    }

    @Test
    public void TestInsertingOneWithoutTalk() throws SQLException {
        Timeslot timeslot = new Timeslot( LocalTime.of(10,0), LocalTime.of(12, 0));
        getDao().insert(timeslot);

        Timeslot receivedTimeslot = getDao().retrieve(timeslot.getId());

        assertThat(receivedTimeslot).isEqualToComparingFieldByFieldRecursively(timeslot);
    }

    @Test
    public void TestInsertingOneWithTalk() throws SQLException, IOException {



        Talk talk = new Talk("Timeslottalk", "timeslottalktest", "timeslot talk topic");
        TalkDao talkDao = new TalkDao(dataSource);
        talkDao.insert(talk);

        Talk receievedTimeslotTalk = talkDao.retrieve(talk.getId());
        assertThat(talk).isEqualToComparingFieldByFieldRecursively(receievedTimeslotTalk);

        //bruk talk id når oppretter insert Timeslot
        Timeslot timeslot = new Timeslot( LocalTime.of(10,0), LocalTime.of(12, 0), talk);
        getDao().insert(timeslot);

        
        Timeslot receivedTimeslot = getDao().retrieve(timeslot.getId());

        assertThat(receivedTimeslot).isEqualToComparingFieldByFieldRecursively(timeslot);

    }




    @Test
    @Override
    public void IDShouldBePrimaryKey() throws SQLException {
        Timeslot timeslot = new Timeslot( LocalTime.of(10,0), LocalTime.of(11, 30));
        getDao().insert(timeslot);

        assertThatExceptionOfType(SQLException.class).isThrownBy(() -> {
            getDao().insert(timeslot);
        });
    }
    @Test
    @Override
    public void assertGettingAll() throws SQLException {
        Timeslot[] timeslots = new Timeslot[]{
                new Timeslot(LocalTime.of(10,0), LocalTime.of(11, 30)),
                new Timeslot(LocalTime.of(11,0), LocalTime.of(12, 30)),
                new Timeslot(LocalTime.of(12,0), LocalTime.of(13, 30)),
                new Timeslot(LocalTime.of(13,0), LocalTime.of(14, 30)),
                new Timeslot(LocalTime.of(14,0), LocalTime.of(15, 30)),

        };

        for(Timeslot timeslot : timeslots){
            getDao().insert(timeslot);
        }

        assertThat(getDao().retrieveAll())
                .asList()
                .contains(timeslots);
    }
    @Test
    @Override
    public void shouldUpdate() throws SQLException {
        Timeslot timeslot = new Timeslot(LocalTime.of(14,0), LocalTime.of(15, 30));
        getDao().insert(timeslot);

        Timeslot updated = new Timeslot(timeslot.getId(), LocalTime.of(14,0), LocalTime.of(15, 30));
        getDao().update(updated);

        Timeslot received = getDao().retrieve(timeslot.getId());
        assertThat(received).isEqualToComparingFieldByFieldRecursively(updated);
    }


    @Test
    public void shouldConnectTalksAndTimeslots() throws SQLException {
        Talk talk = new Talk("some talk", "this is a cool talk", "this is my topic");
        new TalkDao(dataSource).insert(talk);

        Day day = new Day(LocalDate.of(2010, 2, 10));
        new DayDao(dataSource).insert(day);

        Timeslot timeslot = new Timeslot(LocalTime.of(10,0), LocalTime.of(11, 30), talk);
        getDao().insert(timeslot);


        getDao().connectTimeslotToDay(timeslot.getId(), day.getId());

        List<Timeslot> timeslots = getDao().retrieveByDay(day.getId());

        assertThat(timeslots)
                .contains(timeslot);
    }


    @Test
    public void shouldOnlyContainConnectedTimeslots() throws SQLException {

        Day day = new Day(LocalDate.of(2010, 2, 10));
        new DayDao(dataSource).insert(day);

        Timeslot connected = new Timeslot(LocalTime.of(10,1), LocalTime.of(11, 30));
        Timeslot unconnected = new Timeslot(LocalTime.of(10,1), LocalTime.of(11, 30));

        getDao().insert(connected);
        getDao().insert(unconnected);

        getDao().connectTimeslotToDay(connected.getId(), day.getId());

        List<Timeslot> timeslots = getDao().retrieveByDay(day.getId());

        assertThat(timeslots)
                .contains(connected);
        assertThat(timeslots)
                .doesNotContain(unconnected);
    }


    @Test
    @Override
    public void shouldDelete() throws SQLException {

        Timeslot timeslot = new Timeslot(LocalTime.of(14,0), LocalTime.of(15, 30));
        getDao().insert(timeslot);

        assertThat(getDao().retrieve(timeslot.getId())).isNotNull();

        getDao().delete(timeslot.getId());

        assertThat(getDao().retrieve(timeslot.getId())).isNull();
    }
}