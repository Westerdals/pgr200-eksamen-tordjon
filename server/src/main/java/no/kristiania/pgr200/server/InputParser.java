package no.kristiania.pgr200.server;

import no.kristiania.pgr200.core.http.uri.Path;
import no.kristiania.pgr200.server.command.Command;
import no.kristiania.pgr200.server.command.CreateDemoConferenceCommand;
import no.kristiania.pgr200.server.command.InvalidInputCommand;
import no.kristiania.pgr200.server.command.ShowScheduleCommand;
import no.kristiania.pgr200.server.command.insertion.InsertConferenceCommand;
import no.kristiania.pgr200.server.command.insertion.InsertDayCommand;
import no.kristiania.pgr200.server.command.insertion.InsertTalkCommand;
import no.kristiania.pgr200.server.command.listing.ListTalksCommand;

import java.util.HashMap;
import java.util.Map;

public class InputParser {



    public static Command decodeInput(Path path, HashMap<String, String> parameters) throws IllegalArgumentException {


        HashMap<String, Class<? extends Command>> map = new HashMap<>();
        populateCommandMap(map);


        Class<? extends Command> command = map.get(path.toString());

        if (command == null) {
            return new InvalidInputCommand();
        }

        try {

            return command.newInstance().build(parameters);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void populateCommandMap(Map<String, Class<? extends Command>> map) {

        map.put("/api/insert/talk", InsertTalkCommand.class);
        map.put("/api/insert/day", InsertDayCommand.class);
        map.put("/api/list/talks", ListTalksCommand.class);
        map.put("/api/insert/conference", InsertConferenceCommand.class);
        map.put("/api/insert/democonference", CreateDemoConferenceCommand.class);
        map.put("/api/showschedule", ShowScheduleCommand.class);


        // talk
       /* map.put("insert talk", InsertTalkCommand.class);
        map.put("list talks", ListTalksCommand.class);
        map.put("delete talk", DeleteTalkCommand.class);
        map.put("update talk", UpdateTalkCommand.class);

        // day
        map.put("insert day", InsertDayCommand.class);
        map.put("list days", ListDaysCommand.class);
        map.put("delete day", DeleteDayCommand.class);
        map.put("update day", UpdateDayCommand.class);

        // timeslot
        map.put("insert timeslot", InsertTimeslotCommand.class);
        map.put("list timeslots", ListTimeslotsCommand.class);
        map.put("delete timeslot", DeleteTimeslotCommand.class);
        map.put("update timeslot", UpdateTimeslotCommand.class);

        // day
        map.put("insert conference", InsertConferenceCommand.class);
        map.put("list conferences", ListConferencesCommand.class);
        map.put("delete conference", DeleteConferenceCommand.class);
        map.put("update conference", UpdateConferenceCommand.class);

        // connecting
        map.put("connect day-with-conference", ConnectDayWithConference.class);
        map.put("connect talk-with-timeslot", ConnectTalkWithTimeslotCommand.class);
        map.put("connect timeslot-with-day", ConnectTimeslotWithDayCommand.class);

        // TODO: SE OVER ISSE
        //map.put("list talk-with-timeslot", ConnectTalkWithTimeslotCommand.class);
        //map.put("remove timeslot-with-day", ConnectTimeslotWithDayCommand.class);

        //show conference program
        map.put("show schedule", ShowScheduleCommand.class);
        //create demo conference
        map.put("create demo", CreateDemoConferenceCommand.class);
        // resetting the no.kristiania.pgr200.server.database
        map.put("reset db", ResetDBCommand.class);*/



    }

}
