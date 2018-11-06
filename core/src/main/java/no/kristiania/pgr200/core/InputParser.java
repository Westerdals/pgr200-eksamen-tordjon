package no.kristiania.pgr200.core;

import no.kristiania.pgr200.core.command.Command;
import no.kristiania.pgr200.core.command.InvalidInputCommand;

import java.util.HashMap;
import java.util.Map;

public class InputParser {




    public static Command decodeInput(Map<String, Class<? extends Command>> map,String type, HashMap<String, String> parameters) throws IllegalArgumentException {


        Class<? extends Command> command = map.get(type);


        if (command == null) {

            return null;
        }

        try {return command.newInstance().build(parameters);}
        catch (InstantiationException e) { e.printStackTrace();}
        catch (IllegalAccessException e) { e.printStackTrace();}

        return null;
    }


}
