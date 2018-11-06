package no.kristiania.pgr200.server;


import com.google.gson.Gson;
import javafx.beans.binding.BooleanExpression;
import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.core.model.Day;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.core.model.Timeslot;
import no.kristiania.pgr200.server.database.Util;
import no.kristiania.pgr200.server.database.dao.ConferenceDao;
import no.kristiania.pgr200.server.database.dao.DayDao;
import no.kristiania.pgr200.server.database.dao.TalkDao;
import no.kristiania.pgr200.server.database.dao.TimeslotDao;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Super;
import org.junit.*;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

public class ServerTest {

    private static DataSource dataSource;
    Gson gson = new Gson();

    static ConferenceDao conferenceDao = new ConferenceDao(dataSource);
    static TalkDao talkDao;
    static TimeslotDao timeslotDao;
    static DayDao dayDao;

    int port = 8080;
    String hostname = "localhost";


    public static void getDataSource() throws IOException {
        dataSource = Util.createDataSource("./../test.properties");
    }

    @BeforeClass
    public static void init() throws IOException {
        getDataSource();
        HttpServer httpServer = new HttpServer( 8080, "./../test.properties");
        httpServer.start();

        talkDao = new TalkDao(dataSource);
        conferenceDao = new ConferenceDao(dataSource);
        timeslotDao = new TimeslotDao(dataSource);
        dayDao = new DayDao(dataSource);


    }
    @Before
    public void resetDatabase() throws IOException {
        Uri resetdburi = new Uri("/api/resetdb");
        HttpRequest resetDbRequest = new HttpRequest(hostname, port, resetdburi.toString());
        resetDbRequest.execute();

        getDataSource(); //"refresh" the dataSource after cleaning flyway.
    }
    @AfterClass
    public static void teatDown(){

        //kill server?
    }




    @Test
    public void shouldCreateDemoConferenceAndDoProperDatabaseModifications() throws SQLException, IOException {


        HttpRequest createDemoConferenceRequest = new HttpRequest(hostname, port, new Uri("/api/createdemo").toString());
        HttpResponse response = createDemoConferenceRequest.execute();

        assertThat(response.getStatusCode()).isEqualTo(200);

        ConferenceDao conferenceDao = new ConferenceDao(dataSource);
        TimeslotDao timeslotDao = new TimeslotDao(dataSource);


        assertThat(conferenceDao.retrieveAll().size()).isEqualTo(1);
        assertThat(dayDao.retrieveAll().size()).isEqualTo(2);
        assertThat(timeslotDao.retrieveAll().size()).isEqualTo(4);
        assertThat(talkDao.retrieveAll().size()).isEqualTo(4);
    }

    @Test
    public void shouldShowSchedule() throws IOException, SQLException {

        HttpRequest createDemoConferenceRequest = new HttpRequest(hostname, port, new Uri("/api/createdemo").toString());
        HttpResponse createDemoConferenceResponse = createDemoConferenceRequest.execute();

        Conference conference = conferenceDao.retrieveAll().get(0);
        //String conferencejson = gson.toJson(conference); ?


        HttpRequest showScheduleRequest = new HttpRequest(hostname, port,
                new Uri("/api/showschedule?id=" + conference.getId().toString()).toString());
        HttpResponse showScheduleResponse = showScheduleRequest.execute();


        assertEquals(showScheduleResponse.getBody(), createDemoConferenceResponse.getBody());

    }



   @Test
    public void shouldListTalks() throws IOException, SQLException {
        Talk talk = new Talk("en talk", "med description", "kjedelig topic");
        Talk otherTalk = new Talk("en talk", "med description", "kjedelig topic");
        talkDao.insert(talk);
        talkDao.insert(otherTalk);

        List<Talk> talks = talkDao.retrieveAll();
        String talksJson = gson.toJson(talks);
        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/list/talks").toString());
        HttpResponse response = request.execute();

        assertEquals(talksJson, response.getBody());
        assertEquals(200,response.getStatusCode());

    }


    @Test
    public void shouldListDays() throws IOException, SQLException {

        Day day = new Day(LocalDate.of(2019, 11, 19));
        Day otherDay = new Day(LocalDate.of(2020, 9, 19));
        dayDao.insert(day);
        dayDao.insert(otherDay);


        List<Day> days = dayDao.retrieveAll();
        String json = gson.toJson(days);

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/list/days").toString());
        HttpResponse response = request.execute();

        assertEquals(json, response.getBody());
        assertEquals(200,response.getStatusCode());

    }



    @Test
    public void shouldListConferences() throws IOException, SQLException {
        Conference conference = new Conference("Blizzcon");
        Conference otherConference = new Conference("TwitchCOn");
        conferenceDao.insert(conference);
        conferenceDao.insert(otherConference);

        List<Conference> conferences = conferenceDao.retrieveAll();

        String json = gson.toJson(conferences);

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/list/conferences").toString());
        HttpResponse response = request.execute();

        assertEquals(json, response.getBody());
        assertEquals(200,response.getStatusCode());

    }

    @Test
    public void shouldListTimeslots() throws IOException, SQLException {
        Timeslot timeslot = new Timeslot(LocalTime.of(10,30,0), LocalTime.of(12,0,0));
        Timeslot otherTimeslot = new Timeslot(LocalTime.of(13,0,0), LocalTime.of(14,30,0));
        timeslotDao.insert(timeslot);
        timeslotDao.insert(otherTimeslot);
        List<Timeslot> timeslots = timeslotDao.retrieveAll();
        String json = gson.toJson(timeslots);

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/list/timeslots").toString());
        HttpResponse response = request.execute();

        assertEquals(json, response.getBody());
        assertEquals(200,response.getStatusCode());

    }

    @Test
    public void TestTalkCommands() throws SQLException, IOException {

        //----INSERT----//
        Talk talk = new Talk("testtalk", "med desc", "kjedelig topic");
        HashMap<String, String> params = new HashMap<>();
        params.put("title", talk.getTitle());
        params.put("description", talk.getDescription());
        params.put("topic", talk.getTopicTitle());

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/insert/talk", params).toString());
        HttpResponse response = request.execute();

        Talk retrievedTalk = gson.fromJson(response.getBody(), Talk.class);

        assertThat(retrievedTalk).isEqualToComparingOnlyGivenFields(talk, "title", "topicTitle", "description");
        assertEquals(200,response.getStatusCode());

        talk = retrievedTalk;


        //----UPDATE---//
        Talk updatedTalk = new Talk(talk.getId(), talk.getTitle(), talk.getDescription(), "updated topic");
        params = new HashMap<>();
        params.put("id", updatedTalk.getId().toString());
        params.put("title", updatedTalk.getTitle());
        params.put("description", updatedTalk.getDescription());
        params.put("topic", updatedTalk.getTopicTitle());

        request = new HttpRequest(hostname, port,
                new Uri("/api/update/talk", params).toString());
        response = request.execute();

        retrievedTalk = gson.fromJson(response.getBody(), Talk.class);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(retrievedTalk).isEqualToComparingFieldByField(updatedTalk);
        assertThat(retrievedTalk.toString()).isNotEqualTo(talk.toString());

        //----DELETE----/
        request = new HttpRequest(hostname, port,
                new Uri("/api/delete/talk?id=" + retrievedTalk.getId()).toString());
        response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().equals(gson.toJson(retrievedTalk.getId())));

    }



    @Test
    public void TestConferenceCommands() throws SQLException, IOException {

        //----INSERT----//
        Conference conference = new Conference("Testconference");
        HashMap<String, String> params = new HashMap<>();
        params.put("name", conference.getName());

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/insert/conference", params).toString());
        HttpResponse response = request.execute();

        Conference retrievedConference = gson.fromJson(response.getBody(), Conference.class);

        assertThat(retrievedConference).isEqualToComparingOnlyGivenFields(conference, "name");
        assertEquals(200,response.getStatusCode());

        conference = retrievedConference;


        //----UPDATE---//
        Conference updatedConference = new Conference(conference.getId(), "TestconferenceNewName");
        params = new HashMap<>();
        params.put("id", updatedConference.getId().toString());
        params.put("name", updatedConference.getName());

        request = new HttpRequest(hostname, port,
                new Uri("/api/update/conference", params).toString());
        response = request.execute();

        retrievedConference = gson.fromJson(response.getBody(), Conference.class);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(retrievedConference).isEqualToComparingFieldByField(updatedConference);
        assertThat(retrievedConference.toString()).isNotEqualTo(conference.toString());

        //----DELETE----/
        request = new HttpRequest(hostname, port,
                new Uri("/api/delete/conference?id=" + retrievedConference.getId()).toString());
        response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().equals(gson.toJson(retrievedConference.getId())));

    }


    @Test

    public void TestDayCommands() throws SQLException, IOException {

        //----INSERT----//
        Day day = new Day(LocalDate.of(2019,11,14));
        HashMap<String, String> params = new HashMap<>();
        params.put("date", day.getDate().format(DateTimeFormatter.ofPattern("d.MM.yyyy")));


        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/insert/day", params).toString());
        HttpResponse response = request.execute();

        Day retrievedDay = gson.fromJson(response.getBody(), Day.class);

        assertThat(retrievedDay).isEqualToComparingOnlyGivenFields(day, "date");
        assertEquals(200,response.getStatusCode());

        day = retrievedDay;


        //----UPDATE---//
        Day updatedDay = new Day(day.getId(), LocalDate.of(2019,10,2));
        params = new HashMap<>();
        params.put("id", updatedDay.getId().toString());
        params.put("date", updatedDay.getDate().format(DateTimeFormatter.ofPattern("d.MM.yyyy")));

        request = new HttpRequest(hostname, port,
                new Uri("/api/update/day", params).toString());
        response = request.execute();

        retrievedDay = gson.fromJson(response.getBody(), Day.class);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(retrievedDay).isEqualToComparingFieldByField(updatedDay);
        assertThat(retrievedDay.toString()).isNotEqualTo(day.toString());

        //----DELETE----/
        request = new HttpRequest(hostname, port,
                new Uri("/api/delete/day?id=" + retrievedDay.getId()).toString());
        response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().equals(gson.toJson(retrievedDay.getId())));

    }



    @Test
    public void TestTimeslotCommands() throws SQLException, IOException {


        //----INSERT----//
        Timeslot timeslot = new Timeslot(LocalTime.of(12,30,0), LocalTime.of(13,45,0));
        HashMap<String, String> params = new HashMap<>();
        params.put("start", timeslot.getStart().toString());
        params.put("end", timeslot.getEnd().toString());

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/insert/timeslot", params).toString());
        HttpResponse response = request.execute();

        Timeslot retrievedTimeslot = gson.fromJson(response.getBody(), Timeslot.class);

        assertThat(retrievedTimeslot).isEqualToComparingOnlyGivenFields(timeslot, "start", "end");
        assertEquals(200,response.getStatusCode());

        timeslot = retrievedTimeslot;


        //----UPDATE---//
        Timeslot updatedTimeslot = new Timeslot(timeslot.getId(), LocalTime.of(13,10,0), timeslot.getEnd());
        params = new HashMap<>();
        params.put("id", updatedTimeslot.getId().toString());
        params.put("start", updatedTimeslot.getStart().toString());

        request = new HttpRequest(hostname, port,
                new Uri("/api/update/timeslot", params).toString());
        response = request.execute();

        retrievedTimeslot = gson.fromJson(response.getBody(), Timeslot.class);

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(retrievedTimeslot).isEqualToComparingFieldByField(updatedTimeslot);
        assertThat(retrievedTimeslot.toString()).isNotEqualTo(timeslot.toString());

        //----DELETE----/
        request = new HttpRequest(hostname, port,
                new Uri("/api/delete/timeslot?id=" + retrievedTimeslot.getId()).toString());
        response = request.execute();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().equals(gson.toJson(retrievedTimeslot.getId())));





    }







    @Test
    public void testResetingDatabase() throws SQLException, IOException {

        Talk talk = new Talk("talkname", "talkdescription", "topictittel");
        talkDao.insert(talk);

        List<Talk> talks = talkDao.retrieveAll();
        assertThat(talks).asList().contains(talk);

        HttpRequest request = new HttpRequest(hostname, port,
                new Uri("/api/resetdb").toString());
        HttpResponse response = request.execute();

        List<Talk> retrievedEmpty = talkDao.retrieveAll();
        assertThat(retrievedEmpty)
                .asList()
                .isEmpty();
    }








/*

    @Test
    public void shouldConnectDayToConference() throws IOException, SQLException {
        main(new String[]{"help"});
        ConferenceDao conferenceDao = new ConferenceDao(dataSource);
        DayDao dayDao = new DayDao(dataSource);

        Conference conference = new Conference("Blizzcon");
        Day day = new Day(LocalDate.of(2019, 9, 19));
        Day day2 = new Day(LocalDate.of(2019, 9, 20));
        Day day3 = new Day(LocalDate.of(2019, 9, 24));

        conferenceDao.insert(conference);
        dayDao.insert(day);
        dayDao.insert(day2);
        dayDao.insert(day3);

        String[] connectArgs = {"connect", "day-with-conference",
                                "-day", day.getId().toString(),
                                "-conference", conference.getId().toString()};

        main(connectArgs);
        List<Day> connectedDays = dayDao.retrieveByConference(conference.getId());

        assertThat(connectedDays).asList().contains(day);
        assertThat(connectedDays).asList().doesNotContain(day2);
        assertThat(connectedDays).asList().doesNotContain(day3);
    }


    @Test
    public void shouldConnectTimeslotToDay() throws IOException, SQLException {
        main(new String[]{"help"});
        TimeslotDao timeslotDao = new TimeslotDao(dataSource);
        DayDao dayDao = new DayDao(dataSource);

        Timeslot timeslot = new Timeslot(LocalTime.of(10,30), LocalTime.of(12,15));
        Timeslot timeslot2 = new Timeslot(LocalTime.of(12,30), LocalTime.of(13,15));
        Day day = new Day(LocalDate.of(2019, 9, 19));


        timeslotDao.insert(timeslot);
        timeslotDao.insert(timeslot2);
        dayDao.insert(day);


        timeslot = timeslotDao.retrieve(timeslot.getId());
        day = dayDao.retrieve(day.getId());


        String[] connectArgs = {"connect", "timeslot-with-day",
                "-timeslot", timeslot.getId().toString(),
                "-day", day.getId().toString(),
        };

        main(connectArgs);
        List<Timeslot> connectedTimeslots = timeslotDao.retrieveByDay(day.getId());

        assertThat(connectedTimeslots).asList().contains(timeslot);
        assertThat(connectedTimeslots).asList().doesNotContain(timeslot2);
    }


    @Test
    public void shouldConnectTalkToTimeslot() throws IOException, SQLException {
        TimeslotDao timeslotDao = new TimeslotDao(dataSource);
        TalkDao talkDao = new TalkDao(dataSource);

        Timeslot timeslot = new Timeslot(LocalTime.of(8,40), LocalTime.of(9,55));
        Talk talk = new Talk("Testtalk", "talkdesc", "...");
        talkDao.insert(talk);
        timeslotDao.insert(timeslot);

        String[] connectArgs = {"connect", "talk-with-timeslot",
                "-talk", talk.getId().toString(),
                "-timeslot", timeslot.getId().toString()};

        main(connectArgs);
        List<Talk> connectedTalks = talkDao.retrieveByTimeslot(timeslot.getId());

        assertThat(connectedTalks).asList().contains(talk);

    }
    */
}
