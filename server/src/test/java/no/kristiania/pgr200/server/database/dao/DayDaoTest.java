package no.kristiania.pgr200.server.database.dao;


import no.kristiania.pgr200.server.database.Util;
import model.Conference;
import model.Day;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class DayDaoTest implements DaoTest<Day> {

    private DayDao dao;
    private DataSource dataSource;

    @Before
    @Override
    public void createDao() throws IOException {
        dataSource = Util.createDataSource("test.properties");
        dao = new DayDao(dataSource);
    }

    @Override
    public DayDao getDao() {
        return dao;
    }


    @Test
    public void testInsertingOne() throws SQLException {

        Day day = new Day(LocalDate.now());

        getDao().insert(day);

        Day receievedDay = getDao().retrieve(day.getId());

        assertThat(receievedDay).isEqualToComparingFieldByFieldRecursively(day);
    }


    @Test
    public void testInsertingSeveral() throws SQLException {

        Day[] days = new Day[]{
                new Day(LocalDate.of(2018,12, 24)),
                new Day(LocalDate.of(2016, 8, 10)),
                new Day(LocalDate.of(2028, 10, 22)),
                new Day(LocalDate.of(2000, 11, 9))
        };


        for (Day day : days) {
            getDao().insert(day);
        }

        for (int i = 0; i < days.length; i++) {
            Day day = days[i];
            Day  retrieved = getDao().retrieve(day.getId());

            assertThat(retrieved).isEqualToComparingFieldByFieldRecursively(day);
        }

    }

    @Test
    public void IDShouldBePrimaryKey() throws SQLException {
        Day day = new Day(LocalDate.of(2000, 1, 1));

        getDao().insert(day);

        assertThatExceptionOfType(SQLException.class).isThrownBy(() -> {
            getDao().insert(day);
        });
    }

    @Test
    public void assertGettingAll() throws SQLException {

        Day[] days = new Day[]{
                new Day(LocalDate.of(2016, 8, 10)),
                new Day(LocalDate.of(2028, 10, 22)),
                new Day(LocalDate.of(2000, 11, 9))
        };

        for(Day day : days){
            getDao().insert(day);
        }

        List<Day> retrieved = getDao().retrieveAll();

        assertThat(retrieved).contains(days);
    }

    @Test
    public void shouldUpdate() throws SQLException {
        Day day = new Day(LocalDate.of(1, 2, 3));
        getDao().insert(day);

        Day updated = new Day(day.getId(), LocalDate.of(4,5,6));
        getDao().update(updated);

        Day received = getDao().retrieve(day.getId());
        assertThat(received).isEqualToComparingFieldByFieldRecursively(updated);
    }

    @Test
    public void shouldDelete() throws SQLException {
        Day day = new Day(LocalDate.of(1 ,2, 3));
        getDao().insert(day);

        assertThat(getDao().retrieve(day.getId())).isNotNull();

        getDao().delete(day.getId());
        assertThat(getDao().retrieve(day.getId())).isNull();
    }

    @Test
    public void shouldConnectDaysAndConferences() throws SQLException {
        Conference conference = new Conference("Java conference!");
        Day firstDay = new Day(LocalDate.of(2010, 12, 3));
        Day secondDay = new Day(LocalDate.of(2010, 12, 4));


        // foreign keys has to be present in referenced table
        new ConferenceDao(dataSource).insert(conference);
        getDao().insert(firstDay);
        getDao().insert(secondDay);

        getDao().connectDayToConference(conference.getId(), firstDay.getId());
        getDao().connectDayToConference(conference.getId(), secondDay.getId());

        List<Day> days = getDao().retrieveByConference(conference.getId());
        assertThat(days).contains(firstDay, secondDay);
    }

    @Test
    public void shouldOnlyContainConnectedDays() throws SQLException {
        Conference conference = new Conference("Java conference!");
        Day day = new Day(LocalDate.of(2010, 12, 3));
        Day otherday = new Day(LocalDate.of(2010, 12, 4));


        // foreign keys has to be present in referenced table
        new ConferenceDao(dataSource).insert(conference);
        getDao().insert(day);
        getDao().insert(otherday);

        getDao().connectDayToConference(conference.getId(), day.getId());


        List<Day> days = getDao().retrieveByConference(conference.getId());
        assertThat(days).contains(day);
        assertThat(days).doesNotContain(otherday);
    }

}