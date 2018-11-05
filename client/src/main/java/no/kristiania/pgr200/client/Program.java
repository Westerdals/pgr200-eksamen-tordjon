package no.kristiania.pgr200.client;


import no.kristiania.pgr200.client.command.ClientCreateDemoConferenceCommand;
import no.kristiania.pgr200.client.command.ClientResetDBCommand;
import no.kristiania.pgr200.client.command.ClientShowScheduleCommand;
import no.kristiania.pgr200.client.command.connecting.ClientConnectTalkWithTimeslotCommand;
import no.kristiania.pgr200.client.command.connecting.ClientConnectTimeslotWithDayCommand;
import no.kristiania.pgr200.client.command.deletion.ClientDeleteConferenceCommand;
import no.kristiania.pgr200.client.command.deletion.ClientDeleteDayCommand;
import no.kristiania.pgr200.client.command.deletion.ClientDeleteTalkCommand;
import no.kristiania.pgr200.client.command.deletion.ClientDeleteTimeslotCommand;
import no.kristiania.pgr200.client.command.insertion.ClientInsertConferenceCommand;
import no.kristiania.pgr200.client.command.insertion.ClientInsertDayCommand;
import no.kristiania.pgr200.client.command.insertion.ClientInsertTalkCommand;
import no.kristiania.pgr200.client.command.insertion.ClientInsertTimeslotCommand;
import no.kristiania.pgr200.client.command.listing.ClientListConferencesCommand;
import no.kristiania.pgr200.client.command.listing.ClientListDaysCommand;
import no.kristiania.pgr200.client.command.listing.ClientListTalksCommand;
import no.kristiania.pgr200.client.command.listing.ClientListTimeslotsCommand;
import no.kristiania.pgr200.client.command.updating.ClientUpdateConferenceCommand;
import no.kristiania.pgr200.client.command.updating.ClientUpdateDayCommand;
import no.kristiania.pgr200.client.command.updating.ClientUpdateTalkCommand;
import no.kristiania.pgr200.client.command.updating.ClientUpdateTimeslotCommand;

import no.kristiania.pgr200.core.ArgumentParser;
import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.server.command.connecting.ClientConnectDayWithConference;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Program {


    static DataSource dataSource;
    static String propertiesFileName = "innlevering.properties";

    public static void main(String[] args) {


        if(args.length < 2) //testing.
            return;


        HashMap<String, String> parameters = ArgumentParser.getArguments(args);
        Command command = Command.createCommand(populateCommandMap(), args[0] + " " + args[1], parameters);

        try {
            command.execute(dataSource);
        }catch(Exception e){
            if(e instanceof IOException){
                e.printStackTrace();
            }else{
                e.printStackTrace();
            }
        }


        /*try {
            command.execute(dataSource);
        } catch (SQLException e) {
            //client should never experience an SQLException(?), but it is thrown by execute in Command.
            e.printStackTrace();
        }*/


    }
    static private Map<String, Class<? extends Command>> populateCommandMap() {
        Map<String, Class<? extends Command>> map = new HashMap<>();

        // talk
        map.put("insert talk", ClientInsertTalkCommand.class);
        map.put("list talks", ClientListTalksCommand.class);
        map.put("delete talk", ClientDeleteTalkCommand.class);
        map.put("update talk", ClientUpdateTalkCommand.class);

        // day
        map.put("insert day", ClientInsertDayCommand.class);
        map.put("list days", ClientListDaysCommand.class);
        map.put("delete day", ClientDeleteDayCommand.class);
        map.put("update day", ClientUpdateDayCommand.class);

        // timeslot
        map.put("insert timeslot", ClientInsertTimeslotCommand.class);
        map.put("list timeslots", ClientListTimeslotsCommand.class);
        map.put("delete timeslot", ClientDeleteTimeslotCommand.class);
        map.put("update timeslot", ClientUpdateTimeslotCommand.class);

        // day
        map.put("insert conference", ClientInsertConferenceCommand.class);
        map.put("list conferences", ClientListConferencesCommand.class);
        map.put("delete conference", ClientDeleteConferenceCommand.class);
        map.put("update conference", ClientUpdateConferenceCommand.class);

        // connecting
        map.put("connect day-with-conference", ClientConnectDayWithConference.class);
        map.put("connect talk-with-timeslot", ClientConnectTalkWithTimeslotCommand.class);
        map.put("connect timeslot-with-day", ClientConnectTimeslotWithDayCommand.class);

        // TODO: SE OVER ISSE
        //map.put("list talk-with-timeslot", ConnectTalkWithTimeslotCommand.class);
        //map.put("remove timeslot-with-day", ConnectTimeslotWithDayCommand.class);

        //show conference program
        map.put("show schedule", ClientShowScheduleCommand.class);
        //create demo conference
        map.put("create demo", ClientCreateDemoConferenceCommand.class);
        // resetting the database
        map.put("reset db", ClientResetDBCommand.class);

        return map;

    }


}