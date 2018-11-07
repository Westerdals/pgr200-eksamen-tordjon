package no.kristiania.pgr200.core;

import no.kristiania.pgr200.core.command.Command;

import java.util.HashMap;
import java.util.Map;

public class InputParser {


    /**
     * Returns a built instance of the command
     * we want to execute, based on the given type
     * i.e. decodeInput(map, "myCustomAction", parametersForAction)
     * @param map possible "type-command"-pairs
     * @param type the type of command we want
     * @param parameters the parameters that chould be applied to the command
     * @return
     * @throws IllegalArgumentException
     */
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
