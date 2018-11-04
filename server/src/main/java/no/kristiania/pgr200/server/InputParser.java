package no.kristiania.pgr200.server;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.command.InvalidInputCommand;

import java.util.HashMap;
import java.util.Map;

public class InputParser {




    public static Command decodeInput(Map<String, Class<? extends Command>> map,String type, HashMap<String, String> parameters) throws IllegalArgumentException {

        /*HashMap<String, Class<? extends Command>> map = new HashMap<>();
        populateCommandMap(map);*/


        //client
       /* map = KEY: args[0] + " " + args[1], VALUE; ClientInserTalkCommand.class;
        map = "insert talk", ClientInsertTalkCommand.class;
        InputParser.setMap(map);

        //server
        map = KEY: "/insert/talk", VALUE; ClientInserTalkCommand.class;
        InputParser.setMap(map);*/

        Class<? extends Command> command = map.get(type);

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

  /*  private static void populateCommandMap(Map<String, Class<? extends Command>> map) {

        map.put("/api/insert/talk", ServerInsertTalkCommand.class);
        map.put("/api/insert/day", ServerInsertDayCommand.class);
        map.put("/api/list/talks", ServerListTalksCommand.class);
        map.put("/api/insert/conference", ServerInsertConferenceCommand.class);
        map.put("/api/insert/democonference", ServerCreateDemoConferenceCommand.class);
        map.put("/api/showschedule", ServerShowScheduleCommand.class);


        // talk
       /map.put("insert talk", ServerInsertTalkCommand.class);
        map.put("list talks", ServerListTalksCommand.class);
        map.put("delete talk", ServerDeleteTalkCommand.class);
        map.put("update talk", ServerUpdateTalkCommand.class);

        // day
        map.put("insert day", ServerInsertDayCommand.class);
        map.put("list days", ServerListDaysCommand.class);
        map.put("delete day", DeleteDayCommand.class);
        map.put("update day", ServerUpdateDayCommand.class);

        // timeslot
        map.put("insert timeslot", ServerInsertTimeslotCommand.class);
        map.put("list timeslots", ServerListTimeslotsCommand.class);
        map.put("delete timeslot", DeleteTimeslotCommand.class);
        map.put("update timeslot", ServerUpdateTimeslotCommand.class);

        // day
        map.put("insert conference", ServerInsertConferenceCommand.class);
        map.put("list conferences", ServerListConferencesCommand.class);
        map.put("delete conference", DeleteConferenceCommand.class);
        map.put("update conference", ServerUpdateConferenceCommand.class);

        // connecting
        map.put("connect day-with-conference", ConnectDayWithConference.class);
        map.put("connect talk-with-timeslot", ConnectTalkWithTimeslotCommand.class);
        map.put("connect timeslot-with-day", ServerConnectTimeslotWithDayCommand.class);

        // TODO: SE OVER ISSE
        //map.put("list talk-with-timeslot", ConnectTalkWithTimeslotCommand.class);
        //map.put("remove timeslot-with-day", ServerConnectTimeslotWithDayCommand.class);

        //show conference program
        map.put("show schedule", ServerShowScheduleCommand.class);
        //create demo conference
        map.put("create demo", ServerCreateDemoConferenceCommand.class);
        // resetting the no.kristiania.pgr200.server.database
        map.put("reset db", ServerResetDBCommand.class);/



    }
*/
}
