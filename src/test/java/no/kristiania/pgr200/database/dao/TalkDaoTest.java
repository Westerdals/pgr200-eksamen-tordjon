package no.kristiania.pgr200.database.dao;

import no.kristiania.pgr200.database.Util;
import no.kristiania.pgr200.database.model.Talk;
import no.kristiania.pgr200.database.model.Timeslot;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class TalkDaoTest implements DaoTest<Talk> {


    private TalkDao dao;
    private DataSource dataSource;

    @Override
    public TalkDao getDao() {
        return dao;
    }

    @Before
    @Override
    public void createDao() throws IOException {
        dataSource = Util.createDataSource("test.properties");
        dao = new TalkDao(dataSource);
    }


    @Test
    public void TestInsertingOne() throws SQLException {
        Talk talk = new Talk("Test", "Mer test", "topic title");
        getDao().insert(talk);

        Talk receievedTalk = getDao().retrieve(talk.getId());

        assertThat(talk).isEqualToComparingFieldByFieldRecursively(receievedTalk);
    }

    @Override
    @Test
    public void testInsertingSeveral() throws SQLException {
        Talk[] talks = new Talk[]{
                new Talk("first title", "first description", "Java"),
                new Talk("second title", "second description", "Rust"),
                new Talk("third title", "third description", "Java")
        };

        for(Talk talk : talks) {
            getDao().insert(talk);
        }

        for (Talk talk : talks) {
            Talk received = getDao().retrieve(talk.getId());

            assertThat(received).isEqualToComparingFieldByFieldRecursively(talk);
        }
    }



    @Test
    public void IDShouldBePrimaryKey() throws SQLException {
        Talk talk = new Talk("title", "desc", "topic");
        getDao().insert(talk);


        assertThatExceptionOfType(SQLException.class).isThrownBy(() -> {
            getDao().insert(talk);
        });
    }

    @Test
    public void assertGettingAll() throws SQLException {

        Talk[] talks = new Talk[]{
                new Talk("second", "desc", "topic"),
                new Talk("third", "desc", "topic"),
                new Talk("fourth", "desc", "topic"),
                new Talk("fifth", "desc", "topic"),
                new Talk("sixth", "desc", "topic")
        };

        for(Talk talk : talks){
            getDao().insert(talk);
        }



        assertThat(getDao().retrieveAll())
                .asList()
                .contains(talks);
    }

    @Test
    public void shouldUpdate() throws SQLException {
        Talk talk = new Talk("a", "b", "c");
        getDao().insert(talk);

        Talk updated = new Talk(talk.getId(), "d", "e", "f");
        getDao().update(updated);

        Talk received = getDao().retrieve(talk.getId());
        assertThat(received).isEqualToComparingFieldByFieldRecursively(updated);
    }

    @Test
    public void shouldDelete() throws SQLException {
        Talk talk = new Talk("remove", "me", "now");

        getDao().insert(talk);

        assertThat(getDao().retrieve(talk.getId())).isNotNull();

        getDao().delete(talk.getId());

        assertThat(getDao().retrieve(talk.getId())).isNull();
    }


    @Test
    public void shouldRetrieveTalkByTimeslot() throws SQLException {
        Talk talk = new Talk("some talk", "this is a cool talk", "this is my topic");
        getDao().insert(talk);

        Timeslot timeslot = new Timeslot(LocalTime.MAX, LocalTime.MIN);
        new TimeslotDao(dataSource).insert(timeslot);


        new TimeslotDao(dataSource).connectTalkToTimeslot(talk.getId(), timeslot.getId());

        List<Talk> talks = getDao().retrieveByTimeslot(timeslot.getId());

        assertThat(talks)
                .contains(talk);
    }



    @Test
    public void shouldOnlyContainConnectedTimeslots() throws SQLException {
        Talk connected = new Talk("some talk", "this is a cool talk", "this is my topic");
        Talk unconnected = new Talk("some other talk", "this is a cool talk", "this is my topic");

        getDao().insert(connected);
        getDao().insert(unconnected);

        Timeslot timeslot = new Timeslot(LocalTime.of(10, 10 ,10), LocalTime.of(11, 10, 10));
        new TimeslotDao(dataSource).insert(timeslot);


        new TimeslotDao(dataSource).connectTalkToTimeslot(connected.getId(), timeslot.getId());

        List<Talk> talks = getDao().retrieveByTimeslot(timeslot.getId());

        assertThat(talks)
                .contains(connected);

        assertThat(talks)
                .doesNotContain(unconnected);
    }

}