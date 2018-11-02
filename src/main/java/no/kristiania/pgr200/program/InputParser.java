package no.kristiania.pgr200.program;

import no.kristiania.pgr200.http.uri.Path;
import no.kristiania.pgr200.program.command.*;
import no.kristiania.pgr200.program.command.connecting.ConnectDayWithConference;
import no.kristiania.pgr200.program.command.connecting.ConnectTalkWithTimeslotCommand;
import no.kristiania.pgr200.program.command.connecting.ConnectTimeslotWithDayCommand;
import no.kristiania.pgr200.program.command.deletion.DeleteConferenceCommand;
import no.kristiania.pgr200.program.command.deletion.DeleteDayCommand;
import no.kristiania.pgr200.program.command.deletion.DeleteTalkCommand;
import no.kristiania.pgr200.program.command.deletion.DeleteTimeslotCommand;
import no.kristiania.pgr200.program.command.insertion.InsertConferenceCommand;
import no.kristiania.pgr200.program.command.insertion.InsertDayCommand;
import no.kristiania.pgr200.program.command.insertion.InsertTalkCommand;
import no.kristiania.pgr200.program.command.insertion.InsertTimeslotCommand;
import no.kristiania.pgr200.program.command.listing.ListConferencesCommand;
import no.kristiania.pgr200.program.command.listing.ListDaysCommand;
import no.kristiania.pgr200.program.command.listing.ListTalksCommand;
import no.kristiania.pgr200.program.command.listing.ListTimeslotsCommand;
import no.kristiania.pgr200.program.command.updating.UpdateConferenceCommand;
import no.kristiania.pgr200.program.command.updating.UpdateDayCommand;
import no.kristiania.pgr200.program.command.updating.UpdateTalkCommand;
import no.kristiania.pgr200.program.command.updating.UpdateTimeslotCommand;

import java.util.HashMap;
import java.util.Map;

public class InputParser {



    public static Command decodeInput(Path path, HashMap<String, String> parameters) throws IllegalArgumentException {


       /* if (strings.length < 2) {
            return new HelpCommand().build(strings);
        }*/


        HashMap<String, Class<? extends Command>> map = new HashMap<>();
        populateCommandMap(map);


        Class<? extends Command> command = map.get(path);

        if (command == null) {
            return new HelpCommand();
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

        map.put("/insert/talk", InsertTalkCommand.class);

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
        // resetting the database
        map.put("reset db", ResetDBCommand.class);*/



    }

}
