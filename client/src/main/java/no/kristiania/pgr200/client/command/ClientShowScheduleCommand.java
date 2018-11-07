package no.kristiania.pgr200.client.command;


import no.kristiania.pgr200.core.command.ShowScheduleCommand;
import no.kristiania.pgr200.core.http.HttpRequest;
import no.kristiania.pgr200.core.http.HttpResponse;
import no.kristiania.pgr200.core.http.uri.Uri;
import no.kristiania.pgr200.core.model.Conference;
import no.kristiania.pgr200.core.model.Day;
import no.kristiania.pgr200.core.model.Talk;
import no.kristiania.pgr200.core.model.Timeslot;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;


public class ClientShowScheduleCommand extends ShowScheduleCommand implements ClientCommand {


    @Override
    public HttpResponse execute(DataSource dataSource) throws IOException {
        parameters.put("id", id.toString());

        Uri uri = new Uri("/api/showschedule", parameters);
        HttpRequest req = new HttpRequest("localhost", 8080, uri.toString());

        HttpResponse response = req.execute();

        if(checkForError(response)) {
            return response;
        }
        Conference conference = gson.fromJson(response.getBody(), Conference.class);
        if(conference == null){
            System.out.println("The conference you are looking for does not exist");
            return response;
        }
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

        return response;
    }




}
