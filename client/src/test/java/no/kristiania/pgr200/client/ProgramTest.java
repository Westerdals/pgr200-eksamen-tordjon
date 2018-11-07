package no.kristiania.pgr200.client;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import no.kristiania.pgr200.client.command.listing.ClientListConferencesCommand;
import no.kristiania.pgr200.client.command.listing.ClientListDaysCommand;
import no.kristiania.pgr200.client.command.listing.ClientListTalksCommand;
import no.kristiania.pgr200.client.command.listing.ClientListTimeslotsCommand;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.core.model.Day;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.core.model.Timeslot;
import no.kristiania.pgr200.server.HttpServer;
import no.kristiania.pgr200.server.database.Util;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static no.kristiania.pgr200.client.Program.main;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * This class covers all use cases
 * for our client.
 */
public class ProgramTest {

    private static DataSource dataSource;

    private Gson gson = new Gson();


    @BeforeClass
    public static void init() throws IOException {

        HttpServer httpServer = new HttpServer( 8080, "./../test.properties");
        httpServer.start();
        setDatasource();
    }

    @Before
    public void resetDb() {
        main(new String[]{"reset", "db"});
    }

    public static void setDatasource() throws IOException {
        dataSource = Util.createDataSource("./../test.properties");
    }

    @Test
    public void shouldListHelpOnInvalidInput() {
        main(new String[]{
                "some", "invalid", "input"
        });
    }

    @Test
    public void shouldCreateDemoConferenceWithCorrectName() throws IOException {

        main(new String[]{
                "create", "demo"
        });

        HttpResponse response =
                new ClientListConferencesCommand().execute(dataSource);

        Type collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        Collection<Conference> conferences = gson.fromJson(response.getBody(), collectionType);


        conferences.stream()
                .forEach(conference -> {
                    assertThat(conference.getName())
                            .isEqualTo("Blizzcon");

                });
    }

    @Test
    public void shouldPrintSchedule() throws IOException {
        main(new String[]{
                "create", "demo"
        });

        HttpResponse response =
                new ClientListConferencesCommand().execute(dataSource);
        Type collectionType = new TypeToken<List<Conference>>(){}.getType();
        List<Conference> conferences = gson.fromJson(response.getBody(), collectionType);
        Conference conference = conferences.get(0);

        main(new String[]{"show", "schedule", "-id", conference.getId().toString()});
    }

    @Test
    public void shouldInsertTalk() throws IOException {

        Talk talk = new Talk("this is my title", "some description", "topicnamead");
        main(new String[]{
            "insert", "talk", "-title", talk.getTitle(),
            "-description", talk.getDescription(),
            "-topic", talk.getTopicTitle()
        });

        HttpResponse response = new ClientListTalksCommand().execute(dataSource);

        Type collectionType = new TypeToken<Collection<Talk>>(){}.getType();
        Collection<Talk> talks = gson.fromJson(response.getBody(), collectionType);

        assertThat(talks).contains(talk);
    }

    @Test
    public void shouldInsertConference() throws IOException {

        Conference conference = new Conference("olavpraterdagenlangkonferansen2019");
        main(new String[]{
                "insert", "conference", "-name", conference.getName(),

        });

        HttpResponse response = new ClientListConferencesCommand().execute(dataSource);

        Type collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        List<Conference> conferences = gson.fromJson(response.getBody(), collectionType);

        assertThat(conferences.get(0)).isEqualToComparingOnlyGivenFields(conference, "name");
    }


    @Test
    public void shouldInsertDay() throws IOException {

        Day day = new Day(LocalDate.of(2018, 9, 16));

        main(new String[]{
                "insert", "day", "-date", "16.09.2018"
        });

        HttpResponse response = new ClientListDaysCommand().execute(dataSource);

        Type collectionType = new TypeToken<List<Day>>(){}.getType();
        List<Day> days = gson.fromJson(response.getBody(), collectionType);


        assertThat(days.get(0)).isEqualToComparingOnlyGivenFields(day, "date");

    }

    @Test
    public void shouldInsertTimeslot() throws IOException {
        Timeslot timeslot = new Timeslot(LocalTime.of(14,20), LocalTime.of(15,30));

        main(new String[]{
                "insert", "timeslot", "-start", "14:20", "-end", "15:30"
        });

        HttpResponse response = new ClientListTimeslotsCommand().execute(dataSource);

        Type collectionType = new TypeToken<List<Timeslot>>(){}.getType();
        List<Timeslot> timeslots = gson.fromJson(response.getBody(), collectionType);

        Timeslot retrieved = timeslots.get(0);
        assertThat(retrieved)
                .isEqualToComparingOnlyGivenFields(timeslot, "start", "end");

    }



    @Test
    public void shouldDeleteTalk() throws IOException {
        Talk talk = new Talk("My fun talk!", "my fun description", "my amazing topic");

        // insert first
        main(new String[]{
                "insert", "talk", "-title", talk.getTitle(), "-description", talk.getDescription(), "-topic", talk.getTopicTitle()
        });

        HttpResponse response = new ClientListTalksCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Talk>>(){}.getType();
        List<Talk> talks = gson.fromJson(response.getBody(), collectionType);

        assertThat(talks).contains(talk);

        UUID id = talks.get(0).getId();


        main(new String[]{
                "delete", "talk", "-id", id.toString()
        });

        response = new ClientListTalksCommand().execute(dataSource);
        talks = gson.fromJson(response.getBody(), collectionType);

        assertThat(talks).doesNotContain(talk);
    }

    @Test
    public void shouldDeleteConference() throws IOException {

        Conference conference = new Conference("This is an awesome conference!");

        // insert first
        main(new String[]{
                "insert", "conference", "-name", conference.getName()
        });

        HttpResponse response = new ClientListConferencesCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        List<Conference> conferences = gson.fromJson(response.getBody(), collectionType);

        assertThat(conferences.get(0))
                .isEqualToComparingOnlyGivenFields(conference, "name");

        UUID id = conferences.get(0).getId();


        main(new String[]{
                "delete", "conference", "-id", id.toString()
        });

        response = new ClientListConferencesCommand().execute(dataSource);
        conferences = gson.fromJson(response.getBody(), collectionType);

        assertThat(conferences).doesNotContain(conference);
    }

    @Test
    public void shouldDeleteDay() throws IOException {

        Day day = new Day(LocalDate.of(2018, 12, 24));

        // insert first
        main(new String[]{
                "insert", "day", "-date", "24.12.2018"
        });

        HttpResponse response = new ClientListDaysCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Day>>(){}.getType();
        List<Day> days = gson.fromJson(response.getBody(), collectionType);

        assertThat(days.get(0))
                .isEqualToComparingOnlyGivenFields(day, "date");

        UUID id = days.get(0).getId();


        main(new String[]{
                "delete", "day", "-id", id.toString()
        });

        response = new ClientListDaysCommand().execute(dataSource);
        days = gson.fromJson(response.getBody(), collectionType);

        assertThat(days).doesNotContain(day);
    }

    @Test
    public void shouldDeleteTimeslot() throws IOException {

        Timeslot timeslot = new Timeslot(LocalTime.of(11, 00), LocalTime.of(14, 22));

        // insert first
        main(new String[]{
                "insert", "timeslot", "-start", "11:00", "-end", "14:22"
        });

        HttpResponse response = new ClientListTimeslotsCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        List<Timeslot> timeslots = gson.fromJson(response.getBody(), collectionType);

        assertThat(timeslots.get(0))
                .isEqualToComparingOnlyGivenFields(timeslot, "start", "end");


        UUID id = timeslots.get(0).getId();


        main(new String[]{
                "delete", "timeslot", "-id", id.toString()
        });

        response = new ClientListTimeslotsCommand().execute(dataSource);
        timeslots = gson.fromJson(response.getBody(), collectionType);

        assertThat(timeslots).doesNotContain(timeslot);
    }

    @Test
    public void shouldConnectDayWithConference() throws SQLException, IOException {

        Day day = new Day(LocalDate.of(1212, 12, 12));
        Conference conference = new Conference("What a time to run a computer program!");

        // insert conference, day through main
        // connect them through main
        // get conference and confirm that it has list of days
        main(new String[]{
            "insert", "day",
            "-date", "12.12.1212"

        });

        main(new String[]{
            "insert", "conference",
            "-name", conference.getName()
        });

        HttpResponse response = new ClientListDaysCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Day>>(){}.getType();
        List<Day> days = gson.fromJson(response.getBody(), collectionType);

        response = new ClientListConferencesCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        List<Conference> conferences = gson.fromJson(response.getBody(), collectionType);


        Day retrievedDay = days.get(0);
        Conference retrievedConference = conferences.get(0);

        // actually connecting:
        main(new String[]{
                "connect", "day-with-conference",
                "-day", retrievedDay.getId().toString(),
                "-conference", retrievedConference.getId().toString()
        });


        // asserting that the connection is registered and applied to retrieved days
        response = new ClientListConferencesCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        List<Conference> conferencesWithDays = gson.fromJson(response.getBody(), collectionType);

        Conference conferenceWithDays = conferencesWithDays.get(0);

        Day connectedDay = conferenceWithDays.getDays().get(0);

        assertThat(connectedDay.getId())
            .isEqualTo(retrievedDay.getId());

    }


    @Test
    public void shouldConnectTalkWithTimeslot() throws IOException {

        Talk talk = new Talk("Apple and oranges", "A talk about something fruity", "fruits");
        Timeslot timeslot = new Timeslot(LocalTime.of(20, 00), LocalTime.of(23, 20));

        main(new String[]{
                "insert", "talk",
                "-title", talk.getTitle(),
                "-description", talk.getDescription(),
                "-topic", talk.getTopicTitle()

        });

        main(new String[]{
                "insert", "timeslot",
                "-start", "20:00",
                "-end", "23:20"
        });

        HttpResponse response = new ClientListTalksCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Talk>>(){}.getType();
        List<Talk> talks = gson.fromJson(response.getBody(), collectionType);

        response = new ClientListTimeslotsCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        List<Timeslot> timeslots = gson.fromJson(response.getBody(), collectionType);


        Talk retrievedTalk = talks.get(0);
        Timeslot retrievedTimeslot = timeslots.get(0);

        // actually connecting:
        main(new String[]{
                "connect", "talk-with-timeslot",
                "-talk", retrievedTalk.getId().toString(),
                "-timeslot", retrievedTimeslot.getId().toString()
        });


        // asserting that the connection is registered and applied to retrieved talks
        response = new ClientListTimeslotsCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        List<Timeslot> timeslotsWithDays = gson.fromJson(response.getBody(), collectionType);

        Timeslot timeslotWithTalks = timeslotsWithDays.get(0);

        Talk connectedTalk = timeslotWithTalks.getTalk();


        assertThat(connectedTalk.getId())
                .isEqualTo(retrievedTalk.getId());

    }


    @Test
    public void shouldConnectTimeslotWithDay() throws IOException {

        Timeslot timeslot = new Timeslot(LocalTime.of(20, 00), LocalTime.of(23, 20));
        Day day = new Day(LocalDate.of(2020, 12, 12));
        main(new String[]{
                "insert", "timeslot",
                "-start", "20:00",
                "-end", "23:20"
        });
        main(new String[]{
                "insert", "day",
                "-date", "12.12.2020"

        });


        HttpResponse response = new ClientListTimeslotsCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        List<Timeslot> timeslots = gson.fromJson(response.getBody(), collectionType);

        response = new ClientListDaysCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Day>>(){}.getType();
        List<Day> days = gson.fromJson(response.getBody(), collectionType);


        Timeslot retrievedTimeslot = timeslots.get(0);
        Day retrievedDay = days.get(0);

        // actually connecting:
        main(new String[]{
                "connect", "timeslot-with-day",
                "-timeslot", retrievedTimeslot.getId().toString(),
                "-day", retrievedDay.getId().toString()
        });


        // asserting that the connection is registered and applied to retrieved timeslots
        response = new ClientListDaysCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Day>>(){}.getType();
        List<Day> daysWithTimeslots = gson.fromJson(response.getBody(), collectionType);


        Timeslot connectedTimeslot = daysWithTimeslots.get(0).getTimeslots().get(0);


        assertThat(connectedTimeslot.getId())
                .isEqualTo(retrievedTimeslot.getId());

    }


    @Test
    public void shouldUpdateTalk() throws IOException {
        Talk original = new Talk("original title", "some desc", "some topic");
        String newTitle = "Some new and shiny title!";

        main(new String[]{
                "insert", "talk",
                "-title", original.getTitle(),
                "-description", original.getDescription(),
                "-topic", original.getTopicTitle()
        });


        // must retrieve the talk to get the ID that the server uses
        HttpResponse response = new ClientListTalksCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Talk>>(){}.getType();
        List<Talk> talks = gson.fromJson(response.getBody(), collectionType);
        Talk talk = talks.get(0);

        assertThat(talk).isEqualTo(original);

        main(new String[]{
                "update", "talk",
                "-id", talk.getId().toString(),
                "-title", newTitle
        });

        // fetch the updated one
        // must retrieve the talk to get the ID that the server uses
        response = new ClientListTalksCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Talk>>(){}.getType();
        talks = gson.fromJson(response.getBody(), collectionType);
        Talk updated = talks.get(0);

        assertThat(updated.getTitle())
                .isEqualTo(newTitle);
    }

    @Test
    public void shouldUpdateConference() throws IOException {
        Conference original = new Conference("original conference name");
        String newName = "Some new and shiny confernce name!";

        main(new String[]{
                "insert", "conference",
                "-name", original.getName()
        });


        // must retrieve the talk to get the ID that the server uses
        HttpResponse response = new ClientListConferencesCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        List<Conference> conferences = gson.fromJson(response.getBody(), collectionType);
        Conference conference = conferences.get(0);

        assertThat(conference).isEqualToComparingOnlyGivenFields(original, "name");

        main(new String[]{
                "update", "conference",
                "-id", conference.getId().toString(),
                "-name", newName
        });

        // fetch the updated one
        // must retrieve the talk to get the ID that the server uses
        response = new ClientListConferencesCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Conference>>(){}.getType();
        conferences = gson.fromJson(response.getBody(), collectionType);
        Conference updated = conferences.get(0);

        assertThat(updated.getName())
                .isEqualTo(newName);
    }

    @Test
    public void shouldUpdateDay() throws IOException {
        Day original = new Day(LocalDate.of(2000, 1, 1));
        LocalDate newDate = LocalDate.of(2020, 12,12);

        main(new String[]{
                "insert", "day",
                "-date", "01.01.2000"
        });


        // must retrieve the talk to get the ID that the server uses
        HttpResponse response = new ClientListDaysCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Day>>(){}.getType();
        List<Day> days = gson.fromJson(response.getBody(), collectionType);
        Day day = days.get(0);

        assertThat(day).isEqualToComparingOnlyGivenFields(original, "date");

        main(new String[]{
                "update", "day",
                "-id", day.getId().toString(),
                "-date", "12.12.2020"
        });

        // fetch the updated one
        // must retrieve the talk to get the ID that the server uses
        response = new ClientListDaysCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Day>>(){}.getType();
        days = gson.fromJson(response.getBody(), collectionType);
        Day updated = days.get(0);

        assertThat(updated.getDate())
                .isEqualTo(newDate);
    }

    @Test
    public void shouldUpdateTimeslots() throws IOException {
        Timeslot original = new Timeslot(LocalTime.of(10, 00), LocalTime.of(10, 30));
        LocalTime newStart = LocalTime.of(10, 15);

        main(new String[]{
                "insert", "timeslot",
                "-start", "10:00",
                "-end", "10:30"
        });


        // must retrieve the talk to get the ID that the server uses
        HttpResponse response = new ClientListTimeslotsCommand().execute(dataSource);
        Type collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        List<Timeslot> timeslots = gson.fromJson(response.getBody(), collectionType);
        Timeslot timeslot = timeslots.get(0);

        assertThat(timeslot).isEqualToComparingOnlyGivenFields(original, "start", "end");

        main(new String[]{
                "update", "timeslot",
                "-id", timeslot.getId().toString(),
                "-start", "10:15"
        });

        // fetch the updated one
        // must retrieve the talk to get the ID that the server uses
        response = new ClientListTimeslotsCommand().execute(dataSource);
        collectionType = new TypeToken<Collection<Timeslot>>(){}.getType();
        timeslots = gson.fromJson(response.getBody(), collectionType);
        Timeslot updated = timeslots.get(0);

        assertThat(updated.getStart())
                .isEqualTo(newStart);
    }
}
