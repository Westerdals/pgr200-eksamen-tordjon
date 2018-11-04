package no.kristiania.pgr200.server.command.connecting;

import model.Conference;
import model.Day;
import model.Talk;
import model.Timeslot;
import no.kristiania.pgr200.server.ServerResponse;
import no.kristiania.pgr200.server.database.Util;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.DayDao;
import no.kristiania.pgr200.server.database.dao.TalkDao;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConnectingCommandResponseTest {

    private ConnectingCommand command;
    private DataSource dataSource;





    @Before
    public void pickRandomConnectingCommand() throws SQLException {

        // SETUP - connecting things in databse

        DayDao dayDao               = new DayDao(dataSource);
        ConferenceDao conferenceDao = new ConferenceDao(dataSource);
        TalkDao talkDao             = new TalkDao(dataSource);
        TimeslotDao timeslotDao     = new TimeslotDao(dataSource);

        Day day = new Day(LocalDate.of(2010, 10, 1));
        Conference conference = new Conference("amazing conference");
        Talk talk = new Talk("title", "description", "topic");
        Timeslot timeslot = new Timeslot(LocalTime.of(14, 30), LocalTime.of(15, 30));

        dayDao.insert(day);
        conferenceDao.insert(conference);
        talkDao.insert(talk);
        timeslotDao.insert(timeslot);

        HashMap<String, String> arguments = new HashMap<>();

        // Getting the command:
        ConnectingCommand[] commands = {
                new ConnectDayWithConference(),
                new ConnectTalkWithTimeslotCommand(),
                new ConnectTimeslotWithDayCommand()
        };

        int index = new Random().nextInt(commands.length);

        command = commands[index];
    }

    @Before
    public void setDataSource() throws IOException {
        dataSource = dataSource = Util.createDataSource("test.properties");
    }

    @Test
    public void testStatus() throws SQLException {
        ServerResponse response = command.execute(dataSource);

        assertThat(response.getStatus().getCode()).isEqualTo(201);
    }

    @Test
    public void testContentLength() throws SQLException {
        ServerResponse response = command.execute(dataSource);

        assertThat(response.getHeaders().get("Content-Length"))
                .isEqualTo("0");
    }

}